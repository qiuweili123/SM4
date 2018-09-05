package com.spring.aop;

import org.springframework.stereotype.Component;

/**
 * Created by lenovo on 2017/7/6.
 */
@Component
public class User {
    private Long id;
    private String name;
    @Money
    private Long money;

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

    public Long getMoney() {
        return money;
    }

    @Money(name = "testttt")
    public void setMoney(Long money) {
        System.out.println("############ret==" + money);
        this.money = money;
    }
}
