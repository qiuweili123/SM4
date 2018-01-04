package com.commons.test;

import org.apache.commons.chain.Command;
import org.apache.commons.chain.Context;
import org.apache.commons.chain.impl.ChainBase;

public class CommandChain extends ChainBase {

    //增加命令的顺序也决定了执行命令的顺序

    public CommandChain() {

        addCommand(new Command1());

        addCommand(new Command2());

        addCommand(new Command3());

    }


    public static void main(String[] args) throws Exception {

        Command process = new CommandChain();

        Context ctx = new SellVehicleContext();
        ctx.put("alias", "ligang");
        ctx.put("name", "123");

        try {
            process.execute(ctx);
        } catch (Exception e) {
            System.out.println("exception::" + e.getMessage());
        }


    }

}