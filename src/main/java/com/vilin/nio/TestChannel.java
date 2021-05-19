package com.vilin.nio;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Map;
import java.util.Set;

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
 * <p>
 * 五、分数(Scatter)与聚集(Gather)
 * 分数读取(Scattering Reads) : 将通道中的数据分数到多个缓冲区中；
 * 聚集写入(Gathering writes) : 将多个缓冲区中的数据聚集到通道中；
 * <p>
 * 六、字符集：Charset
 * 编码：字符串 -> 字节数组
 * 解码：字节数组 -> 字符串
 */

public class TestChannel {

    public static void main(String[] args) throws CharacterCodingException {
        test6();
    }

    public static void test6() throws CharacterCodingException {
        Charset cs1 = Charset.forName("GBK");

        //获取编码器
        CharsetEncoder ce = cs1.newEncoder();

        //获取解码器
        CharsetDecoder cd = cs1.newDecoder();

        CharBuffer cBuf = CharBuffer.allocate(1024);

        cBuf.put("我是程序员");
        cBuf.flip();

        //编码
        ByteBuffer bBuf = ce.encode(cBuf);

        for(int i = 0; i < 10; i++){
            System.out.println(bBuf.get());
        }

        //解码
        bBuf.flip();
        CharBuffer cBuf2 = cd.decode(bBuf);
        System.out.println(cBuf2.toString());

        System.out.println("------------------------------");

        Charset cs2 = Charset.forName("GBK");
        bBuf.flip();
        CharBuffer cBuf3 = cs2.decode(bBuf);

        System.out.println(cBuf3.toString());

    }

    //字符集
    public static void test5() {
        Map<String, Charset> map = Charset.availableCharsets();
        Set<Map.Entry<String, Charset>> set = map.entrySet();
        for (Map.Entry<String, Charset> entry : set) {
            System.out.println(entry.getKey() + "=" + entry.getValue());
        }

    }

    //分散和聚集
    public static void test4() throws IOException {
        RandomAccessFile raf = new RandomAccessFile("1.txt", "rw");

        //1.获取通道
        FileChannel channel1 = raf.getChannel();

        //2.分配指定大小的缓冲区
        ByteBuffer buf1 = ByteBuffer.allocate(100);
        ByteBuffer buf2 = ByteBuffer.allocate(1024);

        //3.分散读取
        ByteBuffer[] bufs = {buf1, buf2};
        channel1.read(bufs);

        for (ByteBuffer byteBuffer : bufs) {
            byteBuffer.flip();
        }

        System.out.println(new String(bufs[0].array(), 0, bufs[0].limit()));
        System.out.println("---------------------");
        System.out.println(new String(bufs[1].array(), 0, bufs[1].limit()));

        //4.聚集写入
        RandomAccessFile raf2 = new RandomAccessFile("2.txt", "rw");
        FileChannel channel2 = raf2.getChannel();

        channel2.write(bufs);
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
