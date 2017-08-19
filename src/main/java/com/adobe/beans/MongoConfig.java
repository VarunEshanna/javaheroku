package com.adobe.beans;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.authentication.UserCredentials;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.WriteConcern;

@Configuration
@EnableMongoRepositories(basePackageClasses = MyCollectionRepository.class)
public class MongoConfig
{
	@Value("mynewdb")
	private String  databaseName;

	@Value("ds145193.mlab.com")
	private String  mongoHost;

	@Value("45193")
	private Integer mongoPort;

	@Value("varun")
	private String  mongoUser;

	@Value("123456")
	private String  mongoPass;


	
	@Bean
	public MongoDbFactory mongoDbFactory() throws Exception {

	    // Set credentials      
	    MongoCredential credential = MongoCredential.createCredential(mongoUser, databaseName, mongoPass.toCharArray());
	    ServerAddress serverAddress = new ServerAddress(mongoHost, mongoPort);

	    // Mongo Client
	    MongoClient mongoClient = new MongoClient(serverAddress,Arrays.asList(credential)); 

	    // Mongo DB Factory
	    SimpleMongoDbFactory simpleMongoDbFactory = new SimpleMongoDbFactory(
	            mongoClient, databaseName);

	    return simpleMongoDbFactory;
	}

	/**
	 * Template ready to use to operate on the database
	 * 
	 * @return Mongo Template ready to use
	 */
	@Bean
	public MongoTemplate mongoTemplate() throws Exception {
	    return new MongoTemplate(mongoDbFactory());
	}
	
	/*
	@Override
	protected String getDatabaseName()
	{
		return this.dbName;
	}

	@Override
	public Mongo mongo() throws Exception
	{
		return new MongoClient(this.host, this.port);
	}

	@Override
	@Bean
	public SimpleMongoDbFactory mongoDbFactory() throws Exception
	{
		return new SimpleMongoDbFactory(mongo(), getDatabaseName());
	}

	@Override
	@Bean
	public MongoTemplate mongoTemplate() throws Exception
	{
		final UserCredentials userCredentials = new UserCredentials(this.userName, this.password);

		final MongoTemplate mongoTemplate = new MongoTemplate(mongo(), getDatabaseName(), userCredentials);
		mongoTemplate.setWriteConcern(WriteConcern.SAFE);

		return mongoTemplate;
	}*/

}