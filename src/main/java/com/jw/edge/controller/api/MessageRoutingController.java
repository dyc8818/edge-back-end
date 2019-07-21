package com.jw.edge.controller.api;

import com.jw.edge.entity.MessageRouting;
import com.jw.edge.entity.Product;
import com.jw.edge.service.MessageRoutingService;
import com.jw.edge.util.LayuiTableResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api")
@RestController
public class MessageRoutingController {
    @Autowired
    MessageRoutingService messageRoutingService;

    @GetMapping("/messageRoutings")
    @ResponseBody
    public LayuiTableResultUtil<List<MessageRouting>> getMessageRoutings(@RequestParam Integer page, @RequestParam Integer limit) {
        Pageable pageable = PageRequest.of(page-1, limit);
        Page<MessageRouting> messageRoutings =  messageRoutingService.findAllMessageRouting(pageable);
        //System.out.println("啊哈哈哈哈");
        LayuiTableResultUtil<List<MessageRouting>> messageRouingTable=new LayuiTableResultUtil<List<MessageRouting>>("",messageRoutings.getContent(),0,(int)messageRoutings.getTotalElements());
        return messageRouingTable;
    }
}
