package com.xsk.rabbitmq.direct;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.xsk.rabbitmq.RabbitMQUtil;

import java.util.HashMap;
import java.util.Map;

public class Sender {

    private static final String QUEUE_NAME = "hello";

    public static void main(String[] args) throws Exception {
        Connection connection = RabbitMQUtil.getConnection();
        Channel channel = connection.createChannel();
        /**
         * 声明队列会在服务端自动创建
         * String queue 队列名字
         * boolean durable  持久化
         * boolean exclusive  独占队列
         * boolean autoDelete  自动删除
         */
//        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        Map<String,Object> params = new HashMap<>();
        params.put("x-queue-type","quorum");
        //声明Quorum队列的方式就是添加一个x-queue-type参数，指定为quorum。默认是classic
        channel.queueDeclare(QUEUE_NAME, true, false, false, params);

//        params.put("x-queue-type","stream");
//        params.put("x-max-length-bytes", 20_000_000_000L); // maximum stream size: 20 GB
//        params.put("x-stream-max-segment-size-bytes", 100_000_000); // size of segment files: 100 MB
//        channel.queueDeclare(QUEUE_NAME, true, false, false, params);

        String message = "Hello World!333";
        channel.basicPublish("", QUEUE_NAME, null, message.getBytes("UTF-8"));
        System.out.println(" [x] Sent '" + message + "'");

        channel.close();
        connection.close();
    }
}
