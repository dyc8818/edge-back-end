package com.jw.edge.service;

import com.jw.edge.entity.MyLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface LogService {
    public List<MyLog> findAll();
    public Page<MyLog> findAllByPage(int page, int rows);
    public int count();
    public List<MyLog> findByMsgLike(String msg);
    public List<MyLog> findByLevel(String level);
    public List<MyLog> findByThread(String thread);
}