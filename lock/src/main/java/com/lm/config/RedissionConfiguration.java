package com.lm.config;

import lombok.Data;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "redisson")
//该值为空 配置不生效
@ConditionalOnProperty("redisson.password")
@Data
public class RedissionConfiguration {

    private int timeout = 3000;

    private String address;

    private String password;

    private int database = 0;

    private int connectionPoolSize = 64;

    private int connectionMinimumIdleSize=10;

    private int slaveConnectionPoolSize = 250;

    private int masterConnectionPoolSize = 250;

    private String[] sentinelAddresses;

    private String masterName;
}
