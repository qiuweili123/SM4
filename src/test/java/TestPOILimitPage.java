import com.sh.utils.ExcelUtil;

import java.io.IOException;
import java.util.*;


public class TestPOILimitPage {

    /**
     * 会员导出列表页面
     * http://www.itmmd.com/201503/636.html
     *
     * @param request
     * @param response
     * @throws IOException
     */
    /* @SuppressWarnings("unchecked")
     @RequestMapping(params = "method=Excel")
	 public void Excel(HttpServletRequest request, HttpServletResponse response)
	   throws IOException {
	 
	  MemInfoStatisService dfs = new MemInfoStatisService(RequestUtil
	    .getMap(request));
	 
	  Calendar cal22 = Calendar.getInstance();
	  Date now = new Date();
	  SimpleDateFormat sdf22 = new SimpleDateFormat("yyyy-MM-dd");
	 
	  cal22.setTime(now);
	 
	  String str1 = sdf22.format(cal22.getTime());
	  response.setHeader("Content-disposition", "attachment; filename="
	    + URLEncoder.encode(str1 + "会员信息统计表", "utf-8") + ".xlsx");
	 
	  response.setContentType("application/msexcel;charset=utf-8");
	  OutputStream os = response.getOutputStream();
	  os.flush();
	 
	  /*result.put("totleCount", totleCount);
	  result.put("resultLIst", _list); 
	  Map<String,Object> result = dfs.exportWorkerTrades();
	  List<Map> list  = (List<Map> )result.get("resultLIst");
	  int totleCount = (Integer)result.get("totleCount");
	 
	  ArrayList headList = new ArrayList<String>();
	  headList.add("会员总数");
	  headList.add(totleCount+"");
	   
	  LinkedHashMap<String, String> headersLinkedMap = new LinkedHashMap<String, String>();
	 
	  headersLinkedMap.put("姓名", "name");
	  headersLinkedMap.put("用户昵称", "nick_name");
	  headersLinkedMap.put("会员卡号", "user_card_no");
	  headersLinkedMap.put("加油卡号", "oil_card_no");
	  headersLinkedMap.put("证件类型", "idcard_type");
	  headersLinkedMap.put("证件号码", "idcard_no");
	  headersLinkedMap.put("手机号", "tel");
	  headersLinkedMap.put("注册地市", "card_city");
	  headersLinkedMap.put("注册站点", "website_name");
	  headersLinkedMap.put("会员等级", "user_level");
	  headersLinkedMap.put("注册来源", "reg_type");
	  headersLinkedMap.put("新老会员", "user_type");
	  headersLinkedMap.put("启用状态", "user_state");
	  headersLinkedMap.put("验证状态", "state");
	  headersLinkedMap.put("性别", "q_sex");
	  headersLinkedMap.put("注册人", "oper_name");
	  headersLinkedMap.put("注册日期", "register_date");
	  headersLinkedMap.put("会员省份", "province");
	  headersLinkedMap.put("会员地市", "city");
	  headersLinkedMap.put("会员区县", "district_id");
	  headersLinkedMap.put("职业", "profession");
	  headersLinkedMap.put("收入", "income");
	  headersLinkedMap.put("会员类型", "user_per_type");
	  headersLinkedMap.put("加油类型", "fuel_type");
	  headersLinkedMap.put("车型", "VEHICLE_TYPE");
	  headersLinkedMap.put("车辆归属", "vehicle_belong");
	  headersLinkedMap.put("会员群体", "mem_groups"); //需要管理客户表
	  headersLinkedMap.put("会员来源", "user_type_judge_type_name");
	  headersLinkedMap.put("会员价值", "user_value");
	  headersLinkedMap.put("无效原因", "bind_log");
	  try {
	   //System.out.println("2007");
	   ExcelUtil.exportExcelWithHeaderFast2007("会员信息统计表",headList, headersLinkedMap, list, os);
	  } catch (IOException ioe) {
	   ioe.printStackTrace();
	  } finally {
	   os.close();
	 
	  }
	 
	 }*/
    public static void main(String[] args) {

        ArrayList headList = new ArrayList<String>();
        headList.add("会员总数");
        headList.add("总结数据");
        List<Map> list = new ArrayList<Map>();
        LinkedHashMap<String, String> headersLinkedMap = new LinkedHashMap<String, String>();
        headersLinkedMap.put("姓名", "name");
        headersLinkedMap.put("编号", "no");
        //模拟数据
        ExcelUtil.displayMemery();
        long start = System.currentTimeMillis();
        for (int i = 1; i < 10000000; i++) {
            Map<String, Object> dataMap = new HashMap();
            dataMap.put("name", "张三" + i);
            dataMap.put("no", 122222 + i);
            list.add(dataMap);
        }
        ExcelUtil.displayMemery();

//		try {
//			//ExcelUtil.exportExcelWithHeaderFast2007("会员信息统计表",headList, headersLinkedMap, list, null);
// 
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
        long end = System.currentTimeMillis();

        System.out.println((end - start) / 1000);
        ExcelUtil.displayMemery();
    }


}
