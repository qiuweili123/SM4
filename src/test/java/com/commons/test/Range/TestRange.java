package com.commons.test.Range;

import org.apache.commons.lang.math.DoubleRange;
import org.apache.commons.lang.math.NumberRange;

/**
 * Created by lenovo on 2018/1/5.
 */
public class TestRange {
    public static void main(String[] args) {
        DoubleRange doubleRange = new DoubleRange(90, 120);

        double score1 = 120.1;

        NumberRange numberRange = new NumberRange(90.0, 120.0);

        Number number = 120.0;



        System.out.println("number range is: " +   numberRange.containsNumber(number));
    }
}
