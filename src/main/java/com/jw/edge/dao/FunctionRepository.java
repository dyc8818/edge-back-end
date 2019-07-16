package com.jw.edge.dao;

import com.jw.edge.entity.Function;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FunctionRepository extends MongoRepository<Function,String> {
    //自定义的查询方法
    public Function findFunctionByFunctionName(String functionName);
    public void deleteByFunctionName(String functionName);
    public Page<Function> findAll(Pageable pageable);
    public Function findFunctionByFunctionId(String functionId);
    public Page<Function> findByProductName(String productName,Pageable pageable);
    public List<Function> findByProductName(String productName);
}

