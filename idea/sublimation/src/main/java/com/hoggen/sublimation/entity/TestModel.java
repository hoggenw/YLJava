package com.hoggen.sublimation.entity;

public class TestModel {

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public boolean isFoo() {
        return foo;
    }

    public void setFoo(boolean foo) {
        this.foo = foo;
    }

    private  String name;
    private  long userId;
    private  boolean  foo;
}
