package com.alibaba.csp.sentinel.dashboard.rule.redis.gateway;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.gateway.ApiDefinitionEntity;
import com.alibaba.csp.sentinel.dashboard.rule.DynamicRuleProvider;
import com.alibaba.csp.sentinel.dashboard.rule.redis.RedisUtil;
import com.alibaba.csp.sentinel.dashboard.rule.redis.RuleConsts;
import com.alibaba.csp.sentinel.util.StringUtil;
import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Component("gatewayApiDefinitionRedisProvider")
public class GatewayApiDefinitionRedisProvider implements DynamicRuleProvider<List<ApiDefinitionEntity>> {

    @Resource
    private RedisUtil redisUtil;

    @Override
    public List<ApiDefinitionEntity> getRules(String appName) throws Exception {
        String rules = redisUtil.getString(appName + ":" + RuleConsts.GATEWAY_API_DEFINITION);
        if (StringUtil.isEmpty(rules)) {
            return new ArrayList<>();
        }
        return JSON.parseArray(rules, ApiDefinitionEntity.class);
    }
}
