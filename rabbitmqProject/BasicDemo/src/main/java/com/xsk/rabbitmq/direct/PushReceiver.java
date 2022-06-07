package com.xsk.rabbitmq.direct;

import com.rabbitmq.client.*;
import com.xsk.rabbitmq.RabbitMQUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PushReceiver {

    private static final String QUEUE_NAME = "hello";

    //保持长连接，等待服务器推送的消费方式。
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

        Consumer myconsumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("========================");
                String routingKey = envelope.getRoutingKey();
                System.out.println("routingKey >"+routingKey);
                String contentType = properties.getContentType();
                System.out.println("contentType >"+contentType);
                long deliveryTag = envelope.getDeliveryTag();
                System.out.println("deliveryTag >"+deliveryTag);
                System.out.println("content:"+new String(body,"UTF-8"));
                // (process the message components here ...)
                //消息处理完后，进行答复。答复过的消息，服务器就不会再次转发。
                //没有答复过的消息，服务器会一直不停转发。
                channel.basicAck(deliveryTag, false);
            }
        };
        /**
         * autoAck为true则表示消息发送到该Consumer后就被Consumer消费掉了
         * 为false则会继续往其他Consumer转发
         * channel.basicConsume(String queue, boolean autoAck, Consumer callback);
         */
        channel.basicConsume(QUEUE_NAME, false, myconsumer);
        channel.close();
        connection.close();
    }
}
