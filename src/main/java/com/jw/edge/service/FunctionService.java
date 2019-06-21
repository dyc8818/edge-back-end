package com.jw.edge.service;

import com.jw.edge.entity.Function;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FunctionService {
    public boolean addFunction(Function function);
    public String deleteFunction(String functionName);
    public Function findFunctionByFunctionName(String functionName);
    //void updateFunction(Function function);
    public List<Function> findAllFunction();
    public Page<Function> findAllFunction(Pageable pageable);
}
