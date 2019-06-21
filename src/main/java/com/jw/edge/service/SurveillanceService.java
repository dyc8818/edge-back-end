package com.jw.edge.service;

import com.jw.edge.entity.Surveillance;
import java.util.List;

public interface SurveillanceService {
    List<Surveillance> findAll();
    List<Surveillance> findByType(String type);
    List<Surveillance> findByTypeAndValue(String type, int from, int to);
}
