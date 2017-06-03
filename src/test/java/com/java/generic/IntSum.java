package com.java.generic;

public class IntSum implements ISum<Integer> {

    @Override
    public int sum(Integer... t) {
        int sum = 0;
        for (int i = 0; i < t.length; i++) {
            sum += t[i];
        }
        // TODO Auto-generated method stub
        return sum;
    }

}
