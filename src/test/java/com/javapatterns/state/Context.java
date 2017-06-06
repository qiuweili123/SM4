package com.javapatterns.state;

/**
 * Created by Administrator on 17-6-6.
 */
public class Context {
    private IState state;
    private int flag;

    public IState getState() {
        return state;
    }

    public void setState(IState state) {
        this.state = state;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public void dealWith() {
        this.state.doSomthing(this);
    }
}
