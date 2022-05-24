package com.xsk.test.service;

import java.util.Arrays;
import java.util.List;

public class test {

    public static void main(String[] args) {
        List<String> list = Arrays.asList("1","2","3");
        list.forEach(str -> {
            if (str.equals("1")) System.out.println("success");
            else System.out.println("fail");
        });
    }
}
