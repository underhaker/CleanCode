package com.fmi.cleancode.god.utils;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Point2DTest {

    private Point2D point2D;

    @Before
    public void setUp() throws Exception {
        this.point2D = new Point2D(0, 0);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void checkEuclidianDistance() throws Exception {
        Point2D point1 = new Point2D(2, 3);
        Point2D point2 = new Point2D(7, 6);
        assertEquals(point2D.getDistance(point1, point2), 5.831, 0.5);
    }

}