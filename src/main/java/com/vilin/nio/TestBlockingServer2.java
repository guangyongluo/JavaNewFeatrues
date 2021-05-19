package com.vilin.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class TestBlockingServer2 {

    public static void main(String[] args) {
        ServerSocketChannel ssChannel = null;

        FileChannel outChannel = null;

        SocketChannel sChannel = null;


        try {
            ssChannel = ServerSocketChannel.open();
            outChannel = FileChannel.open(Paths.get("2.jpg"), StandardOpenOption.WRITE, StandardOpenOption.CREATE);
            ssChannel.bind(new InetSocketAddress(9898));
            sChannel = ssChannel.accept();
            ByteBuffer buf = ByteBuffer.allocate(1024);

            while(sChannel.read(buf) != -1){
                buf.flip();
                outChannel.write(buf);
                buf.clear();
            }

            //发送反馈给客户端
            buf.put("服务器端收数据成功".getBytes());
            buf.flip();
            sChannel.write(buf);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //关闭通道
            if (sChannel != null){
                try {
                    sChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(outChannel != null){
                try {
                    outChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (ssChannel != null) {
                try {
                    ssChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }





}
