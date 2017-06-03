package com.java.io;

import java.io.RandomAccessFile;

/**
 * @author Comsys-liqiuwei
 * @ClassName: RandomAccessFileTest
 * @Description: RandomAccessFile是Java中输入，输出流体系中功能最丰富的文件内容访问类，它提供很多方法来操作文件，包括读写支持，
 * 与普通的IO流相比，它最大的特别之处就是支持任意访问的方式，程序可以直接跳到任意地方来读写数据。
 * 如果我们只希望访问文件的部分内容，而不是把文件从头读到尾，使用RandomAccessFile将会带来更简洁的代码以及更好的性能。
 * RandomAccess直译过来是随机访问 ,实际应该是任意访问的意思
 * @date 2015年5月31日 下午6:36:07
 */
public class RandomAccessFileTest {
    /**
     * 读的方法
     *
     * @param path   文件路径
     * @param pointe 指针位置
     **/
    public static void randomRed(String path, int pointe) {
        try {
            //RandomAccessFile raf=new RandomAccessFile(new File("D:\\3\\test.txt"), "r");  
            /**
             * model各个参数详解 
             * r 代表以只读方式打开指定文件 
             * rw 以读写方式打开指定文件 
             * rws 读写方式打开，并对内容或元数据都同步写入底层存储设备 
             * rwd 读写方式打开，对文件内容的更新同步更新至底层存储设备 
             *
             * **/
            RandomAccessFile raf = new RandomAccessFile(path, "r");
            //获取RandomAccessFile对象文件指针的位置，初始位置是0  
            System.out.println("RandomAccessFile文件指针的初始位置:" + raf.getFilePointer());
            raf.seek(pointe);//移动文件指针位置  
            byte[] buff = new byte[1024];
            //用于保存实际读取的字节数  
            int hasRead = 0;
            //循环读取  
            while ((hasRead = raf.read(buff)) > 0) {
                //打印读取的内容,并将字节转为字符串输入  
                System.out.println(new String(buff, 0, hasRead));

            }


        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    /**
     * 追加方式
     * 写的方法
     *
     * @param path 文件路径
     ***/
    public static void randomWrite(String path) {
        try {
            /**以读写的方式建立一个RandomAccessFile对象**/
            RandomAccessFile raf = new RandomAccessFile(path, "rw");

            //将记录指针移动到文件最后  
            raf.seek(raf.length());
            raf.write("我是追加的 \r\n".getBytes());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
