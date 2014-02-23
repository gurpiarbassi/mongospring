package com.gurps.mongo.main;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;

@Configuration
@EnableMongoRepositories(basePackages = "com.gurps.mongo.repository")
@EnableAutoConfiguration
public class Config extends AbstractMongoConfiguration {

	  @Override
	  protected String getDatabaseName() {
	    return "test3";
	  }

	  @Override
	  public Mongo mongo() throws Exception {
	    return new MongoClient("127.0.0.1");
	  }

	  @Override
	  protected String getMappingBasePackage() {
	    return "com.gurps.mongo.reposository";
	  }
}
