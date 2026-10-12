package org.example.controller;

import org.example.model.Attack;
import org.example.model.Pokemon;

import java.util.Random;

public class BattleEngine {
    private Pokemon p1;
    private Pokemon p2;
    Random random = new Random();
    public BattleEngine(Pokemon p1, Pokemon p2){
        this.p1 = p1;
        this.p2 = p2;
    }

    public void startBattle(){

        boolean running = true;
        // coin flip to decide who goes first

        int turn = random.nextInt(2);
        while (running) {
            if (turn == 0) {
                System.out.println("Your turn: ");
                // choose an attack
                System.out.print(p1.getAttacks());
                System.out.println("Choose your attack: ");
                //does the attack hit?
                if (p1.getAttacks().get(0).getAccuracy() == 100) {
                    int damagetaken = calculateAttackDmg();
                    p2.takeDamage(damagetaken);
                    //perform the attack
                } else {
                    int chance = random.nextInt(101);
                    if (chance <= p1.getAttacks().get(0).getAccuracy()) {
                        //perform the attack
                        int damagetaken = calculateAttackDmg();
                        p2.takeDamage(damagetaken);
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
                int randomCpuAttack = random.nextInt(p2.getAttacks().size());
                Attack randomattack = p2.getAttacks().get(randomCpuAttack);
                if (randomattack.getAccuracy() == 100) {
                    int damagetaken = calculateCPUmove();
                    p1.takeDamage(damagetaken);
                } else {
                    int CPUchance = random.nextInt(101);
                    if (CPUchance <= randomattack.getAccuracy()) {
                        int damagetaken = calculateCPUmove();
                        p1.takeDamage(damagetaken);
                    } else {
                        System.out.println("Attack missed!");
                    }
                }
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
    public int calculateAttackDmg() {
        double rngmult = random.nextDouble(0.85,1.00);
        double damagetaken = p1.getAttacks().get(0).getBaseDmg() * rngmult;
        boolean isCrit = random.nextDouble(1.00) <= ((double) 1 /24);
        if (isCrit) {
            int damagetotal = (int) (damagetaken * 1.5);
            return damagetotal;
        } else {
            int damagetotal = (int) (damagetaken);
            return damagetotal;
        }

    }

    public int calculateCPUmove() {
        int randomCpuAttack = random.nextInt(p2.getAttacks().size());
        Attack randomattack = p2.getAttacks().get(randomCpuAttack);
        double rngmult = random.nextDouble(0.85,1.00);
        boolean isCrit = random.nextDouble(1.00) <= ((double) 1/24);
        if (isCrit) {
            int damagetotal = (int) (randomattack.getBaseDmg() * rngmult * 1.5);
            return damagetotal;
        } else {
            int damagetotal = (int) (randomattack.getBaseDmg() * rngmult);
            return damagetotal;
        }
    }

}
