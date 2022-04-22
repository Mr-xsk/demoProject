package com.example.nettyproject.netty_adv;

import cn.tuling.nettyadv.busivo.User;
import cn.tuling.nettyadv.busivo.UserContact;

import java.util.Scanner;

/**
 * @author Mark老师
 * 类说明：业务方如何调用Netty客户端演示
 */
public class BusiClient {
    public static void main(String[] args) throws Exception {
        NettyClient nettyClient = new NettyClient();
        new Thread(nettyClient).start();
        while(!nettyClient.isConnected()){
            synchronized (nettyClient){
                nettyClient.wait();
            }
        }
        System.out.println("网络通信已准备好，可以进行业务操作了........");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String msg = scanner.next();
            if (msg == null) {
                break;
            } else if ("q".equals(msg.toLowerCase())) {
                nettyClient.close();
                while(nettyClient.isConnected()){
                    synchronized (nettyClient){
                        nettyClient.wait();
                    }
                }
                scanner.close();
                System.exit(1);
            } else if("v".equals(msg.toLowerCase())){
                User user = new User();
                user.setAge(19);
                String userName = "mark";
                user.setUserName(userName);
                user.setId("No:1");
                user.setUserContact(
                        new UserContact(userName+"@tuling.com",
                                "133"));
                nettyClient.send(user);
            }
            else {
                nettyClient.send(msg);
            }
        }
    }
}
