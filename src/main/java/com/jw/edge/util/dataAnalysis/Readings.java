package com.jw.edge.util.dataAnalysis;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONArray;
import com.jw.edge.controller.api.MessageRouterController;
import com.jw.edge.service.MqService;
import com.jw.edge.util.ApplicationContextProvider;
import org.apache.activemq.command.*;

import javax.jms.*;
import java.util.Map;

public class Readings implements Runnable {

    private String name;
    private String incomingQueue;
    private String outgoingQueue;
    private ConnectionFactory connectionFactory = MessageRouterController.connectionFactory;
    private MqService mqService = ApplicationContextProvider.getBean(MqService.class);

    public Readings(String name, String incomingQueue, String outgoingQueue) {
        this.name = name;
        this.incomingQueue = incomingQueue;
        this.outgoingQueue = outgoingQueue;
    }

    public JSONObject getInfo(){
        JSONObject info = new JSONObject();
        info.put("name",this.name);
        info.put("incomingQueue",this.incomingQueue);
        info.put("outgoingQueue",this.outgoingQueue);
        return info;
    }

    @Override
    public void run() {
        try {
            Connection connection = connectionFactory.createConnection();
            connection.start();
            Session session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createTopic(this.incomingQueue);
            MessageConsumer consumer = session.createConsumer(destination);
            while (check()) {
                try {
                    ActiveMQBytesMessage activeMQMessage = (ActiveMQBytesMessage) consumer.receive();
                    byte[] bt = new byte[(int) activeMQMessage.getBodyLength()];
                    activeMQMessage.readBytes(bt);
                    System.out.println(bt.toString());
//                    JSONObject msg = new JSONObject();
//                    System.out.println("收到"+destination+msg);
                    //TO DO CACULATION HERE

//                    mqService.publish(this.outgoingQueue,msg);

                }catch (Exception e){e.printStackTrace();break;}
            }
            connection.close();
        }catch (Exception e){e.printStackTrace();}
    }

    public boolean check(){
        boolean flag = false;
        JSONArray array = MessageRouterController.status;
        for (int i = 0; i < array.size(); i++){
            if(name.equals(array.getJSONObject(i).getString("name"))){
                flag = true;
            }
        }
        return flag;
    }
}
