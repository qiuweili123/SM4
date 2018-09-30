package com.java.jdk8.test.stream;

/**
 * Created by Administrator on 17-6-27.
 */
public class Person {
    private String name;
    private String cardId;
    private Integer age;

    public Person() {
    }

    public Person(String name, String cardId, Integer age) {
        this.name = name;
        this.cardId = cardId;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
