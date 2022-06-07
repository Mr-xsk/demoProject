package com.xsk.rocketmq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.cloud.stream.messaging.Source;

/**
 * @Author Mr.Xu
 * @description:
 * @Time 2022-06-07 17:14
 */
@EnableBinding({Source.class, Sink.class})
@SpringBootApplication
public class RocketMQSpringCloudApplication {

    public static void main(String[] args) {
        SpringApplication.run(RocketMQSpringCloudApplication.class, args);
    }
}
