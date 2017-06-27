package com.java.jdk8.test.stream;

import java.util.Random;
import java.util.function.Supplier;

public class PersonSupplier implements Supplier<Person> {
    private int index = 0;
    private Random random = new Random();

    @Override
    public Person get() {
        System.out.println("=-------" + index);
        return new Person(String.valueOf(index++), "StormTestUser" + index, random.nextInt(100));
    }
}