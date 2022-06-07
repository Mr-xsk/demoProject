package com.xsk.test.service;

import com.xsk.test.bean.Person;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class test {

    public static void main(String[] args) {
//        List<String> list = Arrays.asList("1","2","3");
//        list.forEach(str -> {
//            if (str.equals("1")) System.out.println("success");
//            else System.out.println("fail");
//        });

        //创建对象
        Person p1 = new Person("t1",15);
        Person p2 = new Person("t2",19);
        Person p3 = new Person("t3",5);
        Person p4 = new Person("t4",11);
        Person p5 = new Person("t5",25);
        Person p6 = new Person("t6",15);

        //集合中添加对象
        List<Person> list = new ArrayList<>();
        list.add(p1);
        list.add(p2);
        list.add(p3);
        list.add(p4);
        list.add(p5);
        list.add(p6);

        list.forEach(str -> System.out.println(str));

        list.sort(Comparator.comparingInt(Person::getAge));

        list.sort((q1,q2) -> q2.getAge() - q1.getAge());

        list.forEach(str -> System.out.println(str));

        System.out.println(16 << 2);
    }
}
