package org.example.controller;

import org.example.model.Attack;
import org.example.model.Pokemon;
import org.example.utility.InputHelper;


import java.util.Random;
import java.util.Scanner;

public class BattleEngine {
    private Pokemon p1;
    private Pokemon p2;
    Random random = new Random();
    Scanner scan = new Scanner(System.in);

    public BattleEngine(Pokemon p1, Pokemon p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    public void startBattle() {

        boolean running = true;

        System.out.println("Flipping a coin...");
        int turn = coinFlip();
        while (running) {
            if (turn == 0) {
                System.out.println(p1.getName() + "'s turn now. ");
                for (Attack a : p1.getAttacks()) {
                    System.out.println(a);
                }
                String attackname = InputHelper.readname(scan, "\nChoose your attack: ");
                Attack selected = null;
                for (Attack atk : p1.getAttacks()) {
                    if (attackname.equalsIgnoreCase(atk.getName())) {
                        selected = atk;
                        break;
                    }
                }
                if (selected == null) {
                    System.out.println("Invalid attack, try again");
                    continue;
                }
                if (attackHits(selected)) {
                    int damagetaken = calculateAttackDmg(selected);
                    p2.takeDamage(damagetaken);
                    System.out.println(p2.getName() + " took " + damagetaken + " damage.");
                } else {
                    System.out.println("Attack missed!");
                }
                if (p2.isFainted()) {
                    System.out.println(p2.getName() + " fainted, Battle won.");
                    running = false;
                }
                turn = 1;
            } else {
                System.out.println(p2.getName() + "'s turn.");
                Attack randomCpuAttack = p2.getAttacks().get(random.nextInt(p2.getAttacks().size()));
                System.out.println(p2.getName() + " is using " + randomCpuAttack.getName() + ".");
                if (attackHits(randomCpuAttack)) {
                    int damagetaken = calculateAttackDmg(randomCpuAttack);
                    p1.takeDamage(damagetaken);
                    System.out.println(p1.getName() + " took " + damagetaken + " damage.");
                } else {
                    System.out.println("Attack missed!");
                }
                if (p1.isFainted()) {
                    System.out.println(p1.getName() + " fainted, Battle lost.");
                    running = false;
                }
                turn = 0;
            }
        }
    }

    private int calculateAttackDmg(Attack attack) {
        double rngmult = random.nextDouble(0.85, 1.00);
        boolean isCrit = random.nextDouble(1.00) <= (1.0 / 24);
        double totaldmg = attack.getBaseDmg() * rngmult;
        if (isCrit) {
            totaldmg *= 1.5;
            System.out.println(attack.getName() + " was critical!");
        }
        return (int) totaldmg;
    }

    private boolean attackHits(Attack attack) {
        return random.nextInt(100) < attack.getAccuracy();
    }

    private int coinFlip(){
        int turn = random.nextInt(2);
        if(turn == 0) {
            System.out.println("Heads! " + p1.getName() + " goes first.");
        } else {
            System.out.println("Tails! " + p2.getName() + " goes first.");
        }
        return turn;
    }

}


