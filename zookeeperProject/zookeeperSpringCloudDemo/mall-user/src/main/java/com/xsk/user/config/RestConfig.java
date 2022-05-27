package com.xsk.user.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestConfig {
    
    @Bean
    @LoadBalanced  //客户端负载均衡
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
