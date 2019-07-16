package com.jw.edge.dao;

import com.jw.edge.entity.Rule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RuleRepository extends MongoRepository<Rule,String> {
    public Rule findRuleByRuleName(String ruleName);
    public Rule findRuleByRuleExecute(int ruleExecute);
    public Page<Rule> findAll(Pageable pageable);
    public Rule findRuleByRuleId(String ruleId);
}
