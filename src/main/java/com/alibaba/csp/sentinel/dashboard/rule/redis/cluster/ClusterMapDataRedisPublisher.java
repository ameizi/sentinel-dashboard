package com.alibaba.csp.sentinel.dashboard.rule.redis.cluster;

import com.alibaba.csp.sentinel.dashboard.domain.cluster.config.ClusterClientConfig;
import com.alibaba.csp.sentinel.dashboard.domain.cluster.request.ClusterAppAssignMap;
import com.alibaba.csp.sentinel.dashboard.rule.DynamicRulePublisher;
import com.alibaba.csp.sentinel.dashboard.rule.redis.RedisUtil;
import com.alibaba.csp.sentinel.dashboard.rule.redis.RuleConsts;
import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component("clusterMapDataRedisPublisher")
public class ClusterMapDataRedisPublisher implements DynamicRulePublisher<List<ClusterAppAssignMap>> {

    @Resource
    private RedisUtil redisUtil;

    @Override
    public void publish(String app, List<ClusterAppAssignMap> rules) throws Exception {
        if (rules == null) {
            return;
        }
        String strs = JSON.toJSONString(rules);
        ClusterClientConfig clusterClientConfig = new ClusterClientConfig().setRequestTimeout(20);
        redisUtil.setString(app + ":" + RuleConsts.RULE_CLUSTER_RULE, strs, RuleConsts.RULE_CLUSTER_FLOW_MAP);
        redisUtil.setString(app + ":" + RuleConsts.RULE_CLUSTER_CLIENT_CONFIG, JSON.toJSONString(clusterClientConfig), RuleConsts.RULE_CLUSTER_CLIENT_CONFIG_CHANNEL);
    }
}