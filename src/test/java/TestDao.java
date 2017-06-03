import com.sh.model.Users;
import com.sh.service.UserService;
import com.sh.utils.JsonUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.logicalcobwebs.proxool.ProxoolException;
import org.logicalcobwebs.proxool.ProxoolFacade;
import org.logicalcobwebs.proxool.admin.SnapshotIF;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @author lenovo
 * @version $Id: TestDao.java, v 0.1 2017年3月8日 下午3:02:59 lenovo Exp $
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:conf/application.xml"})

public class TestDao {

    /**
     * 用户serveice
     */
    @Resource
    UserService userService;

    @Resource
    JdbcTemplate jdbcMasterTemplate;

    private int activeCount = 0;

    /**
     * @throws Exception
     */
    @Test
    public void testFindbyUser() throws Exception {

        System.out.println("1111111111");
        System.out.println("path" + userService.getClass().getResource("/").getPath());
        String nameString = userService.findUserInfoById(394L);

    }

    @Test
    public void testFindUserList() throws Exception {
        List<Users> list = userService.findUsersList();

        System.out.println("size==" + list.size());
        for (Users u : list) {
            System.out.println("name" + u.getName());

        }
    }

    @Test
    public void testMapQureyInfo() throws Exception {
        List<Map> list = userService.find("select new Map(id as id,name as name) from Users");
        List<Integer> list1 = userService.find("select count(*) from Users");

        System.out.println("size=" + list1.get(0));
        for (Map map : list) {
            System.out.println(map.get("id") + "##" + map.get("name"));

        }
    }

    @Test
    public void testJdbcQuery() throws Exception {
        List<Users> list = jdbcMasterTemplate.query(" select * from E_VENDOR ", new RowMapper<Users>() {

            @Override
            public Users mapRow(ResultSet rs, int rowNum) throws SQLException {
                Users users = new Users();
                users.setId(rs.getLong("id"));
                users.setName(rs.getString("name"));
                users.setAddress(rs.getString("address"));
                return users;
            }

        });
        //1、实现rowmapper
        for (Users u : list) {
            System.out.println("name=" + u.getName());
        }
        //2、直接查询生成Map
        List<Map<String, Object>> list2 = jdbcMasterTemplate.queryForList("select id,name from E_VENDOR");
        for (Map map : list2) {
            System.out.println("map=" + map.get("id") + map.get("name"));

        }
    }

    @Test
    public void testJdbcUpdate() throws Exception {
        int cnt = jdbcMasterTemplate.update(" update E_VENDOR set name='现代出版社' where name='现代出版社1'");
        System.out.println("cnt==" + cnt);
    }

    @Test
    public void testHSqlQuery() throws Exception {
        List<Users> list = userService.findBySqlQuery("select * from E_VENDOR  where  name='现代出版社'", "com.sh.model.Users");

        Thread.sleep(30000);
        System.out.println("list==" + JsonUtil.toJson(list));
        for (Users u : list) {
            System.out.println("##" + u.getCreatedAt());
        }
    }

    /**
     * 注意map中得到的Key为数据库中的字段
     */
    @Test
    public void testHSqlQueryMap() throws Exception {
        List<Map<String, Object>> list = userService.findBySqlQuery("select * from E_VENDOR  where  name='现代出版社'", "java.util.HashMap");
        for (Map<String, Object> map : list) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
            }

        }
    }

    @Test
    public void testProxool() throws Exception {
        try {
            List<Users> list = userService.findBySqlQuery("select * from E_VENDOR  where  name='现代出版社'", "com.sh.model.Users");

            Thread.sleep(30000);
            for (Users u : list) {
                System.out.println("##" + u.getCreatedAt());
            }

            SnapshotIF snapshot = ProxoolFacade.getSnapshot("DBPool", true);
            int curActiveCount = snapshot.getActiveConnectionCount();// 获得活动连接数 
            int availableCount = snapshot.getAvailableConnectionCount();// 获得可得到的连接数
            int maxCount = snapshot.getMaximumConnectionCount();// 获得总连接数    

            System.out.println("##curActiveCount=" + curActiveCount);
            if (curActiveCount != activeCount)// 当活动连接数变化时输出的信 息 
            {
                System.out.println("活动连接数:" + curActiveCount + "(active)  可得到的连接数:" + availableCount + "(available)  总连接数:" + maxCount + "(max)");
                activeCount = curActiveCount;
            }

            System.out.println("list==" + JsonUtil.toJson(list));

        } catch (ProxoolException e) {
            e.printStackTrace();
        }

    }
}
