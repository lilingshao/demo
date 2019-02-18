package com.example.demo.customAop;

public class InitTest1 {

    public int getId() {
        return id;
    }
    @Init(id = 1)
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    @Init(value = "li")
    public void setName(String name) {
        this.name = name;
    }

    private int id;
    private String name;
}
