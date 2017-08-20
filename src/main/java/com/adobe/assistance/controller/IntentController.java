package com.adobe.assistance.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.adobe.assistance.beans.ResponseData;
import com.adobe.assistance.beans.UserData;
import com.adobe.assistance.service.IntentService;

@RestController
public class IntentController {
	
	@Autowired
	IntentService intentService;
	
	@RequestMapping(value = "/getLuisData", method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseData getLuisData(@RequestBody ResponseData responseData){
		if(responseData.getLuisCallRequired()){
			responseData = intentService.getLUISData(responseData);
			
			Map<String,String> testMap = new HashMap<String,String>();
			for(UserData userData : responseData.getUserData()){
				testMap.put(userData.getName(), userData.getAppMessage());
			}

			responseData = intentService.updateUserData(responseData);
			
			for(UserData userData : responseData.getUserData()){
				for(String s :testMap.keySet()){
					if(s.equals(userData.getName())){
						userData.setUserResponse(testMap.get(s));
					}
				}
			}
		}
		return responseData;
	}
	
	@RequestMapping(value = "/getRespData", method = RequestMethod.POST)
	public ResponseData getResponseData(@RequestBody ResponseData responseData){
		boolean finalResponse = true;
		for(UserData userData : responseData.getUserData()){
			if(userData.getUserResponse() == null && userData.getUserResponse().equals("") && userData.getType().equals("ENTITY")){
				finalResponse = false;
			}
		}
		
		if(finalResponse){
			responseData.setFinalResponse(true);
		}
		return responseData;
		
	}

}
