package com.fmi.cleancode.god;

import com.fmi.cleancode.god.simulator.Simulator;

public class MainGame {

    public void StartGame() {
        final Simulator simulator = new Simulator();
        Thread threadMenu = new Thread(simulator::Run);
        Thread threadGameUpdate = new Thread(() -> {
            try {
                simulator.update();
            } catch (Exception e) {
                System.out.println("An error has occurred during the game!\n" + e.getLocalizedMessage());
            }
        });
        threadMenu.start();
        threadGameUpdate.start();
    }
    //runs up the game with 2 threads:1 for menu and 1 for updates
}
