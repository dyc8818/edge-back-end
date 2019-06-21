package com.jw.edge.service.impl;

import com.jw.edge.dao.RuleRepository;
import com.jw.edge.entity.Rule;
import com.jw.edge.service.RuleService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RuleServiceImpl implements RuleService {
    @Autowired
    RuleRepository ruleRepository;

    @Override
    public Page<Rule> findAllRule(Pageable pageable) {
        Page<Rule> rules = ruleRepository.findAll(pageable);
        return rules;
    }

    @Override
    public List<Rule> findAllRule() {
        return ruleRepository.findAll();
    }

    @Override
    public boolean addRule(Rule rule) {
        Rule findRule = ruleRepository.findRuleByRuleName(rule.getRuleName());
        if (findRule == null) {
            Rule rule1 = ruleRepository.save(rule);
            ObjectId objectId = new ObjectId(rule1.getRuleId());
            rule1.setRuleCreateTime(objectId.getDate());
            ruleRepository.save(rule1);
            return true;

        } else {
            return false;
        }
    }

    @Override
    public String deleteRule(String ruleId) {
        Rule rule = ruleRepository.findRuleByRuleId(ruleId);
        if (rule == null) {
            return "不存在该规则";
        } else {
            ruleRepository.deleteById(ruleId);
            return "删除成功";
        }
    }

    @Override
    public Rule findRuleByRuleId(String ruleId) {
        return ruleRepository.findRuleByRuleId(ruleId);
    }

}
