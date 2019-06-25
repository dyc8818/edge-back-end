package com.jw.edge.service.impl;

import com.jw.edge.dao.FunctionRepository;
import com.jw.edge.entity.Function;
import com.jw.edge.service.FunctionService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FunctionServiceImpl implements FunctionService{
    @Autowired
    FunctionRepository functionRepository;

    @Override
    public Page<Function> findAllFunction(Pageable pageable) {
        Page<Function> functions = functionRepository.findAll(pageable);
        return functions;
    }

    @Override
    public boolean addFunction(Function function) {
        Function findFunction = functionRepository.findFunctionByFunctionName(function.getFunctionName() );
        if (findFunction == null) {
            Function function1 = functionRepository.save(function);
            ObjectId objectId = new ObjectId(function1.getFunctionId());
            //function1.setFunctionCreateTime(objectId.getDate());
            functionRepository.save(function1);
            return true;

        } else {
            return false;
        }
    }

    @Override
    public String deleteFunction(String functionName) {
        Function function = functionRepository.findFunctionByFunctionName(functionName);
        if (function == null) {
            return "不存在该功能";
        } else {
            functionRepository.deleteByFunctionName(functionName);
            return "删除成功";
        }
    }

    @Override
    public Function findFunctionByFunctionName(String functionName) {
        return functionRepository.findFunctionByFunctionName(functionName);
    }


    /*@Override
    public void updateFunction(Function function) {
        Function user1 = findFunction(function.getFunctionName());
        user1.setIdentifier(function.getIdentifier());
        functionRepository.save(user1);
    }*/



    @Override
    public List<Function> findAllFunction() {
        return functionRepository.findAll();
    }


}
