package org.example.menu;

import org.example.controller.BattleEngine;
import org.example.model.Attack;
import org.example.model.Pokemon;
import org.example.model.Types;
import org.example.repo.FileHelperBattler;
import org.example.repo.FileHelperPokemon;
import org.example.utility.InputHelper;
import org.example.utility.PokeHelper;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

import static org.example.utility.InputHelper.*;


public class Main {
    public static ArrayList<Pokemon> pokelist = new ArrayList<>();
    public static ArrayList<Pokemon> wildpokemon = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {
        Scanner scan = new Scanner(System.in);
        boolean running = true;
        Path pokepath = Path.of("saved pokémon.csv");
        Path attackpath = Path.of("saved pokémon attack.csv");
        Path resultpath = Path.of("Battle results.csv");
        int[] results = FileHelperBattler.loadResults(resultpath);
        int wins = results[0];
        int losses = results[1];
        addwildpokemon();
        initializepokedex(pokepath, attackpath);

        while (running) {
            System.out.println("\n=== Pokédex Menu ===");
            System.out.println("1.Show all Pokémon.\n2.Add a new Pokémon.\n3.Edit Pokémon(not available right now).\n4.Remove Pokémon.");
            System.out.println("5.Save to file. \n6.Upload from file. \n7.Reset Pokédex. \n8.Start a battle.");
            System.out.println("9.Show Battle results. \n10.Save and quit.");

            int option = InputHelper.readInt(scan, "Choose option: ");
            switch (option) {
                case 1 -> {
                    for (Pokemon p : pokelist) {
                        System.out.println(p);
                    }
                }
                case 2 -> {
                    scan.nextLine();
                    String newName = readname(scan, "Enter a new Pokémon name: ");
                    Types type = readTypes(scan, "Enter the Pokemon type: ");
                    boolean dualtype = readBoolean(scan, "Is the Pokémon a dual type? (True or False): ");
                    Types type2 = null;
                    if (dualtype) {
                        scan.nextLine();
                        type2 = readTypes(scan, "Enter 2nd type: ");
                    }
                    int newMaxhp = readInt(scan, "Enter the Pokémon max Hp: ");
                    int newCurhp = readInt(scan, "Enter the Pokémon current Hp: ");
                    Pokemon p8 = new Pokemon(newName, type, type2, newMaxhp, newCurhp);
                    pokelist.add(p8);
                    scan.nextLine();
                    String newattackname = readname(scan, "Enter new Pokémon attack name: ");
                    int newattackdmg = readInt(scan, "Enter new attack's base damage: ");
                    int newattackacc = InputHelper.readaccuracy(scan, "Enter new attack's accuracy: ");
                    scan.nextLine();
                    Types newtype = readTypes(scan, "Enter new attack's type: ");
                    p8.getAttacks().add(new Attack(newattackname, newattackdmg, newattackacc, newtype));
                    System.out.println("Pokémon added successfully.");
                }
                case 3 -> /* editPokemon(scan);*/ System.out.println("Current option is not available, check later.");
                case 4 -> removePokemon(scan);
                case 5 -> FileHelperPokemon.savePokemon(pokelist, pokepath, attackpath);
                case 6 -> pokelist = FileHelperPokemon.uploadPokemon(pokepath, attackpath);
                case 7 -> {
                    resetData();
                    System.out.println("Pokédex reset successfully.");
                }
                case 8 -> {
                    scan.nextLine();
                    if (pokelist.isEmpty()) {
                        System.out.println("No Pokémon available, battle cannot start.");
                        break;
                    }
                    for (Pokemon p : pokelist) {
                        System.out.println(p);
                    }
                    Pokemon chosenOne = PokeHelper.searchPokemon(scan, pokelist, "\nChoose your Pokémon: ");
                    if (chosenOne.getAttacks().isEmpty()) {
                        System.out.println(chosenOne.getName() + " does not have any attacks, try another Pokémon.");
                        break;
                    }
                    chosenOne = PokeHelper.statusCheck(chosenOne);
                    Pokemon chosenCPU = PokeHelper.chooseRandomPoke(wildpokemon);
                    System.out.println("Battle is between " + chosenOne.getName() + " and " + chosenCPU.getName());
                    System.out.println();
                    BattleEngine engine = new BattleEngine(chosenOne, chosenCPU);
                    engine.startBattle();
                    if (chosenCPU.isFainted()) {
                        wins++;
                    } else {
                        losses++;
                    }
                    FileHelperBattler.saveResults(wins, losses, resultpath);
                }
                case 9 -> {
                    System.out.println("\nAsh has won " + wins + " time(s) and lost " + losses + " time(s).");
                }
                case 10 -> {
                    FileHelperPokemon.savePokemon(pokelist, pokepath, attackpath);
                    System.out.println("System quitting...");
                    running = false;
                }

                default -> System.out.println("Option does not exist, try again(1-10).");
            }
        }
    }


