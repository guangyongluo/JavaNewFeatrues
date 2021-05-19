package com.vilin.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class TestBlockingClient2 {

    public static void main(String[] args) {
        SocketChannel sChannel = null;
        FileChannel inChannel = null;
        try {
            sChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9898));

            inChannel = FileChannel.open(Paths.get("target/classes/1.jpg"), StandardOpenOption.READ);

            ByteBuffer buf = ByteBuffer.allocate(1024);

            while(inChannel.read(buf) != -1){
                buf.flip();
                sChannel.write(buf);
                buf.clear();
            }

            sChannel.shutdownOutput();

            //接受服务器的反馈
            int len = 0;

            while((len = sChannel.read(buf)) != -1) {
                buf.flip();
                System.out.println(new String(buf.array(), 0, len));
                buf.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inChannel != null){
                try {
                    inChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (sChannel != null){
                try {
                    sChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
