package com.alibaba.csp.sentinel.dashboard.rule.redis.system;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.SystemRuleEntity;
import com.alibaba.csp.sentinel.dashboard.rule.DynamicRulePublisher;
import com.alibaba.csp.sentinel.dashboard.rule.redis.RedisUtil;
import com.alibaba.csp.sentinel.dashboard.rule.redis.RuleConsts;
import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component("systemRuleRedisPublisher")
public class SystemRuleRedisPublisher implements DynamicRulePublisher<List<SystemRuleEntity>> {

    @Resource
    private RedisUtil redisUtil;

    @Override
    public void publish(String app, List<SystemRuleEntity> rules) throws Exception {
        if (rules == null) {
            return;
        }
        String strs = JSON.toJSONString(rules);
        redisUtil.setString(app + ":" + RuleConsts.RULE_SYSTEM, strs, RuleConsts.RULE_SYSTEM_CHANNEL);
    }
}