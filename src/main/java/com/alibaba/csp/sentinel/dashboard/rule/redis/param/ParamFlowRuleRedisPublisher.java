package com.alibaba.csp.sentinel.dashboard.rule.redis.param;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.ParamFlowRuleCorrectEntity;
import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.ParamFlowRuleEntity;
import com.alibaba.csp.sentinel.dashboard.rule.DynamicRulePublisher;
import com.alibaba.csp.sentinel.dashboard.rule.redis.RedisUtil;
import com.alibaba.csp.sentinel.dashboard.rule.redis.RuleConsts;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Component("paramFlowRuleRedisPublisher")
public class ParamFlowRuleRedisPublisher implements DynamicRulePublisher<List<ParamFlowRuleEntity>> {

    @Resource
    private RedisUtil redisUtil;

    @Override
    public void publish(String app, List<ParamFlowRuleEntity> rules) throws Exception {
        if (rules == null) {
            return;
        }
        for (ParamFlowRuleEntity rule : rules) {
            if (rule.getApp() == null) {
                rule.setApp(app);
            }
        }
        //  转换
        List<ParamFlowRuleCorrectEntity> list = rules.stream().map(rule -> {
            ParamFlowRuleCorrectEntity entity = new ParamFlowRuleCorrectEntity();
            BeanUtils.copyProperties(rule, entity);
            return entity;
        }).collect(Collectors.toList());
        redisUtil.setString(app + ":" + RuleConsts.RULE_PARAM, JSON.toJSONString(list), RuleConsts.RULE_PARAM_CHANNEL);
    }
}