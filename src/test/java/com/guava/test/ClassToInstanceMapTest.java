package com.guava.test;

import com.google.common.collect.ClassToInstanceMap;
import com.google.common.collect.MutableClassToInstanceMap;

/**
 * Created by lenovo on 2017/8/25.
 */
public class ClassToInstanceMapTest {
    public static void main(String[] args) {
        ClassToInstanceMap<Person> classToInstanceMap = MutableClassToInstanceMap.create();

        Person person = new Person();
        person.setName("zhangsan");
        Person p2 = new Person();
        p2.setName("lisi");
        classToInstanceMap.putInstance(Person.class, person);
        classToInstanceMap.putInstance(Person.class, p2);
        System.out.println(classToInstanceMap.getInstance(Person.class).getName());

    }

}
