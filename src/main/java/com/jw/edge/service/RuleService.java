package com.jw.edge.service;

import com.jw.edge.entity.Rule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RuleService {
    public Page<Rule> findAllRule(Pageable pageable);
    public List<Rule> findAllRule();
    public boolean addRule(Rule rule);
    public String deleteRule(String ruleId);
    public Rule findRuleByRuleId(String ruleId);
}
