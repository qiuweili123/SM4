import com.java.httpClient.HttpServletUtil;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


public class TestHttpClient {

    @Test
    public void tesDoGet() throws Exception {
        HttpServletUtil httpServletUtil = new HttpServletUtil();
        try {
            String retString = httpServletUtil.doGet("http://www.baidu.com");
            System.out.println("##retString==" + retString);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * 模拟登陆
     *
     * @param @throws Exception    设定文件
     * @return void    返回类型
     * @throws
     * @Title: testLogin
     * @Description: TODO
     */


    @Test
    public void testLogin() throws Exception {
        HttpServletUtil httpServletUtil = new HttpServletUtil();
        String url = "http://mail.163.com";
        List<NameValuePair> paramsList = new ArrayList();
        paramsList.add(new BasicNameValuePair("loginName", "qiuweili123@163.com"));
        paramsList.add(new BasicNameValuePair("pwd", "Ata(5021)"));
        String retString = httpServletUtil.doPost(paramsList, url);
        System.out.println("##" + retString);
    }
}
