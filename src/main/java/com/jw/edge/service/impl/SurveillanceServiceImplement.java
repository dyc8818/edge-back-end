package com.jw.edge.service.impl;

import com.jw.edge.dao.SurveillanceRepository;
import com.jw.edge.entity.Surveillance;
import com.jw.edge.service.SurveillanceService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SurveillanceServiceImplement implements SurveillanceService{
    @Autowired
    SurveillanceRepository surveillanceRepository;

    @Override
    public List<Surveillance> findAll(){
        return surveillanceRepository.findAll();
    }

    @Override
    public List<Surveillance> findByType(String type){
        return surveillanceRepository.findBySurveillanceFeatureType(type);
    }

    @Override
    public List<Surveillance> findByTypeAndValue(String type, int from, int to){
        return surveillanceRepository.findBySurveillanceFeatureTypeAndSurveillanceFeatureValueIsBetween(type, from, to);
    }
}
