package com.example.nettyproject.nio.difficulty.nio.writeable;

import static com.example.nettyproject.nio.difficulty.Const.DEFAULT_PORT;

/**
 * @author Mark老师
 * 类说明：nio通信服务端
 */
public class NioServerWritable {
    private static NioServerHandleWriteable nioServerHandle;

    public static void start(){

    }
    public static void main(String[] args){
        nioServerHandle = new NioServerHandleWriteable(DEFAULT_PORT);
        new Thread(nioServerHandle,"NIO_Server").start();
    }

}
