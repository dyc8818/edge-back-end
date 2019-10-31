package com.jw.edge.service.impl;

import com.jw.edge.dao.LogRepository;
import com.jw.edge.entity.MyLog;
import com.jw.edge.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogServiceImpl implements LogService {

    @Autowired
    LogRepository logRepository;
    @Override
    public List<MyLog> findAll(){
        return logRepository.findAll();
    }

    /* 返回一共条数 */
    @Override
    public int count(){
        long size = logRepository.count();
        int count = Integer.valueOf(String.valueOf(size));
        return count;
    }
    /* 按页查 两个属性，第一个是页码，第二个是页面大小
       注意：页码在mongodb中是从0开始的。*/
    @Override
    public Page<MyLog> findAllByPage(int page, int rows) {
        PageRequest pageRequest = PageRequest.of(page-1,rows);
        return logRepository.findAll(pageRequest);
    }
    @Override
    public List<MyLog> findByMsgLike(String msg) {
        return logRepository.findByMsgLike(msg);
    }

    @Override
    public List<MyLog> findByLevel(String level) {
        return logRepository.findByLevel(level);
    }

    @Override
    public List<MyLog> findByThread(String thread) {
        return logRepository.findByThread(thread);
    }
}
