package com.commons.test;

import org.apache.commons.chain.Command;
import org.apache.commons.chain.Context;
import org.apache.commons.lang.StringUtils;

public class Command2 implements Command {

    public boolean execute(Context ctx) throws Exception {
        SellVehicleContext context = (SellVehicleContext) ctx;
        if (StringUtils.isEmpty(context.getName())) {
            System.out.println("Command2 is done!");
            return false;
        }


        return false;

    }

}