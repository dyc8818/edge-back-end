package com.jw.edge.controller.api;


import com.jw.edge.entity.MyLog;
import com.jw.edge.service.LogService;
import com.jw.edge.service.impl.LogServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RequestMapping("/api")
@RestController
public class LogController {

    @Autowired
    LogService logService;

    @GetMapping("/mylogs")
    @ResponseBody
    public List<MyLog> getMyLog() {
        return logService.findAll();
    }
    @GetMapping("/page")
    @ResponseBody
    public Page<MyLog> getAllByPage(@RequestParam("page")int page,@RequestParam("rows")int rows) {
        return logService.findAllByPage(page,rows);
    }

    @GetMapping("/level")
    @ResponseBody
    public List<MyLog> getAllByLevel(@RequestParam("level") String level) {
        return logService.findByLevel(level);
    }

    @GetMapping("/msg")
    @ResponseBody
    public List<MyLog> getAllByMsgLike(@RequestParam("msg") String msg) {
        return logService.findByMsgLike(msg);
    }

    @GetMapping("/thread")
    @ResponseBody
    public List<MyLog> getAllByThread(@RequestParam("thread") String thread) {
        return logService.findByThread(thread);
    }

    @GetMapping("/count")
    @ResponseBody
    public int getCount() {
        return logService.count();
    }


}
