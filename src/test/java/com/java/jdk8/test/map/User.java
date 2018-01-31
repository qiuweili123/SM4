package com.java.jdk8.test.map;

/**
 * Created by lenovo on 2018/1/24.
 */
public class User {
    private Long id;

    public User(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
