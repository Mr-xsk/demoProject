package com.example.nettyproject.netty_adv.client;

import com.example.nettyproject.netty_adv.kryocodec.KryoDecoder;
import com.example.nettyproject.netty_adv.kryocodec.KryoEncoder;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.timeout.ReadTimeoutHandler;

/**
 * @author Mark老师
 * 类说明：客户端Handler的初始化
 */
public class ClientInit extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        //ch.pipeline().addLast(new LoggingHandler(LogLevel.INFO));
        /*粘包半包问题*/
        ch.pipeline().addLast(new LengthFieldBasedFrameDecoder(65535,
                0,2,0,
                2));
        ch.pipeline().addLast(new LengthFieldPrepender(2));

        /*序列化相关*/
        ch.pipeline().addLast(new KryoDecoder());
        ch.pipeline().addLast(new KryoEncoder());
        //ch.pipeline().addLast(new LoggingHandler(LogLevel.INFO));
        /*处理心跳超时*/
        ch.pipeline().addLast(new ReadTimeoutHandler(15));

        ch.pipeline().addLast(new LoginAuthReqHandler());
        ch.pipeline().addLast(new HeartBeatReqHandler());
    }
}
