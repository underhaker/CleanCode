package com.fmi.cleancode.god.entities;

import com.fmi.cleancode.god.enums.State;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class HumanTest {
    private Human human;
    @Before
    public void setUp() throws Exception {
        this.human = new Human();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testIfStateIsChangedToAnalyzing() throws Exception {
        this.human.analyze();
        assertEquals(this.human.getState(), State.ANALYZING);
    }

}