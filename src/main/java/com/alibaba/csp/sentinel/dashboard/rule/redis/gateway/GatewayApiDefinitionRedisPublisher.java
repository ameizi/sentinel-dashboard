package com.alibaba.csp.sentinel.dashboard.rule.redis.gateway;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.gateway.ApiDefinitionEntity;
import com.alibaba.csp.sentinel.dashboard.rule.DynamicRulePublisher;
import com.alibaba.csp.sentinel.dashboard.rule.redis.RedisUtil;
import com.alibaba.csp.sentinel.dashboard.rule.redis.RuleConsts;
import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component("gatewayApiDefinitionRedisPublisher")
public class GatewayApiDefinitionRedisPublisher implements DynamicRulePublisher<List<ApiDefinitionEntity>> {

    @Resource
    private RedisUtil redisUtil;

    @Override
    public void publish(String app, List<ApiDefinitionEntity> rules) throws Exception {
        if (rules == null) {
            return;
        }
        String strs = JSON.toJSONString(rules);
        redisUtil.setString(app + ":" + RuleConsts.GATEWAY_API_DEFINITION, strs, RuleConsts.GATEWAY_API_DEFINITION_CHANNEL);
    }

}
