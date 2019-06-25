package com.jw.edge.controller.api;

import com.jw.edge.entity.Surveillance;
import com.jw.edge.service.SurveillanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api")
@RestController
public class SurveillanceController {
    @Autowired
    SurveillanceService surveillanceService;

    @GetMapping("/surveillances")
    @ResponseBody
    public List<Surveillance> getSurveillance(){
        return surveillanceService.findAll();
    }

    @GetMapping("/surveillance")
    @ResponseBody
    public List<Surveillance> getType(@RequestParam String type){
        return surveillanceService.findByType(type);
    }

    @GetMapping("/somesurveillance")
    @ResponseBody
    public List<Surveillance> getTypeOfValue(@RequestParam String type, @RequestParam int from, @RequestParam int to){
        return surveillanceService.findByTypeAndValue(type,from,to);
    }

    @GetMapping("/surnum")
    @ResponseBody
    public int getSurNum(){
        return surveillanceService.findAll().size();
    }


}
