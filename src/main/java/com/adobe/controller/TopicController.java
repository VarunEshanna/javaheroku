package com.adobe.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.adobe.beans.MyCollectionRepository;
import com.adobe.beans.MyCustomCollection;
import com.adobe.beans.Topics;

@RestController
public class TopicController {

	@Autowired
	private MyCollectionRepository repository;

	@RequestMapping("/topics")
	public List<Topics> getTopics(){
		List<Topics> listTopics = new ArrayList<Topics>();
		listTopics.add(new Topics("Spring AOP","Handles AOP modules"));
		listTopics.add(new Topics("Spring Boot","Manages standalone to run as Spring application"));
		return listTopics;
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/topics")
	public List<Topics> addTopic(){
		List<Topics> listTopics = new ArrayList<Topics>();
		listTopics.add(new Topics("Spring AOP","Handles AOP modules"));
		listTopics.add(new Topics("Spring Boot","Manages standalone to run as Spring application"));
		return listTopics;
	}
	
	@RequestMapping(value = "/getdata", method = RequestMethod.GET)
	public List<MyCustomCollection> helloWorld() {
		List<MyCustomCollection> users = repository.findAll();
		return users;
	}
	
}
