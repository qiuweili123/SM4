package com.javapatterns.state;

/**
 * Created by Administrator on 17-6-6.
 */
public class StateA implements IState {
    @Override
    public void doSomthing(Context context) {
        System.out.println("stata A ing....");
        IState state = new StateB();
        context.setState(state);
        context.dealWith();
    }
}
