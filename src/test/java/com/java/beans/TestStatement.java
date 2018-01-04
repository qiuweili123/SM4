package com.java.beans;

import java.beans.Expression;
import java.beans.Statement;

/**
 * Statement和Expression的区别：
 * Expression是可以获得返回值的，方法是getValue。Statement不能获得返回值。
 */
public class TestStatement {

    public static void main(String[] args) {
        testStatement();

        testExpression();
    }

    public static void testStatement() {
        try {
            User user = new User();
            Statement statement = new Statement(user, "setAge", new Object[]{10});
            statement.execute();

            System.out.println(statement.getMethodName() + "##" + user.getAge());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void testExpression() {
        try {
            User user = new User();
            Expression expression = new Expression(user, "say", new Object[]{20, "hello"});

            expression.execute();

            System.out.println(expression.getValue());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}  