package com.jw.edge.controller.api;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jw.edge.entity.Command;
import com.jw.edge.service.CommandService;
import com.jw.edge.service.MqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RequestMapping("/api/commands")
@RestController
public class CommandController {
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
        Command command = new Command();
        command.setName(info.getString("name"));
        command.setCommandId(info.getString("commandId"));
        command.setCommandName(info.getString("commandName"));
        command.setDeviceId(info.getString("deviceId"));
        command.setDeviceName(info.getString("deviceName"));
        command.setCommandType(info.getString("commandType"));
        command.setJsonObject(info.getJSONObject("jsonObject"));
        command.setJsonArray(info.getJSONArray("jsonArray"));
        boolean flag = commandService.addCommand(command);
        if(flag==true){
            return "添加成功！";
        }else {
            return "添加失败！";
        }
    }

    @DeleteMapping("/command")
    @ResponseBody
    public String delete(@RequestParam String name){
        boolean flag = commandService.deleteCommand(name);
        if (flag == true){
            return "删除成功！";
        }else {
            return "删除失败！";
        }
    }

    @GetMapping("/command")
    @ResponseBody
    public JSONArray show(){
        JSONArray table = commandService.showAll();
        return table;
    }


    @JmsListener(destination = "run.command", containerFactory = "topicContainerFactory")
    public void subscribeCommand(JSONObject msg) {
        String url = "http://"+ip+":48082/api/v1/device/" + msg.getString("deviceId")+ "/command/" + msg.getString("commandId");
        switch (msg.getString("commandType")){
            case "get":
                try {
                    JSONObject getObj = new JSONObject(restTemplate.getForObject(url, JSONObject.class));
                    mqService.publish("show",getObj);
                } catch (Exception e) {
                    JSONObject err = new JSONObject();
                    err.put("commandName",msg.getString("commandName"));
                    err.put("alert","失败！");
                    mqService.publish("show",err);
                }
                break;
            case "put":
                restTemplate.put(url,msg.getJSONObject("jsonObject"),String.class);
                break;
        }
    }

    @GetMapping("/test")
    public void sendCommand(@RequestParam String name){
        JSONObject jsonCommand = commandService.find(name);
        mqService.publish("run.command",jsonCommand);
    }

}
