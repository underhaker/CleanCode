package com.fmi.cleancode.god.entities;


import com.fmi.cleancode.god.enums.EntityType;
import com.fmi.cleancode.god.enums.State;
import com.fmi.cleancode.god.planets.Planet;
import com.fmi.cleancode.god.utils.Point2D;
import com.fmi.cleancode.god.utils.RandomNumberGenerator;

import java.util.List;

public class God extends Human {

    public God(String name, double energy, double size, double weight, Point2D position, double strength, State state) {
        super(name, energy, size, weight, position, strength, state);
        this.entity = EntityType.ENTITY;
    }

    public God() {
        super();
        RandomNumberGenerator generator = new RandomNumberGenerator();
        this.name = generator.generateName(EntityType.GOD);
        this.entity = EntityType.GOD;
    }

    public void createPlanet(List<Planet> planets) {
        Planet planet = new Planet();
        if (planet.getName() != null)
            planets.add(planet);
        System.out.println("a new planet " + planet.getName() + " has been created...");
    }


    public void destroyPopulation(Planet planet) {
        planet.destroyPopulation();
    }
}
