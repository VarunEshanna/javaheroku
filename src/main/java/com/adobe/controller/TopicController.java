package com.adobe.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adobe.beans.Topics;

@RestController
public class TopicController {

	@RequestMapping("/topics")
	public List<Topics> getTopics(){
		List<Topics> listTopics = new ArrayList<Topics>();
		listTopics.add(new Topics("Spring AOP","Handles AOP modules"));
		listTopics.add(new Topics("Spring Boot","Manages standalone to run as Spring application"));
		return listTopics;
	}
	
}
