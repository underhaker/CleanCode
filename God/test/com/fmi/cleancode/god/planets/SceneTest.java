package com.fmi.cleancode.god.planets;

import com.fmi.cleancode.god.enums.EntityType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class SceneTest {
    private Scene scene;

    @Before
    public void setUp() throws Exception {
        this.scene = new Scene();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testIfEntityIsAddedToAPlanet() throws Exception {
        this.scene.createPlanet();
        Planet planet = this.scene.getPlanets().get(0);
        this.scene.createCreature(planet, EntityType.HUMAN);
        boolean isEntityAdded = planet.getPopulationCount() == 1 && planet.getPopulation().get(0).getEntity() == EntityType.HUMAN;
        assertTrue(isEntityAdded);
    }

    @Test
    public void testIfPlanetIsCreated() throws Exception {
        this.scene.createPlanet();
        assertTrue(this.scene.getPlanets().size() > 0);
    }

    @Test
    public void testIfPlanetIsDestroyed() throws Exception {
        this.scene.createPlanet();
        Planet planet = this.scene.getPlanets().get(0);
        this.scene.destroyPlanet(planet);
        assertTrue(this.scene.getPlanets().size() == 0);
    }

}