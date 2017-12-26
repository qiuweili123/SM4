package com.commons.test;


import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by lenovo on 2017/8/29.
 */
public class TestStringUtils {

    public static void main(String[] args) {
        TestStr();
    }

    public static void TestStr() {
//null 和 ""操作~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
//判断是否Null 或者 ""
        System.out.println(StringUtils.isEmpty(null));
        System.out.println(StringUtils.isNotEmpty(null));
//判断是否null 或者 "" 去空格~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        System.out.println(StringUtils.isBlank(" "));
        System.out.println(StringUtils.isNotBlank(null));
//去空格.Null返回null~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        System.out.println(StringUtils.trim(null));
//去空格，将Null和"" 转换为Null
        System.out.println(StringUtils.trimToNull(""));
//去空格，将NULL 和 "" 转换为""
        System.out.println(StringUtils.trimToEmpty(null));
//可能是对特殊空格符号去除？？
        System.out.println(StringUtils.strip("大家好 啊 \t"));
//同上，将""和null转换为Null
        System.out.println(StringUtils.stripToNull(" \t"));
//同上，将""和null转换为""
        System.out.println(StringUtils.stripToEmpty(null));
//将""或者Null 转换为 ""
        System.out.println(StringUtils.defaultString(null));
//仅当字符串为Null时 转换为指定的字符串(二参数)
        System.out.println(StringUtils.defaultString("", "df"));
//当字符串为null或者""时，转换为指定的字符串(二参数)
        System.out.println(StringUtils.defaultIfEmpty(null, "sos"));
//去空格.去字符~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
//如果第二个参数为null去空格(否则去掉字符串2边一样的字符，到不一样为止)
        System.out.println(StringUtils.strip("fsfsdf", "f"));
//如果第二个参数为null只去前面空格(否则去掉字符串前面一样的字符，到不一样为止)
        System.out.println(StringUtils.stripStart("ddsuuu ", "d"));
//如果第二个参数为null只去后面空格，(否则去掉字符串后面一样的字符，到不一样为止)
        System.out.println(StringUtils.stripEnd("dabads", "das"));
//对数组没个字符串进行去空格。
//ArrayToList(StringUtils.stripAll(new String[]{" 中华 ", "民 国 ", "共和 "}));
//如果第二个参数为null.对数组每个字符串进行去空格。(否则去掉数组每个元素开始和结尾一样的字符)
//ArrayToList(StringUtils.stripAll(new String[]{" 中华 ", "民 国", "国共和国"}, "国"));
//查找,判断~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
//判断2个字符串是否相等相等,Null也相等
        System.out.println(StringUtils.equals(null, null));
//不区分大小写比较
        System.out.println(StringUtils.equalsIgnoreCase("abc", "ABc"));
//查找，不知道怎么弄这么多查找，很多不知道区别在哪？费劲~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
//普通查找字符，如果一参数为null或者""返回-1
        System.out.println(StringUtils.indexOf(null, "a"));
//从指定位置(三参数)开始查找，本例从第2个字符开始查找k字符
        System.out.println(StringUtils.indexOf("akfekcd中华", "k", 2));
//未发现不同之处
        System.out.println(StringUtils.ordinalIndexOf("akfekcd中华", "k", 2));
//查找,不区分大小写
        System.out.println(StringUtils.indexOfIgnoreCase("adfs", "D"));
//从指定位置(三参数)开始查找,不区分大小写
        System.out.println(StringUtils.indexOfIgnoreCase("adfs", "a", 3));
//从后往前查找
        System.out.println(StringUtils.lastIndexOf("adfas", "a"));
//未理解,此结果为2
        System.out.println(StringUtils.lastIndexOf("d饿abasdafs我", "a", 3));
//未解,此结果为-1
        System.out.println(StringUtils.lastOrdinalIndexOf("yksdfdht", "f", 2));
//从后往前查，不区分大小写
        System.out.println(StringUtils.lastIndexOfIgnoreCase("sdffet", "E"));
//未解,此结果为1
        System.out.println(StringUtils.lastIndexOfIgnoreCase("efefrfs看", "F", 2));
//检查是否查到，返回boolean,null返回假
        System.out.println(StringUtils.contains("sdf", "dg"));
//检查是否查到，返回boolean,null返回假,不区分大小写
        System.out.println(StringUtils.containsIgnoreCase("sdf", "D"));
//检查是否有含有空格,返回boolean
        //  System.out.println(StringUtils.containsWhitespace(" d"));
//查询字符串跟数组任一元素相同的第一次相同的位置
        System.out.println(StringUtils.indexOfAny("absfekf", new String[]{"f", "b"}));
//查询字符串中指定字符串(参数二)出现的次数
        System.out.println(StringUtils.indexOfAny("afefes", "e"));
//查找字符串中是否有字符数组中相同的字符，返回boolean
        System.out.println(StringUtils.containsAny("asfsd", new char[]{'k', 'e', 's'}));
//未理解与lastIndexOf不同之处。是否查到，返回boolean
        System.out.println(StringUtils.containsAny("啡f咖啡", "咖"));
//未解
        System.out.println(StringUtils.indexOfAnyBut("seefaff", "af"));
//判断字符串中所有字符，都是出自参数二中。
        System.out.println(StringUtils.containsOnly("中华华", "华"));
//判断字符串中所有字符，都是出自参数二的数组中。
        System.out.println(StringUtils.containsOnly("中华中", new char[]{'中', '华'}));
//判断字符串中所有字符，都不在参数二中。
        System.out.println(StringUtils.containsNone("中华华", "国"));
//判断字符串中所有字符，都不在参数二的数组中。
        System.out.println(StringUtils.containsNone("中华中", new char[]{'中'}));
//从后往前查找字符串中与字符数组中相同的元素第一次出现的位置。本例为4
        System.out.println(StringUtils.lastIndexOfAny("中国人民共和国", new String[]{"国人", "共和"}));
//未发现与indexOfAny不同之处 查询字符串中指定字符串(参数二)出现的次数
        System.out.println(StringUtils.countMatches("中国人民共和中国", "中国"));
//检查是否CharSequence的只包含Unicode的字母。空将返回false。一个空的CharSequence（长（）= 0）将返回true
        System.out.println(StringUtils.isAlpha("这是干什么的2"));
//检查是否只包含Unicode的CharSequence的字母和空格（''）。空将返回一个空的CharSequence假（长（）= 0）将返回true。
        System.out.println(StringUtils.isAlphaSpace("NBA直播 "));
//检查是否只包含Unicode的CharSequence的字母或数字。空将返回false。一个空的CharSequence（长（）= 0）将返回true。
        System.out.println(StringUtils.isAlphanumeric("NBA直播"));
//如果检查的Unicode CharSequence的只包含字母，数字或空格（''）。空将返回false。一个空的CharSequence（长（）= 0）将返回true。
        System.out.println(StringUtils.isAlphanumericSpace("NBA直播"));
//检查是否只包含ASCII可CharSequence的字符。空将返回false。一个空的CharSequence（长（）= 0）将返回true。
        System.out.println(StringUtils.isAsciiPrintable("NBA直播"));
//检查是否只包含数值。
        System.out.println(StringUtils.isNumeric("NBA直播"));
//检查是否只包含数值或者空格
        System.out.println(StringUtils.isNumericSpace("33 545"));
//检查是否只是空格或""。
        System.out.println(StringUtils.isWhitespace(" "));
//检查是否全是英文小写。
        System.out.println(StringUtils.isAllLowerCase("kjk33"));
//检查是否全是英文大写。
        System.out.println(StringUtils.isAllUpperCase("KJKJ"));
//交集操作~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
//去掉参数2字符串中在参数一中开头部分共有的部分，结果为:人民共和加油
        System.out.println(StringUtils.difference("中国加油", "中国人民共和加油"));
//统计2个字符串开始部分共有的字符个数
        System.out.println(StringUtils.indexOfDifference("ww.taobao", "www.taobao.com"));
//统计数组中各个元素的字符串开始都一样的字符个数
        System.out.println(StringUtils.indexOfDifference(new String[]{"中国加油", "中国共和", "中国人民"}));
//取数组每个元素共同的部分字符串
        System.out.println(StringUtils.getCommonPrefix(new String[]{"中国加油", "中国共和", "中国人民"}));
//统计参数一中每个字符与参数二中每个字符不同部分的字符个数
        System.out.println(StringUtils.getLevenshteinDistance("中国共和发国人民", "共和国"));
//判断开始部分是否与二参数相同
        System.out.println(StringUtils.startsWith("中国共和国人民", "中国"));
//判断开始部分是否与二参数相同。不区分大小写
        System.out.println(StringUtils.startsWithIgnoreCase("中国共和国人民", "中国"));
//判断字符串开始部分是否与数组中的某一元素相同
        System.out.println(StringUtils.startsWithAny("abef", new String[]{"ge", "af", "ab"}));
//判断结尾是否相同
        System.out.println(StringUtils.endsWith("abcdef", "def"));
//判断结尾是否相同，不区分大小写
        System.out.println(StringUtils.endsWithIgnoreCase("abcdef", "Def"));
//字符串截取~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
//截取指定位置的字符，null返回null.""返回""
        System.out.println(StringUtils.substring("国民党", 2));
//截取指定区间的字符
        System.out.println(StringUtils.substring("中国人民共和国", 2, 4));
//从左截取指定长度的字符串
        System.out.println(StringUtils.left("说点什么好呢", 3));
//从右截取指定长度的字符串
        System.out.println(StringUtils.right("说点什么好呢", 3));
//从第几个开始截取，三参数表示截取的长度
        System.out.println(StringUtils.mid("说点什么好呢", 3, 2));
//截取到等于第二个参数的字符串为止
        System.out.println(StringUtils.substringBefore("说点什么好呢", "好"));
//从左往右查到相等的字符开始，保留后边的，不包含等于的字符。本例：什么好呢
        System.out.println(StringUtils.substringAfter("说点什么好呢", "点"));
//这个也是截取到相等的字符，但是是从右往左.本例结果：说点什么好
        System.out.println(StringUtils.substringBeforeLast("说点什么好点呢", "点"));
//这个截取同上是从右往左。但是保留右边的字符
        System.out.println(StringUtils.substringAfterLast("说点什么好点呢？", "点"));
//截取查找到第一次的位置，和第二次的位置中间的字符。如果没找到第二个返回null。本例结果:2010世界杯在
        System.out.println(StringUtils.substringBetween("南非2010世界杯在南非，在南非", "南非"));
//返回参数二和参数三中间的字符串，返回数组形式
//ArrayToList(StringUtils.substringsBetween("[a][b][c]", "[", "]"));
//分割~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
//用空格分割成数组，null为null
//ArrayToList(StringUtils.split("中华 人民 共和"));
//以指定字符分割成数组
//ArrayToList(StringUtils.split("中华 ,人民,共和", ","));
//以指定字符分割成数组，第三个参数表示分隔成数组的长度，如果为0全体分割
//ArrayToList(StringUtils.split("中华 ：人民：共和", "：", 2));
//未发现不同的地方,指定字符分割成数组
//ArrayToList(StringUtils.splitByWholeSeparator("ab-!-cd-!-ef", "-!-"));
//未发现不同的地方,以指定字符分割成数组，第三个参数表示分隔成数组的长度
//ArrayToList(StringUtils.splitByWholeSeparator("ab-!-cd-!-ef", "-!-", 2));
//分割，但" "不会被忽略算一个元素,二参数为null默认为空格分隔
//ArrayToList(StringUtils.splitByWholeSeparatorPreserveAllTokens(" ab de fg ", null));
//同上，分割," "不会被忽略算一个元素。第三个参数代表分割的数组长度。
//ArrayToList(StringUtils.splitByWholeSeparatorPreserveAllTokens("ab de fg", null, 3));
//未发现不同地方,分割
//ArrayToList(StringUtils.splitPreserveAllTokens(" ab de fg "));
//未发现不同地方,指定字符分割成数组
//ArrayToList(StringUtils.splitPreserveAllTokens(" ab de fg ", null));
//未发现不同地方,以指定字符分割成数组，第三个参数表示分隔成数组的长度
//ArrayToList(StringUtils.splitPreserveAllTokens(" ab de fg ", null, 2));
//以不同类型进行分隔
//ArrayToList(StringUtils.splitByCharacterType("AEkjKr i39:。中文"));
//未解
//ArrayToList(StringUtils.splitByCharacterTypeCamelCase("ASFSRules234"));
//拼接~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
//将数组转换为字符串形式
        //    System.out.println(StringUtils.concat(getArrayData()));
//拼接时用参数一得字符相连接.注意null也用连接符连接了
        //   System.out.println(StringUtils.concatWith(",", getArrayData()));
//也是拼接。未发现区别
        System.out.println(StringUtils.join(getArrayData()));
//用连接符拼接，为发现区别
        System.out.println(StringUtils.join(getArrayData(), ":"));
//拼接指定数组下标的开始(三参数)和结束(四参数,不包含)的中间这些元素，用连接符连接
        System.out.println(StringUtils.join(getArrayData(), ":", 1, 3));
//用于集合连接字符串.用于集合
        System.out.println(StringUtils.join(getListData(), ":"));
//移除，删除~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
//删除所有空格符
        System.out.println(StringUtils.deleteWhitespace(" s 中 你 4j"));
//移除开始部分的相同的字符
        System.out.println(StringUtils.removeStart("www.baidu.com", "www."));
//移除开始部分的相同的字符,不区分大小写
        System.out.println(StringUtils.removeStartIgnoreCase("www.baidu.com", "WWW"));
//移除后面相同的部分
        System.out.println(StringUtils.removeEnd("www.baidu.com", ".com"));
//移除后面相同的部分，不区分大小写
        System.out.println(StringUtils.removeEndIgnoreCase("www.baidu.com", ".COM"));
//移除所有相同的部分
        System.out.println(StringUtils.remove("www.baidu.com/baidu", "bai"));
//移除结尾字符为"\n", "\r", 或者 "\r\n".
        System.out.println(StringUtils.chomp("abcrabc\r"));
//也是移除，未解。去结尾相同字符
        System.out.println(StringUtils.chomp("baidu.com", "com"));
//去掉末尾最后一个字符.如果是"\n", "\r", 或者 "\r\n"也去除
        System.out.println(StringUtils.chop("wwe.baidu"));
//替换~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
//替换指定的字符，只替换第一次出现的
        System.out.println(StringUtils.replaceOnce("www.baidu.com/baidu", "baidu", "hao123"));
//替换所有出现过的字符
        System.out.println(StringUtils.replace("www.baidu.com/baidu", "baidu", "hao123"));
//也是替换，最后一个参数表示替换几个
        System.out.println(StringUtils.replace("www.baidu.com/baidu", "baidu", "hao123", 1));
//这个有意识，二三参数对应的数组，查找二参数数组一样的值，替换三参数对应数组的值。本例:baidu替换为taobao。com替换为net
        System.out.println(StringUtils.replaceEach("www.baidu.com/baidu", new String[]{"baidu", "com"}, new String[]{"taobao", "net"}));
//同上，未发现不同
        System.out.println(StringUtils.replaceEachRepeatedly("www.baidu.com/baidu", new String[]{"baidu", "com"}, new String[]{"taobao", "net"}));
//这个更好，不是数组对应，是字符串参数二和参数三对应替换.(二三参数不对应的话，自己看后果)
        System.out.println(StringUtils.replaceChars("www.baidu.com", "bdm", "qo"));
//替换指定开始(参数三)和结束(参数四)中间的所有字符
        System.out.println(StringUtils.overlay("www.baidu.com", "hao123", 4, 9));
//添加，增加~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
//复制参数一的字符串，参数二为复制的次数
        System.out.println(StringUtils.repeat("ba", 3));
//复制参数一的字符串，参数三为复制的次数。参数二为复制字符串中间的连接字符串
        System.out.println(StringUtils.repeat("ab", "ou", 3));
//如何字符串长度小于参数二的值，末尾加空格补全。(小于字符串长度不处理返回)
        System.out.println(StringUtils.rightPad("海川", 4));
//字符串长度小于二参数，末尾用参数三补上，多于的截取(截取补上的字符串)
        System.out.println(StringUtils.rightPad("海川", 4, "河流啊"));
//同上在前面补全空格
        System.out.println(StringUtils.leftPad("海川", 4));
//字符串长度小于二参数，前面用参数三补上，多于的截取(截取补上的字符串)
        System.out.println(StringUtils.leftPad("海川", 4, "大家好"));
//字符串长度小于二参数。在两侧用空格平均补全（测试后面补空格优先）
        System.out.println(StringUtils.center("海川", 3));
//字符串长度小于二参数。在两侧用三参数的字符串平均补全（测试后面补空格优先）
        System.out.println(StringUtils.center("海川", 5, "流"));
//只显示指定数量(二参数)的字符,后面以三个点补充(参数一截取+三个点=二参数)
        System.out.println(StringUtils.abbreviate("中华人民共和国", 5));
//2头加点这个有点乱。本例结果: ...ijklmno
        System.out.println(StringUtils.abbreviate("abcdefghijklmno", 12, 10));
//保留指定长度，最后一个字符前加点.本例结果: ab.f
        System.out.println(StringUtils.abbreviateMiddle("abcdef", ".", 4));
//转换,刷选~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
//转换第一个字符为大写.如何第一个字符是大写原始返回
        System.out.println(StringUtils.capitalize("Ddf"));
//转换第一个字符为大写.如何第一个字符是大写原始返回
        System.out.println(StringUtils.uncapitalize("DTf"));
//反向转换，大写变小写，小写变大写
        System.out.println(StringUtils.swapCase("I am Jiang, Hello"));
//将字符串倒序排列
        System.out.println(StringUtils.reverse("中国人民"));
//根据特定字符(二参数)分隔进行反转
        System.out.println(StringUtils.reverseDelimited("中:国:人民", ':'));
    }


    //将数组转换为List
    private static void ArrayToList(String[] str) {
        System.out.println(Arrays.asList(str) + " 长度:" + str.length);
    }

    //获得集合数据
    private static List getListData() {
        List list = new ArrayList();
        list.add("你好");
        list.add(null);
        list.add("他好");
        list.add("大家好");
        return list;
    }

    //获得数组数据
    private static String[] getArrayData() {
        return (String[]) getListData().toArray(new String[0]);
    }

}
