package com.java.jdk8.test.stream;

import com.google.common.collect.Lists;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
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

/**
 * 创建stream的几种方式：
 * 集合-->Stream：stream()
 * 数组-->Stream：Stream.of(T t)或者Arrays.stream(T[] t)
 * 任意元素-->Stream：Stream.of(T... values)
 */

public class TestStream {
    public static void main(String[] args) {
        //m4();
        m2();
        testMatch();
        testSort();
    }

    /**
     * filter是选择而不是过滤，即filter是选择满足条件的元素。
     */
    public static void m1() {
        Arrays.asList("zhaojigang", "nana", "tianya", "nana").stream().
                distinct().filter(str -> !str.equals("tianya")).filter(str1 -> !str1.equals("nana")).sorted(String::compareTo).collect(Collectors.toList()).forEach(System.out::println);

    }

    public static void m2() {
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
        // 通过实现 Supplier 接口，你可以自己来控制流的生成。这种情形通常用于随机数、常量的 Stream，或者需要前后元素间维持着某种状态信息的 Stream。
        // 把 Supplier 实例传递给 Stream.generate() 生成的 Stream，默认是串行（相对 parallel 而言）但无序的（相对 ordered 而言）。由于它是无限的，在管道中，必须利用 limit 之类的操作限制 Stream 大小
        Supplier<Person> supplier = () -> new Person("0", "StormTestUser0", 1);
        //new PersonSupplier() 与 PersonSupplier：new 不一样效果,PersonSupplier：new 操作的是每个实例的流
        Stream.generate(new PersonSupplier()).limit(10).forEach(person -> System.out.println(person.getName()));
    }

    /**
     * 将数组转化为map
     */
    public static void m3() {
        Stream<String> strStream = Stream.of("java", "c++", "c", "python");
        //Function.identity()-->返回strStream中的元素，toMap方法的我两个参数都是Function接口型的，所以第二个参数即使只放0，也不能直接写作0，可以使用如上的方式进行操作
        Map<String, Integer> map = strStream.collect(Collectors.toMap(Function.identity(), (x) -> 0));
        System.out.println("##map==" + map);
    }

    /**
     * map:
     * 作用：对流中的每一个元素进行操作。
     */
    public static void m4() {
        String[] strs = new String[]{"java", "c++", "c", "python"};
        //Arrays.stream(strs).map(String::toUpperCase).forEach(System.out::println);
        Arrays.stream(strs).map(str -> call(str)).forEach(stringStream -> System.out.println(stringStream));
        //System.out.println( Arrays.toString(strs));

    }

    public static void testMatch() {
        String[] a1 = new String[]{"a", "b", "dd"};

        List<String> list = new ArrayList<>();
        list.stream().forEach(System.out::println);

    }


    public static String call(String string) {
        System.out.println("string==" + string);
        return string + "11";
    }

    public static void groupAndSum() {
        List<Item> items = Arrays.asList(
                new Item("apple", 10, new BigDecimal("9.99")),
                new Item("banana", 20, new BigDecimal("19.99")),
                new Item("orang", 10, new BigDecimal("29.99")),
                new Item("watermelon", 10, new BigDecimal("29.99")),
                new Item("papaya", 20, new BigDecimal("9.99")),
                new Item("apple", 10, new BigDecimal("9.99")),
                new Item("banana", 10, new BigDecimal("19.99")),
                new Item("apple", 20, new BigDecimal("9.99"))
        );
////Group by + Count
        Map<String, Long> counting = items.stream().collect(
                Collectors.groupingBy(Item::getName, Collectors.counting()));

        System.out.println(counting);
        //Group by + Sum qty
        Map<String, Integer> sum = items.stream().collect(
                Collectors.groupingBy(Item::getName, Collectors.summingInt(Item::getQty)));

        System.out.println(sum);

        //group by price
        Map<BigDecimal, List<Item>> groupByPriceMap =
                items.stream().collect(Collectors.groupingBy(Item::getPrice));

        System.out.println(groupByPriceMap);

        // group by price, uses 'mapping' to convert List<item> to Set<string>

        Map<BigDecimal, Set<String>> result = items.stream().collect(
                Collectors.groupingBy(Item::getPrice,
                        Collectors.mapping(Item::getName, Collectors.toSet())
                )
        );

        System.out.println(result);


    }

    public static void testSort() {
        String[] arr = new String[]{"a001", "a003", "a002"};
        Arrays.stream(arr).sorted().forEach(System.out::println);
        List<Person> list=Lists.newArrayList();

        Person p1=new Person();
        p1.setName("zhangsan");
        p1.setAge(10);

        Person p2=new Person();
        p2.setName("lisi");
        p2.setAge(20);
        list.add(p1);
        list.add(p2);
        System.out.println(list);

        List<Person> personList = list.stream().sorted((p3,p4)->{
            return p4.getAge().compareTo(p3.getAge());
        }).collect(Collectors.toList());

        System.out.println("order:"+personList);


    }

}
