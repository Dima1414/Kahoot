package com.example.databases;

import java.util.ArrayList;

public class AvePnts {
    private String name;
    private double avgPoints;
    public AvePnts(String name, double avgPoints)
    {
        this.name = name;
        this.avgPoints = avgPoints;
    }

    public String getName() {
        return name;
    }

    public double getAvgPoints() {
        return avgPoints;
    }
}
