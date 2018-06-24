package com.fmi.cleancode.god.utils;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class RandomNumberGeneratorTest {
    private static final String[] PLANET_NAMES = {"Mercury", "Venus", "Earth", "Mars", "Jupiter", "Saturn", "Uranus", "Neptune", "Pluto", "Gaspra", "Eros", "Ida"};
    private RandomNumberGenerator generator;
    @Before
    public void setUp() throws Exception {
        this.generator = new RandomNumberGenerator();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testGeneratedPlanetNameIsInArray() throws Exception {
        String planetName = this.generator.generatePlanetName();
        boolean isNameUsed = false;
        for (String planet: PLANET_NAMES) {
            if (planetName.contains(planet)) {
                isNameUsed = true;
                break;
            }
        }
        assertTrue(isNameUsed);
    }

}