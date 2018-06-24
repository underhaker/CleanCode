package com.fmi.cleancode.god.planets;

import com.fmi.cleancode.god.entities.Entity;
import com.fmi.cleancode.god.utils.RandomNumberGenerator;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Planet {
    private String name;
    private List<Entity> population;
    private boolean isDestroyed;

    public Planet() {
        population = new CopyOnWriteArrayList<>();
        this.isDestroyed = false;
        RandomNumberGenerator generator = new RandomNumberGenerator();
        this.name = generator.generatePlanetName();

    }

    public List<Entity> getPopulation() {
        return population;
    }

    public void setPopulation(List<Entity> population) {
        this.population = population;
    }

    public String getName() {
        return this.name;
    }

    public int getPopulationCount() {
        return population.size();
    }

    public boolean isDestroyed() {
        return this.isDestroyed;
    }

    public void setDestroyed(boolean isDestroyed) {
        this.isDestroyed = isDestroyed;
    }

    public void addPopulation(Entity e) {
        population.add(e);
    }

    public void destroyPopulation() {
        population.clear();
        System.out.println(this.getName() + " had its population destroyed..");
    }
    //removes population from planet

}
