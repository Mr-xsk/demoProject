package com.example.nettyproject.netty_adv.server;

import com.example.nettyproject.netty_adv.vo.MyMessage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Mark老师
 * 类说明：业务处理类
 */
public class ServerBusiHandler
        extends SimpleChannelInboundHandler<MyMessage> {
    private static final Logger LOG = LoggerFactory.getLogger(ServerBusiHandler.class);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MyMessage msg)
            throws Exception {
        LOG.info(msg.toString());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx)
            throws Exception {
        LOG.info(ctx.channel().remoteAddress()+" 主动断开了连接!");
    }

}
