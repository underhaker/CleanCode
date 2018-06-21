package com.fmi.cleancode.god.entities;


import com.fmi.cleancode.god.utils.Point2D;
import com.fmi.cleancode.god.utils.RandomNumberGenerator;
import com.fmi.cleancode.god.enums.EntityType;
import com.fmi.cleancode.god.enums.State;

public class Entity {
    final static private int PLANET_LENGTH = 400;
    protected String name;
    protected double energy;
    protected double size;
    protected double weight;
    protected Point2D position;
    protected double strength;
    protected State state;
    protected EntityType entity;
    private boolean isAlive = true;

    //upper bound for entities to move
    public Entity() {
        RandomNumberGenerator rng = new RandomNumberGenerator();
        this.name = rng.generateName(EntityType.ENTITY);
        this.energy = rng.generateStrength() * 2;
        this.size = rng.generateNumber();
        this.weight = rng.generateNumber();
        this.position = new Point2D(rng.generateCoordinate(), rng.generateCoordinate());
        this.strength = rng.generateStrength();
        this.state = State.UNKNOWN;
        this.isAlive = true;
        this.entity = EntityType.ENTITY;
    }

    //default constructor with random values
    public Entity(String name, double energy, double size, double weight, Point2D position, double strength, State state) {
        this.name = name;
        this.energy = energy;
        this.size = size;
        this.weight = weight;
        this.position = position;
        this.strength = strength;
        this.state = state;
        this.entity = EntityType.ENTITY;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EntityType getEntity() {
        return entity;
    }

    public double getEnergy() {
        return energy;
    }

    public void setEnergy(double energy) {
        this.energy = energy;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public Point2D getPosition() {
        return position;
    }

    public void setPosition(Point2D position) {
        this.position = position;
    }

    public double getStrength() {
        return strength;
    }

    public void setStrength(double strength) {
        this.strength = strength;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public void attack(Entity ent) {
        this.setState(State.ATTACKING);
        System.out.println("status:" + this.getName() + " attacked " + ent.getName() + " for " + this.getStrength()
                + " damage..");
        ent.setEnergy(ent.getEnergy() - this.getStrength());
        if (ent.getEnergy() <= 0) {
            System.out.println("status:" + ent.getName() + " has died...");
            isAlive = false;
        }
    }

    //moves the ENTITY to X position(X is randomly generated)
    public void move() {
        RandomNumberGenerator rng = new RandomNumberGenerator();
        int randomPosition = rng.generateNumberRange(2);
        int deltaPositionX;
        int deltaPositionY;
        if (randomPosition % 2 == 0) {
            deltaPositionX = position.getX() + rng.generateCoordinate();
            deltaPositionY = position.getY() + rng.generateCoordinate();
            if (deltaPositionX <= PLANET_LENGTH && deltaPositionY <= PLANET_LENGTH) {
                position.setX(deltaPositionX);
                position.setY(deltaPositionY);
            }
        } else if (randomPosition % 2 == 1) {
            deltaPositionX = position.getX() - rng.generateCoordinate();
            deltaPositionY = position.getY() - rng.generateCoordinate();
            if (deltaPositionX >= 0 && deltaPositionY >= 0) {
                position.setX(deltaPositionX);
                position.setY(deltaPositionY);
            }
        }
        this.setState(State.MOVING);
    }
}
