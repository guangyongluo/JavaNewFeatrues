package com.vilin.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Scanner;

public class TestNoBlockingNIOClient {

    public static void main(String[] args) {

        SocketChannel sChannel = null;


        try {
            //1.获取通道
            sChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9898));

            //2.切换非阻塞模式
            sChannel.configureBlocking(false);

            //3.分配指定大小的缓冲区
            ByteBuffer buf = ByteBuffer.allocate(1024);

            //4.发送数据给服务器
            Scanner scan = new Scanner(System.in);

            while (scan.hasNext()) {
                String str = scan.next();
                buf.put((LocalDateTime.now().toString() + "\n" + str).getBytes(StandardCharsets.UTF_8));
                buf.flip();
                sChannel.write(buf);
                buf.clear();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                sChannel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
}
