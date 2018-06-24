package com.fmi.cleancode.god.entities;

import com.fmi.cleancode.god.enums.EntityType;
import com.fmi.cleancode.god.planets.Planet;
import com.fmi.cleancode.god.planets.Scene;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.*;

public class GodTest {
    private God god;
    private Scene scene;
    @Before
    public void setUp() throws Exception {
        scene = new Scene();
        for (int i = 0; i < 5; i++) {
            scene.createPlanet();
        }
        this.god = new God();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testIfPlanetIsAddedToSceneByGod() throws Exception {
        int originalPlanetSize = this.scene.getPlanets().size();
        this.god.createPlanet(this.scene.getPlanets());
        assertTrue(this.scene.getPlanets().size() > originalPlanetSize);
    }

    @Test
    public void testPlanetPopulationIsDestroyedByGod() throws Exception {
        Planet planet = this.scene.getPlanets().get(0);
        this.scene.createCreature(planet, EntityType.HUMAN);
        int originalPopulationCount = planet.getPopulationCount();
        this.god.destroyPopulation(planet);
        assertTrue(planet.getPopulationCount() == 0);
    }

}