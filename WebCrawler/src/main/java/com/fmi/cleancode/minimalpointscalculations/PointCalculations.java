package com.fmi.cleancode.minimalpointscalculations;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

import static java.lang.Math.sqrt;

public class PointCalculations {

    public static List<Point> generatePoints() {
        List<Point> listPoints = new ArrayList<Point>();
        Random rand = new Random();

        for (int i = 0; i < MAX_POINTS; i++) {
            int x = rand.nextInt(MAX_POINT_SIZE);
            int y = rand.nextInt(MAX_POINT_SIZE);
            Point p = new Point(x, y);
            listPoints.add(p);
        }
        return listPoints;
    }

    public static Map<Point, Point> getNearestPoints(final List<Point> generatedPoints) {
        final Map<Point, Point> hm = new ConcurrentHashMap<Point, Point>();
        Thread[] threads = new Thread[THREAD_COUNT];
        final int slice = MAX_POINTS / THREAD_COUNT;

        for (int i = 0; i < THREAD_COUNT; i++) {
            final int index = i;
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    doCalculations(generatedPoints, index * slice, (index + 1) * slice, hm);
                }
            });
            threads[i].start();
        }

        for (int i = 0; i < THREAD_COUNT; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return hm;
    }

    private static double calculateDistance(Point p1, Point p2) {
        double deltaX = p1.getX() - p2.getX();
        double deltaY = p1.getY() - p2.getY();
        double distance = sqrt((deltaX * deltaX) + (deltaY * deltaY));
        return distance;
    }

    public static void doCalculations(List<Point> inPoints, int indexFrom, int indexTo, Map<Point, Point> outMap) {
        double distance = 0.0, minDistance = MIN_DISTANCE;
        Point p1 = null, p2 = null, nearestPoint = null;

        for (int i = indexFrom; i < indexTo; i++) {
            p1 = inPoints.get(i);
            minDistance = MIN_DISTANCE;
            for (int j = 0; j < inPoints.size(); j++) {
                p2 = inPoints.get(j);
                if (p1.equals(p2))
                    continue;
                distance = calculateDistance(p1, p2);
                if (distance <= minDistance) {
                    minDistance = distance;
                    nearestPoint = p2;
                }
            }
            outMap.put(p1, nearestPoint);
        }
    }

    final static int MAX_POINTS = 10_000;
    final static int MAX_POINT_SIZE = 10_000;
    final static int THREAD_COUNT = 10;
    final static double MIN_DISTANCE = MAX_POINT_SIZE * MAX_POINT_SIZE;

    public static void Run() {
        long startTime = System.currentTimeMillis();
        Map<Point, Point> hm = getNearestPoints(generatePoints());
        System.out.println("number of threads: " + THREAD_COUNT);
        System.out.println("number of points in hashmap: " + hm.size());
        System.out.println("time:" + (System.currentTimeMillis() - startTime) / 1000.0 + " seconds.");
    }

    public static void main(String[] args) {
        Run();
    }
}
