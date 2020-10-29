package com.basic

object Hello {
  //def 定义函数：如果定义的函数有返回值，必须加"=";没有返回类型，系统会自动推断；
  def main(args: Array[String]): Unit = {
    println("hello ...");
    //变量声明
    var str: String = "syr";
    //声明常量,但是
    val strConst = "1234";

    println("str::" + str + "##" + strConst)
    //逻辑表达式
    //双分支
    if (true) "d" else "sd"
    //scala没有switch
    val name = printMsg("zhansan", "北京")
    //带参函数
    val name2 = printMsg(addr = "shanxi", name = "cityu")
    println("函数返回:" + name)
    //使用半生对象进行数组赋值  定长数组
    var array = Array(1, 2, 3);
    array.length; //数组长度

    for (arr <- array) {
      println(arr)
    }
    array.foreach(arr => {
      println(s"lam:=${arr}")
    })
    val sum = array.sum;

    println(s"sum:${sum}")

    //元组
    val tuple = (123, "张三");
    tuple._1;
    //获取元组元素
    //第二种定义方式，推荐
    val (name1, age) = ("张三", 123);

    println("def name:" + getName())
  }

  //定义的 函数可以不加返回值
  def printMsg(name: String, addr: String) = {
    //传统方式
    // "hello," + name;
    //使用函数拼接
    s"name：${name},addr:${addr}"

  }


  def getName() = {
    var name = "zhangsna";
    name;
  }
}
