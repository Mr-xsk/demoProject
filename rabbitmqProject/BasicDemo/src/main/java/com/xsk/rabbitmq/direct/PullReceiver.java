package com.xsk.rabbitmq.direct;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.GetResponse;
import com.xsk.rabbitmq.RabbitMQUtil;

import java.util.HashMap;
import java.util.Map;

public class PullReceiver {
    
    private static final String QUEUE_NAME = "hello";

    public static void main(String[] args) throws Exception {
        Connection connection = RabbitMQUtil.getConnection();
        Channel channel = connection.createChannel();
//        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        //每次只确认一条消息
        channel.basicQos(1);

        Map<String,Object> params = new HashMap<>();
		params.put("x-queue-type","quorum");
        //声明Quorum队列的方式就是添加一个x-queue-type参数，指定为quorum。默认是classic
		channel.queueDeclare(QUEUE_NAME, true, false, false, params);

        params.put("x-queue-type","stream");
        params.put("x-max-length-bytes", 20_000_000_000L); // maximum stream size: 20 GB
        params.put("x-stream-max-segment-size-bytes", 100_000_000); // size of segment files: 100 MB
        params.put("x-stream-offset", "first");
        channel.queueDeclare(QUEUE_NAME, true, false, false, params);

        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
        GetResponse response = channel.basicGet(QUEUE_NAME, false);
        if (null != response) System.out.println(new String(response.getBody(), "UTF-8"));

        GetResponse response2 = channel.basicGet(QUEUE_NAME, false);
        if (null != response2) System.out.println(new String(response2.getBody(), "UTF-8"));

        channel.close();
        connection.close();
    }
}
