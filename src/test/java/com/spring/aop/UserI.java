package com.spring.aop;

import org.springframework.stereotype.Component;

@Component
public interface UserI {

    @Money
    void getMoney2();
}
