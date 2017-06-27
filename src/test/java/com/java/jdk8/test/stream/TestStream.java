package com.java.jdk8.test.stream;

import java.util.Arrays;
import java.util.Collection;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * Created by lenovo on 2017/6/27.
 * Stream API极大简化了集合框架的处理（但它的处理的范围不仅仅限于集合框架的处）
 * 注意：
 * 1.所有 Stream 的操作必须以 lambda 表达式为参数
 * 2.当一个 Stream 是并行化的，就不需要再写多线程代码，所有对它的操作会自动并行进行的
 * 3.集合有固定大小，Stream 则不必limit(n) 和 findFirst() 这类的 short-circuiting 操作可以对无限的 Stream 进行运算并很快完成
 * forEach 不能修改自己包含的本地变量值，也不能用 break/return 之类的关键字提前结束循环
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
        System.out.println("----------------Stream.generate-----------------");
        // 通过实现 Supplier 接口，你可以自己来控制流的生成。这种情形通常用于随机数、常量的 Stream，或者需要前后元素间维持着某种状态信息的 Stream。把 Supplier 实例传递给 Stream.generate() 生成的 Stream，默认是串行（相对 parallel 而言）但无序的（相对 ordered 而言）。由于它是无限的，在管道中，必须利用 limit 之类的操作限制 Stream 大小
        Supplier<Person> supplier = () -> new Person("0", "StormTestUser0", 1);
        //new PersonSupplier() 与 PersonSupplier：new 不一样效果
        Stream.generate(new PersonSupplier()).limit(10).forEach(personSupplier -> System.out.println(personSupplier.getName()));
    }
}
