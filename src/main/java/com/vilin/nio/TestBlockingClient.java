package com.vilin.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * 一、使用NIO完成网络通信的三个核心：
 * <p>
 * 1. 通道(Channel):负责连接
 * <p>
 * java.nio.channels.Channel接口:
 * |--SelectableChannel
 * |--SocketChannel
 * |--ServerSocketChannel
 * |--DatagramChannel
 * <p>
 * |--Pipe.SinkChannel
 * |--Pipe.SourceChannel
 * <p>
 * 2. 缓冲区(Buffer):负责数据的存取
 * <p>
 * 3.选择器(Selector):是SelectableChannel的多路复用器。用于监控SelectableChannel的IO状况
 */

public class TestBlockingClient {

    public static void main(String[] args) {
        client();
    }

    //客户端
    public static void client() {
        SocketChannel sChannel = null;
        FileChannel inChannel = null;
        try {
            //1.获取通道
            sChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9898));
            inChannel = FileChannel.open(Paths.get("target/classes/1.jpg"), StandardOpenOption.READ);

            //2.分配指定大小的缓冲区
            ByteBuffer buf = ByteBuffer.allocate(1024);

            //3.读取本地文件，并发送到服务器
            while (inChannel.read(buf) != -1) {
                buf.flip();
                sChannel.write(buf);
                buf.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inChannel != null) {
                try {
                    inChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (sChannel != null) {
                try {
                    sChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
