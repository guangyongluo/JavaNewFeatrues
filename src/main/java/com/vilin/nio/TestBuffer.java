package com.vilin.nio;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

/**
 * 一、缓冲区(buffer):在Java NIO中负责数据的存取。缓冲区就是数组，用于存取不同的数据类型的数据。
 * 除了boolean以外，根据数据类型的不同提供了相应类型的缓存区：
 * ByteBuffer
 * CharBuffer
 * ShortBuffer
 * IntBuffer
 * LongBuffer
 * FloatBuffer
 * DoubleBuffer
 *
 * 上述类型的管理方式基本一致，通过allocate()获取缓冲区
 *
 * 二、缓冲区存取数据的核心方法：
 *
 * put(): 存入数据到缓冲区；
 * get(): 获取缓冲区中数据。
 *
 * 三、缓冲区中的四个核心属性：
 *
 *  capacity: 容量，表示缓冲区中最大存取数据的容量，一旦声明不能改变；
 *  limit：界限，表示缓冲区中可以操作数据的大小(limit后面的数据不能读写)；
 *  position：位置，表示缓冲区中正在操作数据的位置；
 *  mark: 标记，可以记录当前position的位置，可以通过reset回到记录的position位置
 *
 *  0 <= mark <= position <= limit <= capacity
 */
public class TestBuffer {

    public static void main(String[] args) {
        test2();
    }

    public static void test(){
        String str = "abcde";

        //1.分配一个指定大小的缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        System.out.println("======allocate()以后=======");
        System.out.println("buffer.capacity() = " + buffer.capacity());
        System.out.println("buffer.limit() = " + buffer.limit());
        System.out.println("buffer.position() = " + buffer.position());

        //2.使用put()方法存入数据到缓冲区中去
        buffer.put(str.getBytes());

        System.out.println("======put()以后=======");
        System.out.println("buffer.capacity() = " + buffer.capacity());
        System.out.println("buffer.limit() = " + buffer.limit());
        System.out.println("buffer.position() = " + buffer.position());

        //3.切换到读取数据的模式
        buffer.flip();

        System.out.println("======flip()以后=======");
        System.out.println("buffer.capacity() = " + buffer.capacity());
        System.out.println("buffer.limit() = " + buffer.limit());
        System.out.println("buffer.position() = " + buffer.position());

        //4.利用get()方法读取数据到字节数组
        byte[] bytes = new byte[buffer.limit()];
        buffer.get(bytes);
        System.out.println(new String(bytes, 0, bytes.length));

        System.out.println("======get()以后=======");
        System.out.println("buffer.capacity() = " + buffer.capacity());
        System.out.println("buffer.limit() = " + buffer.limit());
        System.out.println("buffer.position() = " + buffer.position());

        //5. rewind方法，可重复读数据

        buffer.rewind();
        System.out.println("======rewind()以后=======");
        System.out.println("buffer.capacity() = " + buffer.capacity());
        System.out.println("buffer.limit() = " + buffer.limit());
        System.out.println("buffer.position() = " + buffer.position());

        //6. clear方法清空缓冲区，缓冲区中的数据依然存在，数据处于被遗忘状态；

        buffer.clear();
        System.out.println("======clear()以后=======");
        System.out.println("buffer.capacity() = " + buffer.capacity());
        System.out.println("buffer.limit() = " + buffer.limit());
        System.out.println("buffer.position() = " + buffer.position());

        System.out.println((char)buffer.get());
    }

    public static void test2(){
        String str = "abcde";

        ByteBuffer buffer = ByteBuffer.allocate(1024);
        buffer.put(str.getBytes());

        buffer.flip();

        byte[] bytes = new byte[buffer.limit()];
        buffer.get(bytes, 0, 2);
        System.out.println(new String(bytes, 0, bytes.length));
        System.out.println("position = " + buffer.position());

        // mark：标记当前的position位置
        buffer.mark();
        buffer.get(bytes, 2, 2);
        System.out.println(new String(bytes, 0, bytes.length));
        System.out.println("position = " + buffer.position());

        // reset:将缓冲区的position位子设置成之前mark记录的位置
        buffer.reset();
        System.out.println("position = " + buffer.position());

        //判断缓冲区中是否还有剩余的数据
        if(buffer.hasRemaining()){
            //获取缓冲区中可以操作的数量
            System.out.println(buffer.remaining());
        }

    }
}
