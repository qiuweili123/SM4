package com.java.jdk8.test.stream;

import java.util.Arrays;
import java.util.Collection;

/**
 * Created by lenovo on 2017/6/27.
 * Stream API极大简化了集合框架的处理（但它的处理的范围不仅仅限于集合框架的处）
 */
public class TestStream {
    public static void main(String[] args) {
        final Collection<Task> tasks = Arrays.asList(
                new Task(Status.OPEN, 5),
                new Task(Status.OPEN, 13),
                new Task(Status.CLOSED, 8)
        );
        final long totalPointsOfOpenTasks = tasks.stream().
                filter(task -> task.getStatus() == Status.OPEN)
                //mapToInt操作通过Task::getPoints这种方式调用每个task实例的getPoints方法把Task的stream转化为Integer的stream
                .mapToInt(Task::getPoints)
                .sum();//用sum函数把所有的分数加起来，
        System.out.println("Total points: " + totalPointsOfOpenTasks);
    }
}
