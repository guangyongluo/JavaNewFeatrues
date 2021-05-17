package com.vilin.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * 一、通道(Channel)：用于源节点和目标节点的连接。在Java NIO中负责缓冲区数据的传输。通道(Channel)本身不存储
 * 数据，因此需要配合缓冲区进行传输。
 * <p>
 * 二、通道的主要实现类：
 * java.nio.channels.Channel接口：
 * |--FileChannel
 * |--SocketChannel
 * |--ServerSocketChannel
 * |--DatagramChannel
 * <p>
 * 三、获取通道
 * 1、Java针对支持通道的类提供了getChannel()方法；
 * 本地IO
 * FileOutputStream/FileInputStream
 * RandomAccessFile
 * 网络IO
 * Socket
 * ServerSocket
 * DatagramSocket
 * 2、在JDK1.7中的NIO.2针对各个通道提供了静态的open方法
 * 3、在JDK1.7中的NIO.2的Files工具类的newByteChannel()
 * <p>
 * 四、通道之间的数据传输
 * transferFrom
 * transferTo
 *
 * 五、分数Scatter与聚集Gather
 */

public class TestChannel {

    public static void main(String[] args) {
        test3();
    }

    //通道之间的数据传输(直接缓冲区)
    public static void test3() {
        FileChannel inChannel = null;
        FileChannel outChannel = null;
        try {
            inChannel = FileChannel.open(Paths.get("target/classes/1.avi"), StandardOpenOption.READ);
            outChannel = FileChannel.open(Paths.get("target/classes/2.avi"), StandardOpenOption.WRITE, StandardOpenOption.READ, StandardOpenOption.CREATE);

            inChannel.transferTo(0, inChannel.size(), outChannel);


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

            if (outChannel != null) {
                try {
                    outChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //使用直接缓冲区完成文件的复制(内存映射文件)
    public static void test2() {
        long start = System.currentTimeMillis();

        FileChannel inChannel = null;
        FileChannel outChannel = null;
        try {
            inChannel = FileChannel.open(Paths.get("target/classes/1.avi"), StandardOpenOption.READ);
            outChannel = FileChannel.open(Paths.get("target/classes/2.avi"), StandardOpenOption.WRITE, StandardOpenOption.READ, StandardOpenOption.CREATE);

            //内存映射文件
            MappedByteBuffer inMappedByteBuffer = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, inChannel.size());
            MappedByteBuffer outMappedByteBuffer = outChannel.map(FileChannel.MapMode.READ_WRITE, 0, inChannel.size());

            //直接对缓冲区进行数据读写
            byte[] buffer = new byte[inMappedByteBuffer.limit()];
            inMappedByteBuffer.get(buffer);
            outMappedByteBuffer.put(buffer);
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

            if (outChannel != null) {
                try {
                    outChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        long end = System.currentTimeMillis();
        System.out.println("直接缓冲区完成文件复制耗时：" + (end - start));
    }


    //利用通道完成文件的复制
    public static void test1() {

        FileInputStream fis = null;
        FileOutputStream fos = null;
        FileChannel fisChannel = null;
        FileChannel fosChannel = null;

        try {
            fis = new FileInputStream("target/classes/1.jpg");
            fos = new FileOutputStream("2.jpg");

            //1.获取通道
            fisChannel = fis.getChannel();
            fosChannel = fos.getChannel();

            //2.分配指定缓冲区大小
            ByteBuffer buffer = ByteBuffer.allocate(1024);

            //3.将通道中的数据存入缓冲区
            while (fisChannel.read(buffer) != -1) {
                buffer.flip();
                //4.将缓冲区的数据写入通道
                fosChannel.write(buffer);
                buffer.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fisChannel != null) {
                try {
                    fisChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fosChannel != null) {
                try {
                    fosChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
