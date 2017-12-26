package com.spring.scan.test;

import com.spring.aop.Money;
import org.springframework.stereotype.Component;

@CustomizeComponent
@Component
public class ScanClass1 {
    @Money
    public void print(String msg) {
        System.out.println("scanClass1::" + msg);
    }
}