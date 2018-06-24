package com.fmi.cleancode.god.entities;

import com.fmi.cleancode.god.utils.Point2D;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class EntityTest {
    private Entity entity;

    @Before
    public void setUp() throws Exception {
        this.entity = new Entity();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testIfAnAttackDepletesSecondEntityEnergy() throws Exception {
        Entity secondEntity = new Entity();
        double secondEntityEnergy = secondEntity.getEnergy();
        this.entity.attack(secondEntity);
        boolean isEnergyChanged = secondEntityEnergy != secondEntity.getEnergy();
        assertTrue(isEnergyChanged);
    }

    @Test
    public void testIfEntityPositionIsChanged() throws Exception {
        Point2D originalPosition = new Point2D(this.entity.getPosition().getX(), this.entity.getPosition().getY());
        System.out.println(originalPosition);
        this.entity.move();
        boolean isPositionChanged = this.entity.getPosition().getX() != originalPosition.getX() && this.entity.getPosition().getY() != originalPosition.getY();
        assertTrue(isPositionChanged);
    }

}