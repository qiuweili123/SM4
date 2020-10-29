import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
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
        String msg = "{\"@timestamp\":\"2020-07-29T08:26:54.164Z\",\"beat\":{\"hostname\":\"astable-1\",\"name\":\"astable-1\",\"version\":\"5.6.4\"},\"input_type\":\"log\",\"logType\":\"service\",\"message\":\"{\\\"date\\\":\\\"2020-07-29T16:06:55.267\\\",\\\"traceId\\\":\\\"fc1338b1b5b14961a90053e433a484c4\\\",\\\"sequenceId\\\":\\\"null\\\",\\\"level\\\":\\\"INFO\\\",\\\"appName\\\":\\\"boot\\\",\\\"class\\\":\\\"com.ishansong.ops.rinet.service.impl.RiskManagerServiceImpl\\\",\\\"method\\\":\\\"check\\\",\\\"line\\\":\\\"106\\\",\\\"message\\\":\\\"{`begin_check_time`:1596010014916,`biz_time`:1596010014866,`data_complete_time`:1596010015229,`disposal_code`:5990,`end_check_time`:1596010015267,`factor_map`:{`device_id_black`:0,`client_ip_black`:0,`submit_order_stat_4`:0,`submit_order_stat_5`:0,`order_mobile_black`:1,`cid_black`:0,`submit_order_stat_6`:0,`submit_order_stat_7`:0,`user_id_black`:0,`from_mobile_black`:0,`user_id_white`:0,`wifiname_black`:0,`imei_black`:0,`uid_black`:0,`ua_black`:0,`submit_order_stat_1`:0,`submit_order_stat_2`:0,`to_mobile_black`:0},`field_map`:{`wifiname`:null,`sencesid`:null,`from_address_detail`:`132`,`premium_amt`:null,`type_max_discount_amount`:-1,`discount`:0,`ua`:`Mozilla/5.0 (Linux; Android 10; PCT-AL10 Build/HUAWEIPCT-AL10; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/80.0.3987.99 Mobile Safari/537.36`,`from_mobile_city`:`邯郸`,`tid`:`46750316761596010014.807172.23.4.15711846691`,`premium_type`:null,`t{\\\"date\\\":\\\"2020-07-29T16:26:48.212\\\",\\\"traceId\\\":\\\"477c45b0ada648c086fe5f58995ca8a0\\\",\\\"sequenceId\\\":\\\"null\\\",\\\"level\\\":\\\"INFO\\\",\\\"appName\\\":\\\"boot\\\",\\\"class\\\":\\\"com.ishansong.ops.rinet.service.impl.RiskManagerServiceImpl\\\",\\\"method\\\":\\\"check\\\",\\\"line\\\":\\\"106\\\",\\\"message\\\":\\\"{`begin_check_time`:1596011207924,`biz_time`:1596011207881,`data_complete_time`:1596011208190,`disposal_code`:5990,`end_check_time`:1596011208211,`factor_map`:{`device_id_black`:0,`client_ip_black`:0,`submit_order_stat_4`:0,`submit_order_stat_5`:0,`order_mobile_black`:0,`cid_black`:0,`submit_order_stat_6`:0,`submit_order_stat_7`:0,`user_id_black`:0,`from_mobile_black`:0,`user_id_white`:0,`wifiname_black`:0,`imei_black`:0,`uid_black`:0,`ua_black`:0,`submit_order_stat_1`:0,`submit_order_stat_2`:0,`to_mobile_black`:0},`field_map`:{`wifiname`:null,`sencesid`:null,`from_address_detail`:`2`,`premium_amt`:null,`type_max_discount_amount`:null,`discount`:0,`ua`:`Mozilla/5.0 (iPhone; CPU iPhone OS 13_5_1 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Mobile/15E148 /sa-sdk-ios/sensors-verify/beidou-astable.bingex.com?pj_user`,`from_mobile_city`:`邯郸`,`tid`:`trace-3e3c4fa5-d175-11ea-bcea-49cd7d92f759-172.23.2.173`,`premium_type`:null,`to_address_detail`:`22`,`weight_fee`:0,`activity_id`:null,`client_ip`:`172.23.2.173`,`to_mobile_city_id`:4402,`identity_type`:null,`order_type`:`0`,`from_address`:`西二旗(地铁站)`,`lat`:40.05442807255353,`order_remark`:null,`client_ip_city_id`:null,`distance_fee`:1600,`pickup_appoint_time`:null,`device_id`:`E436CF1E-B797-44FD-9542-01ACDE7F3A26`,`lng`:116.2896380842076,`weight`:5,`usertype`:null,`identity_time`:null,`to_address`:`上地(地铁站)`,`info_index`:`1`,`coupon_discount_amt`:0,`order_mobile_city_id`:1304,`client_ip_city`:null,`user_id`:30005052,`order_mobile_city`:`清远`,`to_mobile`:`M20000051646`,`order_mobile`:`M20001140533`,`coupon_group_id`:5206,`city_id`:1101,`cid`:`E436CF1E-B797-44FD-9542-01ACDE7F3A26`,`to_mobile_city`:`韶关`,`addition_fee`:0,`from_lng`:116.312753,`order_number`:`TDH2020072919532571`,`type_condition_amount`:null,`identity_number`:null,`from_name`:null,`regedit_mobile`:null,`from_mobile`:`M20000000052`,`platform`:`用户版APP`,`mac`:`02:00:00:00:00:00`,`sid`:`15960075664144`,`total_amt`:1600,`uid`:30005052,`city_name`:`北京市`,`sub_type`:null,`business_type`:1,`to_lng`:116.326742,`order_goods_name`:`文件证件`,`order_channel`:`3`,`client_ip_province`:null,`from_lat`:40.059095,`delivery_appoint_time`:null,`to_lat`:40.038825,`amount`:null,`to_name`:null,`order_appoint_type`:0,`from_city_name`:`北京市`,`type_id`:null,`regedit_time`:null,`client_ip_country`:`本地局域网`,`mobile`:null,`identity_name`:null,`abolish_time`:null,`to_city_name`:`北京市`,`delivery_distance`:2600,`from_mobile_city_id`:4418,`imei`:null,`premium_fee`:null,`order_goods_wordseg`:`10006000`},`hit_status`:1,`hit_time`:1596011208195,`log_type`:1,`result_code`:200,`result_msg`:`成功`,`risk_no`:`099d73b5fad7d494096ae1eaf0c286df`,`risk_uid`:`30005052`,`rule_group_id`:1,`rule_group_priority`:9999,`rule_id`:1,`rule_name`:`下单人黑名单`,`rule_priority`:1000,`rule_weight`:100,`scene_code`:`submit_order`,`sys_code`:`7f4b1410455bc8ef`,`user_type`:1} \\\"}\",\"offset\":93314,\"serverName\":\"astable-1\",\"source\":\"/mnt/iss/web/rinet-web/nlogs/check.2020-07-29.log\",\"type\":\"service-log\"}";
