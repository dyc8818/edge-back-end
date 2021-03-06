package com.jw.edge.dao;

import com.jw.edge.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User,String> {
    //自定义的查询方法
    public void deleteByUsername(String name);
    public User findUserByUsername(String name);

}