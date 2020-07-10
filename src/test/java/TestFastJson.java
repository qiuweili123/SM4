import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.serializer.SerializeConfig;
import org.perf4j.LoggingStopWatch;
import org.perf4j.StopWatch;

import java.util.HashMap;
import java.util.List;
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

       // String msg="{`bd_info`:`{\\`client_ip\\`:\\`182.204.191.34\\`,\\`ua\\`:\\`Mozilla/5.0 (iPhone; CPU iPhone OS 12_4 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Mobile/15E148 MicroMessenger/7.0.12(0x17000c32) NetType/WIFI Language/zh_CN\\`,\\`tid\\`:\\`30625220181593479496.586182.204.191.34112679349216\\`,\\`cid\\`:\\`1593479478538-1736574-0389be69fb75f48-30518547\\`,\\`uid\\`:\\`33619574\\`,\\`sid\\`:\\`15934851651852496\\`,\\`platform\\`:\\`用户版M站\\`}`,`begin_check_time`:1593479496612,`biz_time`:1593479496606,`black_complete_time`:1593479496616,`data_complete_time`:1593479496613,`disposal_code`:9990,`end_check_time`:1593479496616,`ext_info`:`{\\`auth_id\\`:\\`211202198805040510\\`,\\`user_id\\`:33619574,\\`mobile\\`:\\`M20127483376\\`,\\`amout\\`:500,\\`invite_user_id\\`:[33679551,33679552],\\`invite_user_mobile\\`:[\\`M20130497280\\`]}`,`hit_count`:1,`hit_type`:1,`result_code`:200,`result_msg`:`成功`,`risk_no`:`73841874fd1976a805df6903f1b456a5`,`rule_complete_time`:0,`scene_code`:`activity_withdraw`,`sys_code`:`9b694a4fc4c2b144`,`uid`:`33619574`,`white_complete_time`:0} ";
      String msg="{\"date\":\"2020-07-03T16:18:54.009\",\"level\":\"INFO\",\"appName\":\"demm-app\",\"class\":\"com.ishansong.ops.rinet.client.RiskClient\",\"method\":\"finish\",\"line\":\"22\",\"message\":\"{`risk_no`:`201908929`} \"}\n";
        msg = msg.replaceAll("`", "'");
        Map<String,Object> map = JSON.parseObject(msg, HashMap.class);
        Map map1 = JSON.parseObject ((String) map.get("message"), HashMap.class);
        System.out.println("out=="+map1.get("risk_no"));
       // List<Integer> objects = JSON.parseArray(ids.get("invite_user_id").toString(), Integer.class);
        //System.out.println("##"+invite_user_id);

       // objects.stream().forEach(x-> System.out.println(x));
   /*     for (Map.Entry entry:map.entrySet()){
            System.out.println(entry.getKey()+"###"+entry.getValue());
        }*/

        /*for (int i = 0; i < 10; i++) {
            Long startTime = System.currentTimeMillis();
            String str = "{\"sex\":\"男" + i + "\",\"name\":\"zhangsan\"}";
            Map map = JSON.parseObject(str, HashMap.class);
            System.out.println(i + "##" + (System.currentTimeMillis() - startTime));
        }
*/
    }

}
