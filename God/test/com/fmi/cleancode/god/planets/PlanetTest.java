package com.fmi.cleancode.god.planets;

import com.fmi.cleancode.god.enums.EntityType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

public class PlanetTest {
    private Planet planet;

    @Before
    public void setUp() throws Exception {
        Scene scene = new Scene();
        scene.createPlanet();
        this.planet = scene.getPlanets().get(0);
        for (int i = 0; i < 10; i++) {
            scene.createCreature(this.planet, EntityType.ANIMAL);
        }
    }

    @Test
    public void testIfPlanetPopulationIsDestroyed() throws Exception {
        this.planet.destroyPopulation();
        assertTrue(this.planet.getPopulationCount() == 0);
    }

    @After
    public void tearDown() throws Exception {
    }

}