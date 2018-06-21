package com.fmi.cleancode.god.simulator;

import com.fmi.cleancode.god.entities.Entity;
import com.fmi.cleancode.god.entities.God;
import com.fmi.cleancode.god.enums.EntityType;
import com.fmi.cleancode.god.planets.Planet;
import com.fmi.cleancode.god.planets.Scene;
import com.fmi.cleancode.god.utils.Point2D;
import com.fmi.cleancode.god.utils.RandomNumberGenerator;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Scanner;

public class Simulator {
    private final static int PLANET_SIZE = 300;
    //maximum elements that can be added at a time to a planet
    private final static int MAXIMUM_PLANETS = 9;
    private static final String[] METHOD_NAMES = {"analyze", "sleep", "eat", "searchingForFood"};
    private God player;
    private Scene scene;


    public Simulator() {
        player = new God();
        scene = new Scene();
    }

    public void Run() {
        showMenu();
    }

    //main menu in which a command is given from the console
    private void showMenu() {
        Scanner in = new Scanner(System.in);
        String input;
        showHelp();
        while (true) {
            System.out.println("Enter command:");
            input = in.nextLine();
            if (input.equals("exit")) {
                in.close();
                System.out.println("Exiting game..");
                System.exit(0);
            }
            if (input.equals("create")) {
                createPlanet();
                continue;
            }
            if (input.contains("delete population")) {
                if (checkValidInput(input)) {
                    destroyPopulation(input);
                } else {
                    printInvalidCommand();
                    continue;
                }
            }
            if (input.contains("destroy")) {
                if (checkValidInput(input)) {
                    destroyPlanet(input);
                } else {
                    printInvalidCommand();
                }
                continue;
            }
            if (input.equals("statistic")) {
                showStatistics();
                continue;
            }
            if (input.equals("help")) {
                showHelp();
                continue;
            }
            if (input.contains("add")) {
                if (checkValidInput(input)) {
                    addCreatures(input);
                } else {
                    printInvalidCommand();
                }
                continue;
            }
            printInvalidCommand();
        }

    }

