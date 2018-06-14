package com.allanvital.autoconfigure;

import com.allanvital.LeadershipLatch;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.Jedis;

@Configuration
@EnableConfigurationProperties(LeadershipProperties.class)
public class LeadershipAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnBean(value = Jedis.class)
    public LeadershipLatch leadershipLatch(Jedis jedis, LeadershipProperties leadershipProperties) {
        return new LeadershipLatch(jedis, leadershipProperties);
    }

}
