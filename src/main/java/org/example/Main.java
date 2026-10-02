package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

import static org.example.InputHelper.*;

public class Main {
    public static ArrayList<Pokemon> pokelist = new ArrayList<>();
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        boolean running = true;
        Path pokepath = Path.of("saved pokémon.csv");
        Path attackpath = Path.of("saved pokémon attack.csv");
        initializepokedex(pokepath, attackpath);

        while (running) {
            System.out.println("\n=== Pokédex Menu ===");
            System.out.println("1.Show all Pokémon.\n2.Add a new Pokémon.\n3.Edit Pokémon.\n4.Remove Pokémon.");
            System.out.println("5.Save to file. \n6.Upload from file. \n7.Reset Pokédex. \n8.Save and quit.");

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
                    p8.getAttacks().add(new Attack(newattackname,newattackdmg,newattackacc,newtype));
                    System.out.println("Pokémon added successfully.");
                }
                case 3 -> editPokemon(scan);
                case 4 -> removePokemon(scan);
                case 5 -> FileHelper.savePokemon(pokelist, pokepath, attackpath);
                case 6 -> pokelist = FileHelper.uploadPokemon(pokepath, attackpath);
                case 7 -> {
                    resetData();
                    System.out.println("Pokédex reset successfully.");
                }
                case 8 -> {
                    FileHelper.savePokemon(pokelist, pokepath, attackpath);
                    System.out.println("System quitting...");
                    running = false;
                }
                default -> System.out.println("Option does not exist, try again(1-8).");
            }
        }
    }

    public static void editPokemon(Scanner scan) {
        boolean running = true;
        scan.nextLine();
        String name = readname(scan, "Write the name of the Pokémon you wish to edit: ");

        Pokemon selected = null;
        for (Pokemon poke : pokelist) {
            if (name.equalsIgnoreCase(poke.getName())) {
                selected = poke;
                while (running) {
                    System.out.println(selected);
                    System.out.println("\n1.Change Pokémon name.\n2.Change Pokémon HP.\n3.Change Pokémon type.");
                    System.out.println("4.Teach a new attack.\n5.Remove attack.\n6.Return to main menu.");

                    int option = readInt(scan, "Select option: ");
                    switch (option) {
                        case 1 -> {
                            scan.nextLine();
                            String newName = readname(scan, "Enter new Pokémon name: ");
                            poke.setName(newName);
                            System.out.println("New name is: " + poke.getName());
                        }
                        case 2 -> {
                            int newHp = readInt(scan, "Enter new Pokémon max HP: ");
                            poke.setMaxHp(newHp);
                            poke.setCurrentHp(newHp);
                            System.out.println("New Pokémon max HP is: " + poke.getMaxHp());
                        }
                        case 3 -> {
                            scan.nextLine();
                            Types newtype = readTypes(scan, "Enter new Pokémon type: ");
                            poke.setType1(newtype);
                            if (poke.getType2() != null) {
                                Types newtype2 = readTypes(scan, "Enter 2nd Pokémon type: ");
                                poke.setType2(newtype2);
                                System.out.println("New Pokémon type is : " + poke.getType1() + " | " + poke.getType2());
                            } else if (poke.getType2() == null) {
                                System.out.println("New Pokémon type is : " + poke.getType1());
                            }
                        }
                        case 4 -> {
                            if (selected.getAttacks().size() >= 4) {
                                System.out.println("Selected Pokémon cannot learn more attacks.");
                                continue;
                            }
                            scan.nextLine();
                            String newattackname = readname(scan, "Enter new Pokémon attack name: ");
                            int newattackdmg = readInt(scan, "Enter new attack's base damage: ");
                            int newattackacc = InputHelper.readaccuracy(scan, "Enter new attack's accuracy: ");
                            scan.nextLine();
                            Types newtype = readTypes(scan, "Enter new attack's type: ");
                            selected.getAttacks().add(new Attack(newattackname, newattackdmg, newattackacc, newtype));
                            System.out.println("Attack added successfully.");
                        }
                        case 5 -> {
                            scan.nextLine();
                            if (selected.getAttacks().size() <= 1) {
                                System.out.println("Selected Pokémon cannot unlearn more attacks.");
                                continue;
                            }
                            String atkremove = readname(scan, "Which Pokémon attack to remove?: ");
                            boolean foundattack = false;
                            for (Attack pokeattack : selected.getAttacks()) {
                                if (atkremove.equalsIgnoreCase(pokeattack.getName())) {
                                    selected.getAttacks().remove(pokeattack);
                                    foundattack = true;
                                    System.out.println("Attack removed successfully.");
                                    break;
                                }
                            }
                            if (!foundattack) {
                                System.out.println("Attack doesn't exist. Try again.");
                            }
                        }
                        case 6 -> {
                            System.out.println("Returning to main menu...");
                            running = false;
                        }
                        default -> System.out.println("Selected option does not exist, try again(1-6).");
                    }
                }
                break;
            }
        }
        if (selected == null) {
            System.out.println("Selected Pokémon does not exist.");
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

    public static void initializepokedex(Path pokepath, Path attackpath) {
        if (Files.exists(pokepath) && (Files.exists(attackpath))) {         //if both files exist execute this.
           pokelist = FileHelper.uploadPokemon(pokepath, attackpath);
        } else if (Files.exists(pokepath) && (!Files.exists(attackpath))) {  //if only pokemon file exists but not its attacks
           pokelist = FileHelper.uploadPokemon(pokepath, attackpath);
        } else {
            resetData();
        }                                                //any other possibility (for example no pokemon file but attack file)
    }
}