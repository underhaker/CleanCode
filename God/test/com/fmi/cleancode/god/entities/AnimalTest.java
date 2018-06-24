package com.fmi.cleancode.god.entities;

import com.fmi.cleancode.god.enums.State;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AnimalTest {
    private Animal animal;
    @Before
    public void setUp() throws Exception {
        this.animal = new Animal();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testIfStateIsChangedToEating() throws Exception {
        this.animal.eat();
        assertEquals(this.animal.getState(), State.EATING);
    }

    @Test
    public void testIfStateIsChangedToSleeping() throws Exception {
        this.animal.sleep();
        assertEquals(this.animal.getState(), State.SLEEPING);
    }

    @Test
    public void testIfStateIsChangedToSearchingForFood() throws Exception {
        this.animal.searchingForFood();
        assertEquals(this.animal.getState(), State.SEARCHING_FOR_FOOD);
    }

}