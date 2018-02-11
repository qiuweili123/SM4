package com.spring.bean;

import org.springframework.stereotype.Component;

/**
 * Created by lenovo on 2018/1/4.
 */
@Component
public class RequiredValidator implements Validator {
    public RequiredValidator(){
        System.out.println("--------------RequiredValidator-----------");
    }
    @Override
    public void validate(String stri) {

    }
}
