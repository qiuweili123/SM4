import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.serializer.SerializeConfig;
import org.perf4j.LoggingStopWatch;
import org.perf4j.StopWatch;

import java.util.HashMap;
import java.util.Map;

/**
 * All rights Reserved, Designed By Letv
 *
 * @Title: TestFastJson.java
 * @Package
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: liqiuwei
 * @date: 2015年11月3日 下午3:21:57
 * @version
 */

/**
 * @author liqiuwei
 * @create time:2015年11月3日下午3:21:57
 * @Description:TODO(这里用一句话描述这个类的作用)
 */
public class TestFastJson {
    public static ParserConfig pc = new ParserConfig();
    public static SerializeConfig sc = new SerializeConfig();
    private static StopWatch stopWatch = new LoggingStopWatch(TestFastJson.class.getCanonicalName());

    public static void main(String[] args) {
        jsonToMap();
        // mapToJson();
    }

    public static void mapToJson() {
        stopWatch.start();

        Map<String, String> tmpMap = new HashMap();
        tmpMap.put("name", "zhangsan");
        tmpMap.put("sex", "男");
        String json = JSON.toJSONString(tmpMap);
        System.out.println("##json==" + json);
        stopWatch.lap("lap 1");
        stopWatch.stop();
    }

    public static void jsonToMap() {

        for (int i = 0; i < 10; i++) {
            Long startTime = System.currentTimeMillis();
            String str = "{\"sex\":\"男" + i + "\",\"name\":\"zhangsan\"}";
            Map map = JSON.parseObject(str, HashMap.class);
            System.out.println(i + "##" + (System.currentTimeMillis() - startTime));
        }

    }

}
