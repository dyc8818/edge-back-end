package com.jw.edge.dao;

import com.jw.edge.entity.Surveillance;
import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SurveillanceRepository extends MongoRepository<Surveillance,String>{
    //自定义的查询方法
    List<Surveillance> findBySurveillanceFeatureType(String type);
    Surveillance findBySurveillanceName(String name);
    List<Surveillance> findBySurveillanceFeatureValueBetween(int from, int to);
    List<Surveillance> findBySurveillanceFeatureTypeAndSurveillanceFeatureValueIsBetween(String type, int from, int to);
}
