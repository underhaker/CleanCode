package com.fmi.cleancode.god.planets;

import com.fmi.cleancode.god.entities.Entity;
import com.fmi.cleancode.god.utils.RandomNumberGenerator;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Planet {
    private String name;
    private List<Entity> population;
    //Concurrent ArrayList for population on planet
    private boolean isDestroyed;

    public Planet() {
        population = new CopyOnWriteArrayList<>();
        this.isDestroyed = false;
        RandomNumberGenerator generator = new RandomNumberGenerator();
        this.name = generator.generatePlanetName();

    }

    //default constructor
    public List<Entity> getPopulation() {
        return population;
    }

    public void setPopulation(List<Entity> l) {
        this.population = l;
    }

    //returns a list of the population
    public String getName() {
        return this.name;
    }
    //returns size of population

    //returns the name of the planet
    public int getPopulationCount() {
        return population.size();
    }
    //sets this planet's population

    public boolean isDestroyed() {
        return this.isDestroyed;
    }
    //returns true if planet is destroyed

    public void setDestroyed() {
        this.isDestroyed = true;
    }
    //changes planet to destroyed

    public void addPopulation(Entity e) {
        population.add(e);
    }
    //adds an ENTITY to population

    public void destroyPopulation() {
        population.clear();
        System.out.println(this.getName() + " had its population destroyed..");
    }
    //removes population from planet

}
