package com.jw.edge.controller.api;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jw.edge.service.CommandService;
import com.jw.edge.service.MqService;
import com.jw.edge.util.LayuiTableResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RequestMapping("/api/scenario")
@RestController
public class ScenarioController {
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    CommandService commandService;
    @Autowired
    MqService mqService;
    @Value("${server.edgex}")
    private String ip;

    @GetMapping("/list")
    @ResponseBody
    public JSONArray checkCommand(){
        String allUrl = "http://"+ip+":48082/api/v1/device";
        JSONArray commands = new JSONArray();
        JSONArray all = new JSONArray(restTemplate.getForObject(allUrl,JSONArray.class));
        for(int i = 0; i<all.size();i++) {
            JSONObject deviceObj = all.getJSONObject(i);
            String deviceId = deviceObj.getString("id");
            String deviceName = deviceObj.getString("name");
            JSONArray commandsArr = deviceObj.getJSONArray("commands");
            for(int j = 0; j < commandsArr.size();j++) {
                String commandId = commandsArr.getJSONObject(j).getString("id");
                String commandName = commandsArr.getJSONObject(j).getString("name");
                JSONObject thisCommand = new JSONObject();
                thisCommand.put("deviceId",deviceId);
                thisCommand.put("deviceName",deviceName);
                thisCommand.put("commandId",commandId);
                thisCommand.put("commandName",commandName);
                commands.add(thisCommand);
            }
        }
        return commands;
    }

    @PostMapping("/command")
    @ResponseBody
    public String add(@RequestBody JSONObject info){
        boolean flag = commandService.addCommand(info);
        if(flag){
            return "添加成功！";
        }else {
            return "添加失败！";
        }
    }

    @DeleteMapping("/command")
    @ResponseBody
    public boolean delete(@RequestParam String name){
        boolean flag = commandService.deleteCommand(name);
        return flag;
    }

    @GetMapping("/command")
    @ResponseBody
    public LayuiTableResultUtil<JSONArray> show(@RequestParam Integer page, @RequestParam Integer limit){
        JSONArray table = commandService.showAll();
        return new LayuiTableResultUtil<JSONArray>("",table,0,table.size());
    }


    @JmsListener(destination = "run.command", containerFactory = "topicContainerFactory")
    public void subscribeCommand(JSONObject msg) {
        JSONObject fullMsg = commandService.find(msg.getString("name"));
        switch (fullMsg.getIntValue("level")){
            case 1:
                sendCommand(fullMsg);
                break;
            case 2:
                JSONArray array = fullMsg.getJSONArray("jsonArray");
                for(int i = 0; i < array.size(); i++){
                    JSONObject subMsg = commandService.find(array.getJSONObject(i).getString("name"));
                    sendCommand(subMsg);
                }
                break;
        }
    }

    @GetMapping("/test")
    public void sendTest(@RequestParam String name){
        JSONObject jsonCommand = new JSONObject();
        jsonCommand.put("name",name);
        mqService.publish("run.command",jsonCommand);
    }

    private void sendCommand(JSONObject command){
        String url = "http://"+ip+":48082/api/v1/device/" + command.getString("deviceId")+ "/command/" + command.getString("commandId");
        switch (command.getString("commandType")){
            case "get":
                try {
                    JSONObject getObj = new JSONObject(restTemplate.getForObject(url, JSONObject.class));
                    mqService.publish("show",getObj);
                } catch (Exception e) {
                    JSONObject err = new JSONObject();
                    err.put("name",command.getString("name"));
                    err.put("alert","失败！");
                    mqService.publish("show",err);
                }
                break;
            case "put":
                restTemplate.put(url,command.getJSONObject("jsonObject"),String.class);
                break;
        }
    }

}
