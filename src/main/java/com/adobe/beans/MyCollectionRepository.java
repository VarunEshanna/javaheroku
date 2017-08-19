package com.adobe.beans;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyCollectionRepository extends MongoRepository<MyCustomCollection, String> {

}
