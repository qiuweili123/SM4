package com.sh.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class GenericDao<T extends java.io.Serializable> {


    @Autowired
    @Qualifier("masterSessionFactory")
    private SessionFactory masterSessionFactory;


    @Autowired
    @Qualifier("slaveSessionFactory")
    private SessionFactory slaveSessionFactory;


    private SessionFactory sessionFactory;


    private void fixSession() {
        String name = this.getClass().getName();
        System.out.println("className=" + name);
        /**
         * 如果是master 包下的dao 全部指定为 masterSessionFactory
         */
        if (name.indexOf("com.sh.dao.master") > -1) {
            sessionFactory = masterSessionFactory;
        }
        /**
         * 默认的dao是 slaveSessionFactory 下的库
         */
        else {
            sessionFactory = slaveSessionFactory;
        }
    }

    public Session getSession() {
        fixSession();
        return sessionFactory.getCurrentSession();
    }
}