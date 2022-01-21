package com.alibaba.csp.sentinel.dashboard.rule.redis;

public class RuleConsts {

    /**
     * 限流规则key前缀
     */
    public static final String RULE_FLOW = "sentinel_rule_flow";
    public static final String RULE_FLOW_CHANNEL = "sentinel_rule_flow_channel";

    /**
     * 降级规则key前缀
     */
    public static final String RULE_DEGRADE = "sentinel_rule_degrade";
    public static final String RULE_DEGRADE_CHANNEL = "sentinel_rule_degrade_channel";

    /**
     * 系统规则key前缀
     */
    public static final String RULE_SYSTEM = "sentinel_rule_system";
    public static final String RULE_SYSTEM_CHANNEL = "sentinel_rule_system_channel";

    /**
     * 参数热点规则key前缀
     */
    public static final String RULE_PARAM = "sentinel_rule_param";
    public static final String RULE_PARAM_CHANNEL = "sentinel_rule_param_channel";

    /**
     * 授权规则key前缀
     */
    public static final String RULE_AUTHORITY = "sentinel_rule_authority";
    public static final String RULE_AUTHORITY_CHANNEL = "sentinel_rule_authority_channel";

    /**
     * 集群限流规则key前缀
     */
    public static final String RULE_CLUSTER_RULE = "sentinel_cluster_rule_flow";
    public static final String RULE_CLUSTER_FLOW_MAP = "sentinel_cluster_rule_flow_map";

    public static final String RULE_CLUSTER_CLIENT_CONFIG = "sentinel_cluster_client_config";
    public static final String RULE_CLUSTER_CLIENT_CONFIG_CHANNEL = "sentinel_cluster_client_config_channel";

}

