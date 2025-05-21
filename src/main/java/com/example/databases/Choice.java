package com.example.databases;

public class Choice {
    private String name;
    private double stat;
    public Choice(String name, double stat)
    {
        this.name = name;
        this.stat = stat;
    }

    public String getName() {
        return name;
    }

    public double getStat() {
        return stat;
    }
}
