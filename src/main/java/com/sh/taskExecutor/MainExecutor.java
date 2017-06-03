package com.sh.taskExecutor;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("mainExecutor")
public class MainExecutor {
    @Resource
    private ThreadPoolTaskExecutor taskExecutor;

    public void printMessage() {
        for (int i = 0; i < 10; i++) {
            try {

                taskExecutor.execute(new TaskProcessor(taskExecutor.getActiveCount() + "messge" + i));

            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }

    }
}
