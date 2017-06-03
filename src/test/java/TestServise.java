import com.sh.model.Users;
import com.sh.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:conf/*.xml")

public class TestServise {

    @Resource
    private UserService userService;

    @Test
    public void testFindUsersBy() throws Exception {
        List<Users> users = userService.findUsers();
        System.out.println("ddd" + users);
    }
}
