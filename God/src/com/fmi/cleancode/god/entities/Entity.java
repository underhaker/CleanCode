package com.fmi.cleancode.god.entities;


import com.fmi.cleancode.god.enums.EntityType;
import com.fmi.cleancode.god.enums.State;
import com.fmi.cleancode.god.utils.Point2D;
import com.fmi.cleancode.god.utils.RandomNumberGenerator;

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
    private RandomNumberGenerator generator;

    public Entity() {
        this.generator = new RandomNumberGenerator();
        this.name = generator.generateName(EntityType.ENTITY);
        this.energy = generator.generateStrength() * 2;
        this.size = generator.generateNumber();
        this.weight = generator.generateNumber();
        this.position = new Point2D(generator.generateCoordinate(), generator.generateCoordinate());
        this.strength = generator.generateStrength();
        this.state = State.UNKNOWN;
        this.isAlive = true;
        this.entity = EntityType.ENTITY;
    }

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
        return this.state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public boolean isAlive() {
        return this.isAlive;
    }

    public void setAlive(boolean alive) {
        this.isAlive = alive;
    }

    public void attack(Entity ent) {
        this.setState(State.ATTACKING);
        System.out.println("status:" + this.getName() + " attacked " + ent.getName() + " for " + this.getStrength()
                + " damage..");
        ent.setEnergy(ent.getEnergy() - this.getStrength());
        if (ent.getEnergy() <= 0) {
            System.out.println("status:" + ent.getName() + " has died...");
            this.isAlive = false;
        }
    }

    //moves the ENTITY to X position(X is randomly generated)
    public void move() {
        int randomPosition = generator.generateNumberRange(2);
        int deltaPositionX;
        int deltaPositionY;
        if (randomPosition % 2 == 0) {
            deltaPositionX = position.getX() + generator.generateCoordinate();
            deltaPositionY = position.getY() + generator.generateCoordinate();
            if (deltaPositionX <= PLANET_LENGTH && deltaPositionY <= PLANET_LENGTH) {
                position.setX(deltaPositionX);
                position.setY(deltaPositionY);
            }
        } else if (randomPosition % 2 == 1) {
            deltaPositionX = position.getX() - generator.generateCoordinate();
            deltaPositionY = position.getY() - generator.generateCoordinate();
            if (deltaPositionX >= 0 && deltaPositionY >= 0) {
                position.setX(deltaPositionX);
                position.setY(deltaPositionY);
            }
        }
        this.setState(State.MOVING);
    }
}
