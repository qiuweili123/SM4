package com.java.jdk8.test.optional;

import com.commons.test.Address;
import com.commons.test.User;

import java.util.Optional;
import java.util.function.Supplier;

/**
 * Created by lenovo on 2017/6/27.
 * 它可以保存类型T的值，或者仅仅保存null。Optional提供很多有用的方法，这样我们就不用显式进行空值检测
 */
public class TestOptionnal {
    public static void main(String[] args) {
        Optional<String> fullName = Optional.ofNullable(null);
        System.out.println("Full Name is set? " + fullName.isPresent());
        System.out.println("Full Name: " + fullName.orElseGet(() -> "[none]"));
        System.out.println(fullName.map(s -> "Hey " + s + "!").orElse("Hey Stranger!"));
        System.out.println(fullName.orElse(null));
        User user = null;
        User u2 = Optional.ofNullable(user).orElseGet(() -> {
            User u = new User();
            u.setAge(30);
            return u;
        });
        System.out.println("##" + u2.getAge());
        //当u2不为空时执行特定操作
        Optional.ofNullable(u2).ifPresent(u -> {
            u.setName("zhangsan");
            show(u2);
        });
        System.out.println(u2.getName());
        String s = Optional.ofNullable(u2).map(u -> u.getName()).get();
        System.out.println("name::" + s);
        Address address = new Address();

        /*u2.setAddress(address);
         if(u2!=null){
             Address address1 = u2.getAddress();
             if(address1!=null){
                 return address1.getName();
             }
         }
         return "hell"
  以上写法用下面的方式进行替换.
  一定要注意的是这里的map是返回的是Optional表达式，这使我们能够在一行中进行多个 map 操作。Null 检查是在底层自动处理的。
         */
        String s1 = Optional.ofNullable(u2)
                .map(u -> {u.getAddress();
                    System.out.println(u.getAddress()); return u;})
                .map(address1 -> address1.getName())
                .orElseGet(() -> {
                    return "hello";
                });
        //第二种处理方式
        System.out.println("--------------分割线---------------");
      resolve(()->u2.getAddress().getName()).ifPresent(System.out::println);
        System.out.println("--------------分割线---------------");
        System.out.println("s1==="+s1);
        System.out.println("--------------分割线---------------");
        address.setName("北京市");
        Optional.ofNullable(u2)
                .map(u -> u.getAddress())
                .map(address1 -> address1.getName())
                .ifPresent(name -> System.out.println(name));
    }
    //有一种实现相同作用的方式就是通过利用一个 supplier 函数来解决嵌套路径的问题：

    public static <T> Optional<T> resolve(Supplier<T> resolver) {
        try {

//有可能任何时候出现异常
            T result = resolver.get();
            return Optional.ofNullable(result);
        }
        catch (NullPointerException e) {
            //在这种情况下，该异常将会被捕获，而该方法会返回 Optional.empty()。
            return Optional.empty();
        }
    }
    public  static  void show(User user){

        System.out.println("show::"+user.getName());
    }

}
