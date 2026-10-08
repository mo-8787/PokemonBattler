package org.example.controller;

import org.example.model.Pokemon;

import java.util.Random;

public class BattleEngine {
    private Pokemon p1;
    private Pokemon p2;

    public BattleEngine(Pokemon p1, Pokemon p2){
        this.p1 = p1;
        this.p2 = p2;
    }

    public void startBattle(){

        boolean running = true;
        // coin flip to decide who goes first
        Random random = new Random();
        int turn = random.nextInt(2);
        while (running) {
            if (turn == 0) {
                System.out.println("You go first!");
                // choose an attack
                System.out.print(p1.getAttacks());
                System.out.println("Choose your attack: ");
                //does the attack hit?
                if (p1.getAttacks().get(0).getAccuracy() == 100) {
                    //perform the attack
                } else {
                    int chance = random.nextInt(100);
                    if (chance <= p1.getAttacks().get(0).getAccuracy()) {
                        //perform the attack
                    } else {
                        System.out.println("Attack missed!");
                    }
                }
                // perform the attack
                // check if game is over
                if (p2.isFainted()) {
                    System.out.println("Battle won.");
                    running = false;
                }
                turn = 1;
            } else {
                System.out.println("You go second!");
                // CPUs turn to choose and perform an attack if it hits
                // check if game is over otherwise repeat
                if (p1.isFainted()) {
                    System.out.println("Battle lost.");
                    running = false;
                }
                turn = 0;
            }

        }
    }



}
