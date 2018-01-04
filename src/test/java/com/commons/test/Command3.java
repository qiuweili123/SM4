package com.commons.test;

import org.apache.commons.chain.Command;
import org.apache.commons.chain.Context;
import org.apache.commons.validator.routines.IntegerValidator;

public class Command3 implements Command {

    public boolean execute(Context ctx) throws Exception {
        SellVehicleContext myCtx = (SellVehicleContext) ctx;
        System.out.println("Command3 is done!" + myCtx.getName());
        System.out.println("##" + ctx.get("alias"));
        boolean inRange = IntegerValidator.getInstance().isInRange(myCtx.getAge(), 12, 32);
        System.out.println("###" + inRange);
        return true;

    }

}