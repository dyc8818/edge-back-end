package com.jw.edge.controller.api;

import com.alibaba.fastjson.JSONObject;
import com.jw.edge.dao.DeviceRepository;
import com.jw.edge.entity.Function;
import com.jw.edge.entity.Rule;
import com.jw.edge.entity.Device;
import com.jw.edge.service.RuleService;
import com.jw.edge.service.DeviceService;
import com.jw.edge.service.FunctionService;
import com.jw.edge.util.LayuiTableResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api")
@RestController
public class RuleController {
    @Autowired
    RuleService ruleService;
    @Autowired
    FunctionService functionService;
    @Autowired
    DeviceService deviceService;
    @Autowired
    DeviceRepository deviceRepository;

    //设备功能映射
    @GetMapping("/ruleParas")
    @ResponseBody
    public List<Function> getruleParas() {
        List<Function> functions =  functionService.findAllFunction();
        // LayuiTableResultUtil<List<Product>> productsTable=new LayuiTableResultUtil<List<Product>>();
        return functions;
    }

    //对应设备映射
    @GetMapping("/ruleToDevices")
    @ResponseBody
    public List<Device> getruleToDevices() {
        List<Device> devices =  deviceService.findAllDevice();
        // LayuiTableResultUtil<List<Product>> productsTable=new LayuiTableResultUtil<List<Product>>();
        return devices;
    }

    //控制设备激活
    @PutMapping("/changeRuleStatus")
    @ResponseBody
    public int changeRuleStatus(@RequestBody JSONObject ruleID){
        JSONObject obj = new JSONObject(ruleID);//将json字符串转换为json对象

        System.out.println("ruleid:"+ruleID);
        System.out.println("ruleid:"+ruleID.getString("ruleId"));
        Rule rule = ruleService.findRuleByRuleId(ruleID.getString("deviceName"));
        Device device = deviceRepository.findDeviceByDeviceName(ruleID.getString("ruleToDevice"));
//        Device device = deviceService.findDeviceByDeviceId(ruleToDevice.getString("deviceId"));
        System.out.println("TEST:"+device.getDeviceId());
        int currentStatus = 1;
        int deviceCurrentStatus = 1;
        if(rule.getRuleStatus() == 1){
            currentStatus = 0;
        }else {
            currentStatus = 1;
            if(rule.getRuleExecute() == 0) {
                deviceCurrentStatus = 0;
            }
        }
        System.out.println("RuleController CurrentStatus:"+currentStatus);
        ruleService.changeRuleStatus(rule, currentStatus);
        deviceService.changeDeviceStatus(device, deviceCurrentStatus);
        return currentStatus;
    }

    @GetMapping("/rules")
    @ResponseBody
    public LayuiTableResultUtil<List<Rule>> Rules(@RequestParam Integer page, @RequestParam Integer limit) {
        Pageable pageable = PageRequest.of(page-1, limit);
        Page<Rule> rules =  ruleService.findAllRule(pageable);
        LayuiTableResultUtil<List<Rule>> rulesTable=new LayuiTableResultUtil<List<Rule>>("",rules.getContent(),0,(int)rules.getTotalElements());
        return rulesTable;
    }

    @PostMapping("/ruleCreate")
    @ResponseBody
    public Boolean addRule(@RequestBody Rule rule) {
        if (rule != null) {
            if (ruleService.addRule(rule)) {
                return true;
            }
        }
        return false;
    }

    @DeleteMapping("/rule")
    @ResponseBody
    public LayuiTableResultUtil<String> deleteRule(@RequestBody Rule rule){
        // System.out.println(rule.getRuleId());
        String deleteStatus = ruleService.deleteRule(rule.getRuleId());
        return  new LayuiTableResultUtil<String>("",deleteStatus,0,1);
    }
}
