package com.fmi.cleancode.god.planets;

import com.fmi.cleancode.god.entities.Animal;
import com.fmi.cleancode.god.entities.Entity;
import com.fmi.cleancode.god.entities.God;
import com.fmi.cleancode.god.entities.Human;
import com.fmi.cleancode.god.enums.EntityType;
import com.fmi.cleancode.god.enums.State;
import com.fmi.cleancode.god.utils.Point2D;
import com.fmi.cleancode.god.utils.RandomNumberGenerator;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Scene {
    private List<Planet> planets = new CopyOnWriteArrayList<Planet>();

    public List<Planet> getPlanets() {
        return planets;
    }

    //returns an ArrayList of all planets
    public void createCreature(Planet pl, EntityType et) {
        RandomNumberGenerator rng = new RandomNumberGenerator();
        switch (et) {
            case ENTITY:
                pl.addPopulation(new Entity(rng.generateName(EntityType.ENTITY), 2 * rng.generateStrength(), rng.generateNumber(), rng.generateNumber(), new Point2D(rng.generateCoordinate(), rng.generateCoordinate()), rng.generateStrength(), State.UNKNOWN));
                break;
            case ANIMAL:
                pl.addPopulation(new Animal(rng.generateName(EntityType.ANIMAL), 2 * rng.generateStrength(), rng.generateNumber(), rng.generateNumber(), new Point2D(rng.generateCoordinate(), rng.generateCoordinate()), rng.generateStrength(), State.UNKNOWN));
                break;
            case HUMAN:
                pl.addPopulation(new Human(rng.generateName(EntityType.HUMAN), 2 * rng.generateStrength(), rng.generateNumber(), rng.generateNumber(), new Point2D(rng.generateCoordinate(), rng.generateCoordinate()), rng.generateStrength(), State.UNKNOWN));
                break;
            case GOD:
                pl.addPopulation(new God(rng.generateName(EntityType.GOD), 2 * rng.generateStrength(), rng.generateNumber(), rng.generateNumber(), new Point2D(rng.generateCoordinate(), rng.generateCoordinate()), rng.generateStrength(), State.UNKNOWN));
                break;
            default:
                break;
        }

    }

    //creates a new ENTITY(or subclass unit) with random variables and adds it to the planet's population
    public void createPlanet() {
        Planet planet = new Planet();
        if (planet.getName() != null) {
            planets.add(planet);
            System.out.println("a new planet " + planet.getName() + " has been created...");
        }
    }

    //adds a new planet(created with random variables) to the ArrayList
    public void destroyPlanet(Planet planet) {
        planets.remove(planet);
        planet.destroyPopulation();
        planet.setDestroyed();
        System.out.println(planet.getName() + " has been destroyed..");
    }
    //removes the given planet from the ArrayList
}
