package com.alibaba.csp.sentinel.dashboard.rule.redis.authority;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.AuthorityRuleCorrectEntity;
import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.AuthorityRuleEntity;
import com.alibaba.csp.sentinel.dashboard.rule.DynamicRulePublisher;
import com.alibaba.csp.sentinel.dashboard.rule.redis.RedisUtil;
import com.alibaba.csp.sentinel.dashboard.rule.redis.RuleConsts;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Component("authorityRuleRedisPublisher")
public class AuthorityRuleRedisPublisher implements DynamicRulePublisher<List<AuthorityRuleEntity>> {

    @Resource
    private RedisUtil redisUtil;

    @Override
    public void publish(String app, List<AuthorityRuleEntity> rules) throws Exception {
        if (rules == null) {
            return;
        }
        for (AuthorityRuleEntity rule : rules) {
            if (rule.getApp() == null) {
                rule.setApp(app);
            }
        }
        //  转换
        List<AuthorityRuleCorrectEntity> list = rules.stream().map(rule -> {
            AuthorityRuleCorrectEntity entity = new AuthorityRuleCorrectEntity();
            BeanUtils.copyProperties(rule, entity);
            return entity;
        }).collect(Collectors.toList());
        redisUtil.setString(app + ":" + RuleConsts.RULE_AUTHORITY, JSON.toJSONString(list), RuleConsts.RULE_AUTHORITY_CHANNEL);
    }
}
