package com.fmi.cleancode.god.utils;

import com.fmi.cleancode.god.enums.EntityType;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomNumberGenerator {
    private static final String[] PLANET_NAMES = {"Mercury", "Venus", "Earth", "Mars", "Jupiter", "Saturn", "Uranus", "Neptune", "Pluto", "Gaspra", "Eros", "Ida"};
    private Random generator;
    private List<String> visited;


    public RandomNumberGenerator() {
        this.visited = new ArrayList<>();
        this.generator = new Random();
    }

    public String generatePlanetName() {
        int num;
        if (visited.size() == PLANET_NAMES.length) {
            System.out.println("No more planets can be added");
            return null;
        }
        while (true) {
            num = generator.nextInt(PLANET_NAMES.length);
            if (!visited.contains(PLANET_NAMES[num])) {
                break;
            }
        }
        String name = PLANET_NAMES[num];
        return name + generator.nextInt(PLANET_NAMES.length);
    }

    public int generateNumberRange(int range) {
        return generator.nextInt(range);
    }

    public int generateCoordinate() {
        return generator.nextInt(50);
    }

    public int generateNumber() {
        return generator.nextInt(100);
    }

    public String generateName(EntityType et) {
        int num = generator.nextInt(1000);
        return et.toString() + num;
    }

    public double generateStrength() {
        return (double) generator.nextInt(200);
    }
}
