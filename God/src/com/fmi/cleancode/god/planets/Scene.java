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
    private List<Planet> planets = new CopyOnWriteArrayList<>();
    private RandomNumberGenerator rng = new RandomNumberGenerator();

    public List<Planet> getPlanets() {
        return planets;
    }

    //adds a randomly generated entity to a planet
    public void createCreature(Planet planet, EntityType entityType) {
        switch (entityType) {
            case ENTITY:
                planet.addPopulation(new Entity(rng.generateName(EntityType.ENTITY), 2 * rng.generateStrength(), rng.generateNumber(), rng.generateNumber(), new Point2D(rng.generateCoordinate(), rng.generateCoordinate()), rng.generateStrength(), State.UNKNOWN));
                break;
            case ANIMAL:
                planet.addPopulation(new Animal(rng.generateName(EntityType.ANIMAL), 2 * rng.generateStrength(), rng.generateNumber(), rng.generateNumber(), new Point2D(rng.generateCoordinate(), rng.generateCoordinate()), rng.generateStrength(), State.UNKNOWN));
                break;
            case HUMAN:
                planet.addPopulation(new Human(rng.generateName(EntityType.HUMAN), 2 * rng.generateStrength(), rng.generateNumber(), rng.generateNumber(), new Point2D(rng.generateCoordinate(), rng.generateCoordinate()), rng.generateStrength(), State.UNKNOWN));
                break;
            case GOD:
                planet.addPopulation(new God(rng.generateName(EntityType.GOD), 2 * rng.generateStrength(), rng.generateNumber(), rng.generateNumber(), new Point2D(rng.generateCoordinate(), rng.generateCoordinate()), rng.generateStrength(), State.UNKNOWN));
                break;
            default:
                break;
        }

    }

    public void createPlanet() {
        Planet planet = new Planet();
        if (planet.getName() != null) {
            planets.add(planet);
            System.out.println("a new planet " + planet.getName() + " has been created...");
        }
    }

    public void destroyPlanet(Planet planet) {
        planets.remove(planet);
        planet.destroyPopulation();
        planet.setDestroyed(true);
        System.out.println(planet.getName() + " has been destroyed..");
    }
}
