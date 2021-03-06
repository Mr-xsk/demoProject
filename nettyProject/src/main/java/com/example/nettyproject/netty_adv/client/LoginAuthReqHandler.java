package com.example.nettyproject.netty_adv.client;

import com.example.nettyproject.netty_adv.vo.MessageType;
import com.example.nettyproject.netty_adv.vo.MyHeader;
import com.example.nettyproject.netty_adv.vo.MyMessage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Mark老师
 * 类说明：发起登录请求
 */
public class LoginAuthReqHandler extends ChannelInboundHandlerAdapter {

    private static final Logger LOG = LoggerFactory.getLogger(LoginAuthReqHandler.class);

    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        /*发出认证请求*/
        ctx.writeAndFlush(buildLoginReq());
    }

    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception {
        MyMessage message = (MyMessage) msg;
        /*是不是握手成功的应答*/
        if(message.getMyHeader()!=null
                &&message.getMyHeader().getType()== MessageType.LOGIN_RESP.value()){
            byte loginResult = (byte) message.getBody();
            if (loginResult != (byte) 0) {
                // 握手失败，关闭连接
                ctx.close();
            } else {
                LOG.info("Login is ok : " + message);
                ctx.fireChannelRead(msg);
            }
        }else{
            ctx.fireChannelRead(msg);
        }
    }

    private MyMessage buildLoginReq() {
        MyMessage message = new MyMessage();
        MyHeader myHeader = new MyHeader();
        myHeader.setType(MessageType.LOGIN_REQ.value());
        message.setMyHeader(myHeader);
        return message;
    }

    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        ctx.fireExceptionCaught(cause);
    }
}
