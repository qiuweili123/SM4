import com.java.io.DownUtil;
import org.junit.Test;

import java.io.RandomAccessFile;


public class TestIO {

    /**
     * main(这里用一句话描述这个方法的作用)
     *
     * @param @param  args
     * @param @throws Exception    设定文件
     * @return void    返回类型
     * @throws
     * @Title: main
     * @Description: 多线程下载文件
     */


    public static void main(String args[]) throws Exception {
        final DownUtil downUtil = new DownUtil("http://mirror.bit.edu.cn/eclipse/technology/epp/downloads/release/luna/SR2/eclipse-jee-luna-SR2-win32-x86_64.zip", "d:/eclipse-jee-luna-SR2-win32-x86_64.zip", 5);
        long start = System.currentTimeMillis();
        downUtil.download();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (downUtil.getCompleteRate() < 1) {
                    System.out.println("已完成:" + downUtil.getCompleteRate());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();

                    }

                }
            }
        }).start();
        System.out.println("end=" + (System.currentTimeMillis() - start));
    }

    @Test
    public void testWriteRontomFile() throws Exception {
        // 预分配文件所占的磁盘空间，磁盘中会创建一个指定大小的文件
        //1、首先在磁盘创建指定大小 的文件
        RandomAccessFile raf = new RandomAccessFile("D://abc.txt", "rw");
        raf.setLength(1024 * 1024); // 预分配 1M 的文件空间
        raf.close();
        String content = "这就是测试的内容！";
        //2、读写方式打开文件，并写入内容
        raf = new RandomAccessFile("D://abc.txt", "rw");
        raf.seek(1024 * 1);
        raf.writeChars(content);
        // raf.write(content.getBytes());
    }

}
