package com.spring.scan.test;

import com.spring.aop.Money;
import com.spring.scan.test.CustomizeComponent;

@CustomizeComponent
public interface ScanClass3 {
    @Money
    public void print(String msg);
}