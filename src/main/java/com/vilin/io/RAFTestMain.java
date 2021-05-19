package com.vilin.io;

import java.io.IOException;
import java.io.RandomAccessFile;

public class RAFTestMain {
    public static void main(String[] args) throws IOException {
        RandomAccessFile raf = RAFTestFactory.getRAFWithModelR();
        System.out.println("raf.length()->获取文本内容长度:"+raf.length());
        System.out.println("raf.getFilePointer()->获取文本头指针:"+raf.getFilePointer());
        raf.seek(4);
        System.out.println("raf.getFilePointer()->第二次获取文本头指针:"+raf.getFilePointer());

        RandomAccessFile raf2 = RAFTestFactory.getRAFWithModelRW();
        raf2.write(1);
    }
}
