package com.adobe.beans;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection="customCollection1")
public class MyCustomCollection {
	@Id
	private String Id;
	@Field(value="FIELDDATA")
	private String fieldData;
	private String userText;
	private String entity;
	
	public MyCustomCollection(String fieldData, String userText, String entity) {
		super();
		this.fieldData = fieldData;
		this.userText = userText;
		this.entity = entity;
	}
	
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public String getFieldData() {
		return fieldData;
	}
	public void setFieldData(String fieldData) {
		this.fieldData = fieldData;
	}
	public String getUserText() {
		return userText;
	}
	public void setUserText(String userText) {
		this.userText = userText;
	}
	public String getEntity() {
		return entity;
	}
	public void setEntity(String entity) {
		this.entity = entity;
	}
	

}
