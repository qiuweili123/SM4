import com.properties.test.PropertiesUtils;

/**
 * All rights Reserved, Designed By Letv
 *
 * @Title: TestProperties.java
 * @Package
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: lenovo
 * @date: 2016年8月8日 上午10:40:27
 * @version
 */

/**
 * @author lenovo
 * @create time:2016年8月8日上午10:40:27
 * @Description:TODO(这里用一句话描述这个类的作用)
 */
public class TestProperties {

    /**
     * @param args
     * @Description:TODO(这里用一句话描述这个方法的作用)
     * @return: void
     * @author:lenovo 2016年8月8日上午10:40:27
     * @update1:updater:lenovo updatetime:2016年8月8日上午10:40:27 TODO(描述修改内容)
     */
    public static void main(String[] args) {
        //以下每次都load文件
        System.out.println(PropertiesUtils.getCurrentServer("servers"));
        System.out.println(PropertiesUtils.getCurrentServer("servers"));
        //PropertiesUtils增加了对proprties的判断，不会每次都load
        System.out.println(PropertiesUtils.getServer("servers"));
        System.out.println(PropertiesUtils.getServer("servers"));
    }

}
