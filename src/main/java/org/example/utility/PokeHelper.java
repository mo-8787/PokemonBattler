package org.example.utility;

import org.example.model.Pokemon;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class PokeHelper {
    public static Pokemon statusCheck(Pokemon poke) {
        if (poke.isFainted()) {
            System.out.println(poke.getName() + " is fainted, Nurse Joy is healing it back...");
            poke.heal(poke.getMaxHp());
            System.out.println(poke.getName() + " has fully healed and ready to fight.");
        }
        if (poke.getCurrentHp() != poke.getMaxHp()) {
            System.out.println(poke.getName() + " is injured, Nurse Joy is healing it back...");
            poke.heal(poke.getMaxHp());
            System.out.println(poke.getName() + " has fully healed and ready to fight.");
        }
        return poke;
    }

    public static Pokemon chooseRandomPoke(ArrayList<Pokemon> wildpokemon) {
        Random random = new Random();
        int randompoke = random.nextInt(wildpokemon.size());
        Pokemon randompokemon = wildpokemon.get(randompoke);
        System.out.println("A wild " + randompokemon.getName() + " has appeared.");
        return randompokemon;
    }

    public static Pokemon searchPokemon(Scanner scan, ArrayList<Pokemon> pokelist, String prompt) {
        while (true) {
            String name = InputHelper.readname(scan, prompt);
            for (Pokemon p : pokelist) {
                if (name.equalsIgnoreCase(p.getName())) {
                    return p;
                }
            }
            System.out.println("Pokemon name invalid, try again.");
        }
    }
}
