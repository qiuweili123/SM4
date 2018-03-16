package com.groovy

class Demo {

    String name1 = "zhangsan";

    public static void main(String[] args) {
        def age = 1;
        def name = "hello";
        def str1 = 'name $age'
        Demo demo = new Demo();

        println demo.name1;

        Person person = new Person();
        person.setName("name:: lisi")
        //println person.methodA(person.name);
        //单引号不解析$
        def s1 = 'i am 100 $ dolloar'
        println s1;
        //双引号能够解析$
        println person.name + "##$name" + fun3()
        testFor()
        testSwitch()
        testThridOperator()

        testConllection()
    }

    def methodA = { p1 -> println "p1 value::" + p1 }

    def methodB = { p1 -> println "p2 value::" + p2 }

    static String fun3() {

        return "hello"
    }

    static testFor() {
        for (i in 1..6) {
            println "test" + i;
        }
    }

    static testSwitch() {
        int i = 3;
        switch (i) {
            case 1..10:
                println "out 1"
                break
            case 2:
                println "out 2"
                break
            default:
                println "defualt"

        }

    }

    static testThridOperator() {
        Person person = new Person();
        person.setName("lisi");
        println person?.name
    }
    //迭代
    //lst = ['顺丰海淘','就是好','只卖正品']
    //println lst + ['你说呢']
    static testConllection() {
        def list = ["1", 2, 4, "hell0"];
        println list
        list.each { println "item::$it" }
        def lst = ['顺丰海淘', '就是好', '只卖正品']
        println lst + ['你说呢']

        //map
        def map = ["n1": 23, "n2": "num1"]

        map.each { println "$it.key::value::$it.value" }


    }

}
//每个类都有一个默认的run方法，定义的变量在里面执行
