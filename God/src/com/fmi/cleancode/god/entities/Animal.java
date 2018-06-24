package com.fmi.cleancode.god.entities;


import com.fmi.cleancode.god.utils.Point2D;
import com.fmi.cleancode.god.utils.RandomNumberGenerator;
import com.fmi.cleancode.god.enums.EntityType;
import com.fmi.cleancode.god.enums.State;

public class Animal extends Entity {

    public Animal() {
        super();
        RandomNumberGenerator rng = new RandomNumberGenerator();
        this.name = rng.generateName(EntityType.ANIMAL);
        this.entity = EntityType.ANIMAL;
    }

    public Animal(String name, double energy, double size, double weight, Point2D position, double strength, State state) {
        super(name, energy, size, weight, position, strength, state);
        this.entity = EntityType.ENTITY;
    }

    public void eat() {
        this.setState(State.EATING);
        System.out.println("status:" + this.getName() + " is " + this.getState() + "...");
    }

    public void sleep() {
        this.setState(State.SLEEPING);
        System.out.println("status:" + this.getName() + " is " + this.getState() + "...");
    }

    public void searchingForFood() {
        this.setState(State.SEARCHING_FOR_FOOD);
        System.out.println("status:" + this.getName() + " is " + this.getState() + "...");
    }

}