//.getString("message").replaceAll("`", "\"");
        JSONObject jsonObject = JSON.parseObject(msg);

        String string = jsonObject.get("message").toString();
        Map jsonObject1 = JSON.parseObject(string, Map.class);
        String mg2 = "{\"date\":\"2020-07-29T16:06:55.267\",\"traceId\":\"fc1338b1b5b14961a90053e433a484c4\",\"sequenceId\":\"null\",\"level\":\"INFO\",\"appName\":\"boot\",\"class\":\"com.ishansong.ops.rinet.service.impl.RiskManagerServiceImpl\",\"method\":\"check\",\"line\":\"106\",\"message\":\"{`begin_check_time`:1596010014916,`biz_time`:1596010014866,`data_complete_time`:1596010015229,`disposal_code`:5990,`end_check_time`:1596010015267,`factor_map`:{`device_id_black`:0,`client_ip_black`:0,`submit_order_stat_4`:0,`submit_order_stat_5`:0,`order_mobile_black`:1,`cid_black`:0,`submit_order_stat_6`:0,`submit_order_stat_7`:0,`user_id_black`:0,`from_mobile_black`:0,`user_id_white`:0,`wifiname_black`:0,`imei_black`:0,`uid_black`:0,`ua_black`:0,`submit_order_stat_1`:0,`submit_order_stat_2`:0,`to_mobile_black`:0},`field_map`:{`wifiname`:null,`sencesid`:null,`from_address_detail`:`132`,`premium_amt`:null,`type_max_discount_amount`:-1,`discount`:0,`ua`:`Mozilla/5.0 (Linux; Android 10; PCT-AL10 Build/HUAWEIPCT-AL10; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/80.0.3987.99 Mobile Safari/537.36`,`from_mobile_city`:`邯郸`,`tid`:`46750316761596010014.807172.23.4.15711846691`,`premium_type`:null,`t{\"date\":\"2020-07-29T16:26:48.212\",\"traceId\":\"477c45b0ada648c086fe5f58995ca8a0\",\"sequenceId\":\"null\",\"level\":\"INFO\",\"appName\":\"boot\",\"class\":\"com.ishansong.ops.rinet.service.impl.RiskManagerServiceImpl\",\"method\":\"check\",\"line\":\"106\",\"message\":\"{`begin_check_time`:1596011207924,`biz_time`:1596011207881,`data_complete_time`:1596011208190,`disposal_code`:5990,`end_check_time`:1596011208211,`factor_map`:{`device_id_black`:0,`client_ip_black`:0,`submit_order_stat_4`:0,`submit_order_stat_5`:0,`order_mobile_black`:0,`cid_black`:0,`submit_order_stat_6`:0,`submit_order_stat_7`:0,`user_id_black`:0,`from_mobile_black`:0,`user_id_white`:0,`wifiname_black`:0,`imei_black`:0,`uid_black`:0,`ua_black`:0,`submit_order_stat_1`:0,`submit_order_stat_2`:0,`to_mobile_black`:0},`field_map`:{`wifiname`:null,`sencesid`:null,`from_address_detail`:`2`,`premium_amt`:null,`type_max_discount_amount`:null,`discount`:0,`ua`:`Mozilla/5.0 (iPhone; CPU iPhone OS 13_5_1 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Mobile/15E148 /sa-sdk-ios/sensors-verify/beidou-astable.bingex.com?pj_user`,`from_mobile_city`:`邯郸`,`tid`:`trace-3e3c4fa5-d175-11ea-bcea-49cd7d92f759-172.23.2.173`,`premium_type`:null,`to_address_detail`:`22`,`weight_fee`:0,`activity_id`:null,`client_ip`:`172.23.2.173`,`to_mobile_city_id`:4402,`identity_type`:null,`order_type`:`0`,`from_address`:`西二旗(地铁站)`,`lat`:40.05442807255353,`order_remark`:null,`client_ip_city_id`:null,`distance_fee`:1600,`pickup_appoint_time`:null,`device_id`:`E436CF1E-B797-44FD-9542-01ACDE7F3A26`,`lng`:116.2896380842076,`weight`:5,`usertype`:null,`identity_time`:null,`to_address`:`上地(地铁站)`,`info_index`:`1`,`coupon_discount_amt`:0,`order_mobile_city_id`:1304,`client_ip_city`:null,`user_id`:30005052,`order_mobile_city`:`清远`,`to_mobile`:`M20000051646`,`order_mobile`:`M20001140533`,`coupon_group_id`:5206,`city_id`:1101,`cid`:`E436CF1E-B797-44FD-9542-01ACDE7F3A26`,`to_mobile_city`:`韶关`,`addition_fee`:0,`from_lng`:116.312753,`order_number`:`TDH2020072919532571`,`type_condition_amount`:null,`identity_number`:null,`from_name`:null,`regedit_mobile`:null,`from_mobile`:`M20000000052`,`platform`:`用户版APP`,`mac`:`02:00:00:00:00:00`,`sid`:`15960075664144`,`total_amt`:1600,`uid`:30005052,`city_name`:`北京市`,`sub_type`:null,`business_type`:1,`to_lng`:116.326742,`order_goods_name`:`文件证件`,`order_channel`:`3`,`client_ip_province`:null,`from_lat`:40.059095,`delivery_appoint_time`:null,`to_lat`:40.038825,`amount`:null,`to_name`:null,`order_appoint_type`:0,`from_city_name`:`北京市`,`type_id`:null,`regedit_time`:null,`client_ip_country`:`本地局域网`,`mobile`:null,`identity_name`:null,`abolish_time`:null,`to_city_name`:`北京市`,`delivery_distance`:2600,`from_mobile_city_id`:4418,`imei`:null,`premium_fee`:null,`order_goods_wordseg`:`10006000`},`hit_status`:1,`hit_time`:1596011208195,`log_type`:1,`result_code`:200,`result_msg`:`成功`,`risk_no`:`099d73b5fad7d494096ae1eaf0c286df`,`risk_uid`:`30005052`,`rule_group_id`:1,`rule_group_priority`:9999,`rule_id`:1,`rule_name`:`下单人黑名单`,`rule_priority`:1000,`rule_weight`:100,`scene_code`:`submit_order`,`sys_code`:`7f4b1410455bc8ef`,`user_type`:1} \"}";
        System.out.println(JSON.parseObject(string).get("message").toString());
        // String msg="{\"date\":\"2020-07-03T16:18:54.009\",\"level\":\"INFO\",\"appName\":\"demm-app\",\"class\":\"com.ishansong.ops.rinet.client.RiskClient\",\"method\":\"finish\",\"line\":\"22\",\"message\":\"{`risk_no`:`201908929`} \"}\n";
   /*     msg = msg.replaceAll("`", "'");
        Map<String,Object> map = JSON.parseObject(msg, HashMap.class);
        Map map1 = JSON.parseObject ((String) map.get("message"), HashMap.class);
        System.out.println("out=="+map1.get("risk_no"));*/
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
