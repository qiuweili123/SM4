import com.sh.mongo.dao.UserMongoDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:conf/application*.xml"})
public class TestMongo {
    @Resource
    private UserMongoDao useDao;

    @Test
    public void findById() {

        System.out.println(useDao.findOneByUserId("libinsbox@138.com").getUserName());

    }
}
