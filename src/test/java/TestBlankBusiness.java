import com.blank.CallRes;
import com.blank.CustomerType;
import com.blank.GenerateCustomer;
import com.blank.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 模拟银行交易
 *
 * @author inth-liqiuwei
 * @version V1.0
 * @ClassName: TestBlankBusiness
 * @Description: TODO
 * @date 2015年7月19日 下午1:52:31
 */
public class TestBlankBusiness {
    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(18);

        // 创建服务窗口
        // 1-4
        threadPool.execute(new Service(new CustomerType[]{CustomerType.COMMON}, "【窗口1】"));
        threadPool.execute(new Service(new CustomerType[]{CustomerType.COMMON}, "【窗口2】"));
        threadPool.execute(new Service(new CustomerType[]{CustomerType.COMMON}, "【窗口3】"));
        threadPool.execute(new Service(new CustomerType[]{CustomerType.COMMON}, "【窗口4】"));

        // 5
        threadPool.execute(new Service(new CustomerType[]{CustomerType.EXPRESS, CustomerType.COMMON}, "【窗口5】"));

        // 6
        threadPool.execute(new Service(new CustomerType[]{CustomerType.VIP, CustomerType.COMMON}, "【窗口6】"));

        // 生成客户数量1:6:3
        threadPool.execute(new GenerateCustomer(CustomerType.VIP));
        for (int i = 0; i < 6; i++) {
            threadPool.execute(new GenerateCustomer(CustomerType.COMMON));
        }
        for (int i = 0; i < 3; i++) {
            threadPool.execute(new GenerateCustomer(CustomerType.EXPRESS));
        }

        //启动模拟应答
        threadPool.execute(new CallRes());
    }


}