    public static void removePokemon(Scanner scan) {
        scan.nextLine();
        String name = readname(scan, "Write the name of the Pokémon you wish to remove: ");
        Pokemon selected = null;
        for (Pokemon poke : pokelist) {
            if (name.equalsIgnoreCase(poke.getName())) {
                selected = poke;
                pokelist.remove(selected);
                System.out.println("Pokémon removed successfully.");
                break;
            }
        }
        if (selected == null) {
            System.out.println("Selected Pokémon does not exist.");
        }
    }

    public static void resetData() {
        pokelist.clear();

        Pokemon p1 = new Pokemon("Piplup", Types.WATER, 53, 53);
        p1.getAttacks().add(new Attack("Pound", 40, 100, Types.NORMAL));
        Pokemon p2 = new Pokemon("Farfetch'd", Types.FLYING, Types.NORMAL, 52, 52);
        p2.getAttacks().add(new Attack("Steel Wing", 70, 90, Types.STEEL));
        Pokemon p3 = new Pokemon("Rayquaza", Types.DRAGON, Types.FLYING, 105, 105);
        p3.getAttacks().add(new Attack("Dragon Ascent", 140, 100, Types.FLYING));
        Pokemon p4 = new Pokemon("Torchic", Types.FIRE, 45, 45);
        p4.getAttacks().add(new Attack("Ember", 40, 100, Types.FIRE));
        Pokemon p5 = new Pokemon("Dratini", Types.DRAGON, 41, 41);
        p5.getAttacks().add(new Attack("Twister", 40, 100, Types.DRAGON));
        Pokemon p6 = new Pokemon("Oshawott", Types.WATER, 55, 55);
        p6.getAttacks().add(new Attack("Water Gun", 40, 100, Types.WATER));
        Pokemon p7 = new Pokemon("Articuno", Types.ICE, Types.FLYING, 90, 90);
        p7.getAttacks().add(new Attack("Blizzard", 110, 70, Types.ICE));


        pokelist.add(p1);
        pokelist.add(p2);
        pokelist.add(p3);
        pokelist.add(p4);
        pokelist.add(p5);
        pokelist.add(p6);
        pokelist.add(p7);

    }

    public static void addwildpokemon() {
        Pokemon p8 = new Pokemon("Snorlax", Types.NORMAL, 160, 160);
        p8.getAttacks().add(new Attack("Lick", 30, 100, Types.GHOST));
        p8.getAttacks().add(new Attack("Tackle", 40, 100, Types.NORMAL));
        Pokemon p9 = new Pokemon("Ninetales", Types.FIRE, 73, 73);
        p9.getAttacks().add(new Attack("Fire Spin", 35, 85, Types.FIRE));
        p9.getAttacks().add(new Attack("Stored Power", 20, 100, Types.PSYCHIC));
        Pokemon p10 = new Pokemon("Salamence", Types.DRAGON, Types.FLYING, 95, 95);
        p10.getAttacks().add(new Attack("Dual Wingbeat", 40, 90, Types.FLYING));
        p10.getAttacks().add(new Attack("Dragon tail", 60, 90, Types.DRAGON));
        wildpokemon.add(p8);
        wildpokemon.add(p9);
        wildpokemon.add(p10);
    }

    public static void initializepokedex(Path pokepath, Path attackpath) {
        if (Files.exists(pokepath) && (Files.exists(attackpath))) {         //if both files exist execute this.
            pokelist = FileHelperPokemon.uploadPokemon(pokepath, attackpath);
        } else if (Files.exists(pokepath) && (!Files.exists(attackpath))) {  //if only pokemon file exists but not its attacks
            pokelist = FileHelperPokemon.uploadPokemon(pokepath, attackpath);
        } else {
            resetData();                                 //any other possibility (for example no pokemon file but attack file)
        }
    }


}