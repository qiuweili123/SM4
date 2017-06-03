package com.sh.mongo.dao;

import com.sh.mongo.model.UserMongo;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserMongoDao extends MongoRepository<UserMongo, String> {

    public List<UserMongo> findByEmail(String email);

    public UserMongo findOneByUserId(String userId);


}
