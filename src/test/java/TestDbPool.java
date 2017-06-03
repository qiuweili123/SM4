import com.java.dbPool.PerfInterceptor;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author liqiuwei
 * @create time:2015年11月11日上午12:30:01
 * @Description:TODO 不同的线程不同的数据量的测试相应时间
 */
public class TestDbPool {
    private static final Logger logger = LoggerFactory.getLogger(TestDbPool.class);

    @Test
    public void testAdd() {

        logger.info("-----------begin test add------------------------");
//本次测试包括了1个线程同时写入不同量的数据，5个线程同时写入不同量的数据和10个线程同时写入不同量的数据，在本机小跑了2次：  
        monitorAddUser(1, 1);
        monitorAddUser(1, 10);
        monitorAddUser(1, 100);
        monitorAddUser(1, 1000);

        monitorAddUser(5, 1);
        monitorAddUser(5, 10);
        monitorAddUser(5, 100);
        monitorAddUser(5, 1000);

        monitorAddUser(10, 1);
        monitorAddUser(10, 10);
        monitorAddUser(10, 100);
        monitorAddUser(10, 1000);

        logger.info("-----------finish test add------------------------");
    }


    /**
     * @param recordsNumber
     */
    private void monitorAddUser(final int threads, final int records) {  
      /*    
        BlockingQueue<Runnable> queue = new LinkedBlockingQueue<Runnable>();      
         ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 20, 1, TimeUnit.DAYS, queue, new RejectedExecutionHandler(){  
  
            @Override  
            public void rejectedExecution(Runnable arg0, ThreadPoolExecutor arg1) {  
                logger.error(arg0.toString());  
            }  
               
         });  
          
         List<Future<List<UserInfo>>> list = new LinkedList<Future<List<UserInfo>>>();   
           
        for(int i = 0; i < threads; i++){  
            list.add(executor.submit(new Callable<List<UserInfo>>(){  
                        @Override  
                        public List<UserInfo> call() throws Exception {  
                            SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken("Admin","password"));  
                            List<UserInfo> list = new ArrayList<UserInfo>(records);  
                              
                            Calendar cl = Calendar.getInstance();  
                            Date registerTime = cl.getTime();  
                            cl.add(Calendar.MONTH, 6);  
                            Date expiredTime = cl.getTime();  
                            for (int i = 0; i < records; i++){  
                                String username = "temp" + System.nanoTime() + Thread.currentThread().getName() + i;  
                                String userId = username;     
                                final UserInfo userInfo = new UserInfo();  
                                buildUser(userInfo, username, userId, registerTime, expiredTime,  
                                        (username + "@gmail.com"),"ALL");  
                                list.add(userInfoService.save(userInfo));  
                            }  
                            return list;  
                        }   
                    })  
            );  
              
        }  
          
        for(Future<List<UserInfo>> future : list){  
                try {  
                    future.get();  
                } catch (InterruptedException e) {  
                    logger.error(e.getMessage(), e);  
                } catch (ExecutionException e) {  
                    logger.error(e.getMessage(), e);  
                }  
        }  
        executor.shutdown(); */
        PerfInterceptor.printMethodStats();
        PerfInterceptor.clearMethodStats();
    }
}
