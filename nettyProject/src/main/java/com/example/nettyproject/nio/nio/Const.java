package com.example.nettyproject.nio.nio;

/**
 * @author Mark老师
 * 类说明：
 */
public class Const {
    public static int DEFAULT_PORT = 9000;
    public static String DEFAULT_SERVER_IP = "127.0.0.1";

    /*根据输入信息拼接出一个应答信息*/
    public static String response(String msg){
        return "Hello,"+msg+",Now is "+new java.util.Date(
                System.currentTimeMillis()).toString() ;
    }

}