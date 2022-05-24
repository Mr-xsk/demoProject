package com.xsk.test.bean;

public class Result {

    private String name;

    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Result(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public Result() {
    }
}
