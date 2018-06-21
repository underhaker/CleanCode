package com.fmi.cleancode.god;

import com.fmi.cleancode.god.simulator.Simulator;

import java.lang.reflect.InvocationTargetException;

public class MainGame {
    
    public void StartGame(){
        final Simulator simulator = new Simulator();
        Thread t1 = new Thread(() -> simulator.Run());
        Thread t2 = new Thread(() -> {
            try {
                simulator.update();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        });
        t1.start();
        t2.start();
        }
    //runs up the game with 2 threads:1 for menu and 1 for updates
}
