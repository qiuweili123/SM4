package com.commons.test;

import org.apache.commons.chain.Command;
import org.apache.commons.chain.Context;
import org.apache.commons.lang.StringUtils;

public class Command2 implements Command {

    public boolean execute(Context ctx) throws Exception {
        SellVehicleContext context = (SellVehicleContext) ctx;
        if (StringUtils.isBlank(context.getName())) {
            throw new Exception("超出");
        }


        return false;

    }

}