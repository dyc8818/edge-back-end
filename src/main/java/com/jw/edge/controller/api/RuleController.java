package com.jw.edge.controller.api;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jw.edge.dao.DeviceRepository;
import com.jw.edge.dao.RuleRepository;
import com.jw.edge.entity.Function;
import com.jw.edge.entity.Product;
import com.jw.edge.entity.Rule;
import com.jw.edge.entity.Device;
import com.jw.edge.service.ProductServie;
import com.jw.edge.service.RuleService;
import com.jw.edge.service.DeviceService;
import com.jw.edge.service.FunctionService;
import com.jw.edge.util.LayuiTableResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
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
    ProductServie productServie;
    @Autowired
    DeviceRepository deviceRepository;
    @Autowired
    RuleRepository ruleRepository;
    @Autowired
    private RestTemplate restTemplate;
    @Value("${server.edgex}")
    private String ip;

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
//    public List<Device> getruleToDevices() {
//        List<Device> devices =  deviceService.findAllDevice();
//        // LayuiTableResultUtil<List<Product>> productsTable=new LayuiTableResultUtil<List<Product>>();
//        return devices;
//    }
    public List<Product> getruleToDevices(){
        List<Product> products = productServie.findAllProduct();
        return products;
    }

/*    //监听告警 暂时屏蔽了
    @GetMapping("/alertDetails")
    @ResponseBody
//    public JSONObject getDetails(@RequestBody JSONObject ruleID){
//        JSONObject obj = new JSONObject(ruleID);//将json字符串转换为json对象
//        Rule rule = ruleService.findRuleByRuleId(ruleID.getString("ruleId"));
//        int ruleThreshold = rule.getRuleParaThreshold();
//        int ruleCurrentStatus = rule.getRuleStatus();
    public JSONObject getDetails(){
        String ruleName = "规则测试1";
        int ruleExecute = 1;
        Rule rule = ruleRepository.findRuleByRuleExecute(ruleExecute);
//        Rule rule = ruleRepository.findRuleByRuleName(ruleName);
        int ruleThreshold = rule.getRuleParaThreshold();
        int ruleCurrentStatus = rule.getRuleStatus();
        int ruleExecuteStatus = rule.getRuleExecute();
        int ruleJudge = rule.getRuleJudge();
        String rulePara = rule.getRulePara();
        String alertDeviceName = rule.getRuleToDevice();
//        int deviceCurrentStatus = rule.getRuleStatus();
//        int ruleThreshold = 20;
        String deviceId = "94e46d91-b4f5-465b-a4ce-28e379ef97fb";
        String commandsId = "71dc7a67-c13b-4606-9dbf-9522efeea173";
        String TemperCommandsId = "71dc7a67-c13b-4606-9dbf-9522efeea173";
        String HumidityCommandsId = "5a4bb0ff-d1a8-4cbf-bd70-0c15307f64b6";
        int ident = 0;
        if(rulePara.equals("TemperatureDegC")){
            commandsId = TemperCommandsId;
            ident = 0;
        }
        if(rulePara.equals("HumidityPercentRH")){
            commandsId = HumidityCommandsId;
            ident = 1;
        }

        String url = "http://"+ip+":48082/api/v1/device/"+deviceId+"/command/"+commandsId;

//        if(ruleStatus == 1) {}
        JSONObject result = new JSONObject();
        JSONObject commandObj = new JSONObject(restTemplate.getForObject(url, JSONObject.class));
        JSONObject reading = commandObj.getJSONArray("readings").getJSONObject(0);
        result.put(("ruleJudge"), (ruleJudge));
        result.put(reading.getString("name"), reading.getIntValue("value"));
        result.put(("ruleThreshold"), (ruleThreshold));
        result.put(("ruleExecuteStatus"), (ruleExecuteStatus));
        result.put(("ruleCurrentStatus"), (ruleCurrentStatus));
        result.put(("alertDeviceName"), (alertDeviceName));
        result.put(("ident"), (ident));
        return result;
    }*/

    //控制设备激活
    @PutMapping("/changeRuleStatus")
    @ResponseBody
    public int changeRuleStatus(@RequestBody JSONObject ruleID){
        JSONObject obj = new JSONObject(ruleID);//将json字符串转换为json对象

//        System.out.println("ruleid:"+ruleID);
//        System.out.println("ruleid:"+ruleID.getString("ruleId"));
        Rule rule = ruleService.findRuleByRuleId(ruleID.getString("ruleId"));
        Device device = deviceRepository.findDeviceByDeviceName(rule.getRuleToDevice());
        int ruleThreshold = rule.getRuleParaThreshold();

//        System.out.println("TEST:"+device.getDeviceId());
        int currentStatus = 1;
        int deviceCurrentStatus = 1;
        if(rule.getRuleStatus() == 1){
            currentStatus = 0;
        }else {
            currentStatus = 1;
            if(rule.getRuleExecute() == 0) {
                deviceCurrentStatus = 0;
                deviceService.changeDeviceStatus(device, deviceCurrentStatus);
            }
        }
//        System.out.println("RuleController CurrentStatus:"+currentStatus);
        ruleService.changeRuleStatus(rule, currentStatus);
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
