import java.sql.*;

/**
 * All rights Reserved, Designed By Letv
 *
 * @Title: TestMycat.java
 * @Package
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: liqiuwei
 * @date: 2015年11月17日 下午1:32:58
 * @version
 */

/**
 * @author liqiuwei
 * @create time:2015年11月17日下午1:32:58
 * @Description:TODO(这里用一句话描述这个类的作用)
 */
public class TestMycat {
    public static void main(String[] args) {
        String driver = "com.mysql.jdbc.Driver";
        String passwrod = "mycat_admin_test";
        String userName = "mycat_admin";
        String url = "jdbc:mysql://10.140.70.108:8066/orders?useUnicode=true&characterEncoding=UTF-8";
        String sql = "select * from InnoDBUser ";

        try {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(url, userName, passwrod);
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println("id : " + rs.getObject(1));
            }

            // 关闭记录集
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            // 关闭声明
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            // 关闭链接对象
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
