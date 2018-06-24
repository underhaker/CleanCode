package com.fmi.cleancode.god.utils;

public class Point2D {
    private int x;
    private int y;

    public Point2D(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return this.y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public double getDistance(Point2D point1, Point2D point2) {
        double distance;
        int deltaX = point1.getX() - point2.getX();
        int deltaY = point1.getY() - point2.getY();
        distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY);
        return distance;
    }

    @Override
    public String toString() {
        return "Point2D{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
