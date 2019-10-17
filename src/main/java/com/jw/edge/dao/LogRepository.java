package com.jw.edge.dao;

import com.jw.edge.entity.MyLog;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogRepository extends MongoRepository<MyLog,String> {
    public List<MyLog> findByMsgLike(String msg);
    public List<MyLog> findByLevel(String level);
    public List<MyLog> findByThread(String thread);
}
