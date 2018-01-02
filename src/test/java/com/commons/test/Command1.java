package com.commons.test;

import org.apache.commons.chain.Command;
import org.apache.commons.chain.Context;

public class Command1 implements Command {

    public boolean execute(Context context) throws Exception {
        //context.put("name","zhangsan");
        context.put("age", 14);
        System.out.println("Command1 is done!");

        return false;

    }

}