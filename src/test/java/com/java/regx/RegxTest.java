/**
 * All rights Reserved, Designed By Letv
 *
 * @Title: RegxTest.java
 * @Package com.java.regx
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: lenovo
 * @date: 2016年7月1日 上午9:25:13
 * @version
 */
package com.java.regx;


import org.apache.commons.lang3.StringUtils;

/**
 * @author lenovo
 * @create time:2016年7月1日上午9:25:13
 * @Description:正则表达式
 */
public class RegxTest {

    /**
     * @param args
     * @Description:TODO(这里用一句话描述这个方法的作用)
     * @return: void
     * @author:lenovo 2016年7月1日上午9:25:13
     * @update1:updater:lenovo updatetime:2016年7月1日上午9:25:13 TODO(描述修改内容)
     */
    public static void main(String[] args) {
    /*    String str = "replica-10.182.63.85:1070-0000000068";
        String regex = "(\\d+\\.\\d+\\.\\d+\\.\\d+)\\:+(\\d+)\\:*(\\d*)";
	    Pattern p = Pattern.compile(regex);  
	    Matcher m = p.matcher(str);  
	    while (m.find()) {  
	    	 System.out.println("ip:"+m.group(1));  
	    	  System.out.println("port:"+m.group(2));  
	    	  System.out.println("port:"+"".equals(m.group(3)));
	    }      */
//		String  str ="com/letv/shop/controller/DemoController.class";
//		String regex = "*\\/controller\\/.\\/.*Controller*.class";
//		 Pattern p = Pattern.compile(regex);  
//		 p.matcher(regex);

        testMatchSpecal();
    }

    public static void test1() {
        //查找以Java开头,任意结尾的字符串
        //  Pattern pattern = Pattern.compile("^com\\/+\\/controller\\/\\/(\\w+)Controller\\.class$");
        //java.util.regex.Pattern pattern = java.util.regex.Pattern.compile("^com\\/+(.+)\\/controller\\/*.*\\/(\\w+)Controller\\.class$");
        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile("^com\\/+(.+)\\/controller\\/*.*\\/[a-zA-Z]+Controller\\.class$");
        //Matcher matcher = pattern.matcher("com/s/controller/a/aController.class");
        String str = "com/letv/shop/service/DemoService.class";
        java.util.regex.Matcher matcher = pattern.matcher(str);

        boolean b = matcher.matches();
        //当条件满足时，将返回true，否则返回false
        System.out.println(b);
    }

    public static void testReplace() {
        String str = "@1 and @2  and @3 and @4 and @10 and   @10";
        System.out.println(str.replace("@1", "aa"));
        System.out.println(StringUtils.replaceOnce(str, "@1", "aa"));
    }

    /**
     * 以上均是能够全部替换，不同于replace，replaceall可以支持正则
     */
    public static void testReplaceAll() {
        String str = "@1 and @2  and @3 and @4 and @10 and @10";
        //边界替换
        System.out.println(str.replaceAll("@1\\b", "aa"));
    }


    /**
     * 正则包含一个具体的词
     */
    public static void testMatchSpecal() {
        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile("sink.*hive.*");
        //Matcher matcher = pattern.matcher("com/s/controller/a/aController.class");
        String str = "sink.redis-hivedd";
        java.util.regex.Matcher matcher = pattern.matcher(str);

        boolean b = matcher.matches();
        //当条件满足时，将返回true，否则返回false
        System.out.println(b);
    }

}
