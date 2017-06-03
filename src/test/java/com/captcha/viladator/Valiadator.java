/**
 * All rights Reserved, Designed By Letv
 *
 * @Title: valiadator.java
 * @Package com.captcha.viladator
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: liqiuwei
 * @date: 2016年11月17日 上午10:25:45
 * @version
 */
package com.captcha.viladator;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import java.io.File;

/**
 * @author liqiuwei
 * @create time:2016年11月17日上午10:25:45
 * @Description:TODO(这里用一句话描述这个类的作用)
 */
public class Valiadator {
    public static void main(String[] args) {
        File imageFile = new File("D:/captcha/0.png");
        ITesseract instance = new Tesseract(); // JNA Interface Mapping
        instance.setDatapath("C:/Program Files (x86)/Tesseract-OCR");
        // ITesseract instance = new Tesseract1(); // JNA Direct Mapping

        try {
            String result = instance.doOCR(imageFile);
            System.out.println(result);
        } catch (TesseractException e) {
            System.err.println(e.getMessage());
        }
    }
}
