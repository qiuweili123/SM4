package com.spring.scan.test;

import com.spring.aop.Money;
import org.springframework.stereotype.Component;

@CustomizeComponent
public interface ScanClass2 {
    @Money
    public void print(String msg);
}