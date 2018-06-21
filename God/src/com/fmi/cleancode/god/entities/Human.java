package com.fmi.cleancode.god.entities;


import com.fmi.cleancode.god.utils.Point2D;
import com.fmi.cleancode.god.utils.RandomNumberGenerator;
import com.fmi.cleancode.god.enums.EntityType;
import com.fmi.cleancode.god.enums.State;

public class Human extends Animal {
    public Human() {
        super();
        RandomNumberGenerator rng = new RandomNumberGenerator();
        this.name = rng.generateName(EntityType.HUMAN);
        this.entity = EntityType.HUMAN;
    }

    //default constructor with random values set
    public Human(String name, double energy, double size, double weight, Point2D position, double strength, State state) {
        super(name, energy, size, weight, position, strength, state);
        this.entity = EntityType.HUMAN;
    }

    public void analyze() {
        this.setState(State.ANALYZING);
        System.out.println("status:" + this.getName() + " is " + this.getState() + "...");
    }
    //setting the state to ANALYZING
}
