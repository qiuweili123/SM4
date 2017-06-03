import com.sh.taskExecutor.AsyncService;
import com.sh.taskExecutor.MainExecutor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:conf/application-task.xml"})
public class TestTaskExecutor {
    @Resource
    private MainExecutor mainExecutor;
    @Resource
    private AsyncService asyncService;

    @Test
    public void testTask() throws Exception {

        mainExecutor.printMessage();
        Thread.sleep(300000);
    }

    @Test
    public void testAsyncService() throws Exception {
        for (int i = 0; i < 100; i++) {
            System.out.println("1111111111==" + i);
            asyncService.update();
            System.out.println("22222222222");
        }
        Thread.sleep(300000);
    }
}
