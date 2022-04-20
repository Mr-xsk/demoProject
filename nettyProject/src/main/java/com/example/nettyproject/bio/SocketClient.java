package com.example.nettyproject.bio;

import java.io.IOException;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class SocketClient {

    public static void main(String[] args) {
        Socket socket = null;
        try {
            socket = new Socket("127.0.0.1", 9000);
            socket.getOutputStream().write("HelloServer".getBytes(StandardCharsets.UTF_8));
            socket.getOutputStream().flush();
            System.out.println("向服务端发送数据结束");
            byte[] bytes = new byte[1024];
            System.out.println("接收到服务端的数据：" + new String(bytes));
            socket.getInputStream().read(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
