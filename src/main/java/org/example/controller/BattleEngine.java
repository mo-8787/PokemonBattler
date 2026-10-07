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
        // coin flip to decide who goes first
        Random random = new Random();
        
        // choose an attack
        // perform the attack
        // check if game is over
        // CPUs turn to choose and perform an attack
        // check if game is over otherwise repeat
    }

}