    public void update() throws InterruptedException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        while (true) {
            for (Planet planet : scene.getPlanets()) {
                List<Entity> entities = planet.getPopulation();
                if (!planet.isDestroyed()) {
                    moveEntities(entities);
                    executeAnAction(planet, entities);
                    removeDeadEntities(entities);
//                        doStuff(entities);
                }
            }
        }
    }

    private void printInvalidCommand() {
        System.out.println("Invalid command!");
    }

    //starts the showMenu
    private void createPlanet() {
        if (scene.getPlanets().size() < MAXIMUM_PLANETS)
            scene.createPlanet();
        else
            System.out.println("No more planets can be added..");
    }

    //creates a new Planet with random variables and adds it to ArrayList
    private void destroyPopulation(String input) {
        String str[] = input.split(" ");
        String planetName = str[2];
        for (Planet pl : scene.getPlanets()) {
            if (pl.getName().equals(planetName)) {
                pl.destroyPopulation();
                break;
            }
        }
    }

    //removes population from given planet
    private void destroyPlanet(String input) {
        String str[] = input.split(" ");
        String planetName = str[1];
        for (Planet pl : scene.getPlanets()) {
            if (pl.getName().equals(planetName)) {
                scene.destroyPlanet(pl);
                break;
            }
        }
    }

    //removes given planet from ArrayList
    private void showStatistics() {
        for (Planet pl : scene.getPlanets())
            System.out.println("Planet:" + pl.getName() + " population:" + pl.getPopulationCount());
    }
    //updates each ENTITY's position and performs an action chosen by given methods

    //prints each planet's population size
    private void addCreatures(String input) {
        String[] inputStringCommands = input.split(" ");
        String planetName = inputStringCommands[1];
        String entity = inputStringCommands[2];
        int size = Integer.parseInt(inputStringCommands[3]);
        for (Planet planet : scene.getPlanets()) {
            if (planet.getName().equals(planetName)) {
                switch (entity) {
                    case "ENTITY": {
                        for (int i = 0; i < size; i++) {
                            scene.createCreature(planet, EntityType.ENTITY);
                        }
                    }
                    break;
                    case "ANIMAL": {
                        for (int i = 0; i < size; i++) {
                            scene.createCreature(planet, EntityType.ANIMAL);
                        }
                    }
                    break;
                    case "HUMAN": {
                        for (int i = 0; i < size; i++) {
                            scene.createCreature(planet, EntityType.HUMAN);
                        }
                    }
                    break;
                    case "GOD": {
                        for (int i = 0; i < size; i++) {
                            scene.createCreature(planet, EntityType.GOD);
                        }
                    }
                    break;
                }
                break;
            }
        }
        System.out.println(size + " " + entity + "s added to " + inputStringCommands[1] + "...");
    }

    //adds n entities(or subclass units) to given planet
    private void showHelp() {
        System.out.println("Main showMenu");
        System.out.println("Type \"create\" to create a new planet.");
        System.out.println("Type \"delete population <name of planet>\" to annihilate a planet's population.");
        System.out.println("Type \"destroy <name of planet>\" to destroy a planet.");
        System.out.println("Type \"statistic\" for statistics.");
        System.out.println("Type \"add <name of planet> <ENTITY|ANIMAL|HUMAN|GOD> <count>\" to add number of entities to a planet.");
        System.out.println("Type \"help\" for instructions.");
        System.out.println("Type \"exit\" to exit.");
    }

    //updates the ArrayList of entities with dead ones removed
    private void removeDeadEntities(List<Entity> entities) {
        for (Entity et : entities) {
            if (!et.isAlive())
                entities.remove(et);
        }
    }

    //iterates through entities ArrayList and performs move method on them
    private void moveEntities(List<Entity> entities) throws InterruptedException {
        for (Entity entity : entities) {
            Thread.sleep(100);
            entity.move();
        }
    }

    //chooses randomly an action
    private void executeAnAction(Planet pl, List<Entity> entities) throws InterruptedException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        RandomNumberGenerator generator = new RandomNumberGenerator();
        int actionRand;
        int attackRand;
        for (Entity entity1 : entities) {
            for (Entity entity2 : entities)
                if (!entity1.equals(entity2)) {
                    if (pl.isDestroyed()) break;
                    Thread.sleep(500);
                    if (new Point2D(0, 0).getDistance(entity1.getPosition(), entity2.getPosition()) <= 20) {
                        actionRand = generator.generateNumberRange(3);
                        if (actionRand % 3 == 0) {
                            attackRand = generator.generateNumberRange(2);
                            if (attackRand % 2 == 0)
                                entity1.attack(entity2);
                            else if (attackRand % 2 == 1)
                                entity2.attack(entity1);
                            if (!entity1.isAlive())
                                entities.remove(entity1);
                            if (!entity2.isAlive())
                                entities.remove(entity2);
                            break;
                        } else if (actionRand % 3 == 1 && (entity1.isAlive() && entity2.isAlive())) {
                            scene.createCreature(pl, entity1.getEntity());
                            System.out.println("status:" + entity1.getName() + " and " + entity2.getName() + " have created a new creature..");
                            break;
                        } else if (actionRand % 3 == 2) {
                            doStuff(entity1);
                            break;
                        }
                    }

                }
        }
    }

    //invokes a method that does a action based on random choosing by using reflection
    private void doStuff(Entity entity) throws IllegalAccessException, IllegalArgumentException,
            InvocationTargetException, NoSuchMethodException, SecurityException, InstantiationException,
            InterruptedException {
        RandomNumberGenerator rng = new RandomNumberGenerator();
        Thread.sleep(3000);
        int num = rng.generateNumberRange(METHOD_NAMES.length);
        Method[] methods = entity.getClass().getMethods();
        for (Method method : methods) {
            if (method.getName().equals(METHOD_NAMES[num])) {
                method.invoke(entity.getClass().newInstance());
                break;
            }
        }
    }


    //boolean function which checks for valid input
    private boolean checkValidInput(String input) {
        String[] inputStringArray = input.split(" ");
        List<Planet> planets = scene.getPlanets();
        if (inputStringArray[0].equals("destroy")) {
            if (inputStringArray.length < 2) {
                return false;
            }
            for (Planet planet : planets) {
                if (planet.getName().equals(inputStringArray[1]))
                    return true;
            }
        }

        if (inputStringArray[0].equals("delete") && inputStringArray[1].equals("population")) {
            if (inputStringArray.length < 3) {
                return false;
            }
            for (Planet pl : planets) {
                if (pl.getName().equals(inputStringArray[2]))
                    return true;
            }
        }

        if (inputStringArray[0].equals("add")) {
            if (inputStringArray.length < 4) {
                return false;
            }
            for (Planet planet : planets) {
                if (planet.getName().equals(inputStringArray[1])) {
                    boolean isEntity = inputStringArray[2].equals(EntityType.ENTITY.toString())
                            || inputStringArray[2].equals(EntityType.ANIMAL.toString())
                            || inputStringArray[2].equals(EntityType.HUMAN.toString())
                            || inputStringArray[2].equals(EntityType.GOD.toString());
                    if (isEntity) {
                        int entityCount = Integer.parseInt(inputStringArray[3]);
                        if (entityCount > 0 && entityCount < PLANET_SIZE) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }


}
