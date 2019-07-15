package com.jw.edge.controller.api;

import com.jw.edge.entity.Function;
import com.jw.edge.service.FunctionService;
import com.jw.edge.util.LayuiTableResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api")
@RestController
public class FunctionController {
    @Autowired
    FunctionService functionService;

    @GetMapping("/functions")
    @ResponseBody
/*
    public List<Function> getFunction() {
        return functionService.findAllFunctions();
    }

 */
    public LayuiTableResultUtil<List<Function>> getFunctions(@RequestParam Integer page, @RequestParam Integer limit) {
        Pageable pageable = PageRequest.of(page-1, limit);
        Page<Function> functions =  functionService.findAllFunction(pageable);
        LayuiTableResultUtil<List<Function>> functionsTable=new LayuiTableResultUtil<List<Function>>("",functions.getContent(),0,(int)functions.getTotalElements());
        return functionsTable;
    }

    @GetMapping("/functionsByProductName")
    @ResponseBody
    public LayuiTableResultUtil<List<Function>> getFunctionsByProductName(@RequestParam Integer page, @RequestParam Integer limit,@RequestParam String productName) {
        Pageable pageable = PageRequest.of(page-1, limit);
        Page<Function> functions =  functionService.findFunctionByProductName(productName,pageable);
        LayuiTableResultUtil<List<Function>> functionsTable=new LayuiTableResultUtil<List<Function>>("",functions.getContent(),0,(int)functions.getTotalElements());
        return functionsTable;
    }

    @PostMapping("/function")
    @ResponseBody
    public Boolean addFunction(@RequestBody Function function) {
        if (function != null) {
            if (functionService.addFunction(function)) {
                return true;
            }
        }
        return false;
    }

    @DeleteMapping("/function")
    @ResponseBody
    public LayuiTableResultUtil<String> deleteFunction(@RequestBody Function function){
        // System.out.println(product.getProductId());
        String deleteStatus = functionService.deleteFunction(function.getFunctionId());
        return  new LayuiTableResultUtil<String>("",deleteStatus,0,1);
    }
/*
    @GetMapping("/function")
    @ResponseBody
    public Function getFunctionByName(@RequestParam("name") String name) {
        return functionService.findFunction(name);
    }

    @PostMapping("/function")
    @ResponseBody
    public String addFunction(@RequestBody Function function) {
        try {
            functionService.addFunction(function);
            return "success";
        }catch (Exception e){
            return "failed";
        }
    }
/*
    @PutMapping("/function")
    @ResponseBody
    public String updateFunction(@RequestParam String name,@RequestParam String identifier) {
        try {
            functionService.updateFunction(new Function(name,identifier));
            return "success";
        }catch (Exception e){
            return "failed";
        }
    }

    @DeleteMapping("/function")
    @ResponseBody
    public String deleteFunction(@RequestParam String name) {
        try {
           functionService.deleteFunction(name);
            return "success";
        }catch (Exception e){
            return "failed";
        }
    }
*/

}
