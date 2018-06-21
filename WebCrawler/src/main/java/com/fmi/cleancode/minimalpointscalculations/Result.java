package com.fmi.cleancode.minimalpointscalculations;

public class Result {
    private Point point;
    private Double distance;

    public Result(Point point, Double distance) {
        this.point = point;
        this.distance = distance;
    }

    public Point getPoint() {
        return this.point;
    }

    public Double getDistance() {
        return this.distance;
    }
}
