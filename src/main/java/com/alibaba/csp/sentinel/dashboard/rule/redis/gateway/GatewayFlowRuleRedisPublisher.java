package com.alibaba.csp.sentinel.dashboard.rule.redis.gateway;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.gateway.GatewayFlowRuleEntity;
import com.alibaba.csp.sentinel.dashboard.rule.DynamicRulePublisher;
import com.alibaba.csp.sentinel.dashboard.rule.redis.RedisUtil;
import com.alibaba.csp.sentinel.dashboard.rule.redis.RuleConsts;
import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component("gatewayFlowRuleRedisPublisher")
public class GatewayFlowRuleRedisPublisher implements DynamicRulePublisher<List<GatewayFlowRuleEntity>> {

    @Resource
    private RedisUtil redisUtil;

    @Override
    public void publish(String app, List<GatewayFlowRuleEntity> rules) throws Exception {
        if (rules == null) {
            return;
        }
        String strs = JSON.toJSONString(rules);
        redisUtil.setString(app + ":" + RuleConsts.GATEWAY_RULE_FLOW, strs, RuleConsts.GATEWAY_RULE_FLOW_CHANNEL);
    }

}
