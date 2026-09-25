package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

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

            int option = readInt(scan, "Choose option: ");
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
                    System.out.println("Pokémon added successfully.");
                }
                case 3 -> editPokemon(scan);
                case 4 -> removePokemon(scan);
                case 5 -> savePokemon(pokelist, pokepath, attackpath);
                case 6 -> uploadPokemon(pokepath, attackpath);
                case 7 -> {
                    resetData();
                    System.out.println("Pokédex reset successfully.");
                }
                case 8 -> {
                    savePokemon(pokelist, pokepath, attackpath);
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
            if (name.equalsIgnoreCase(poke.name)) {
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
                            poke.name = newName;
                            System.out.println("New name is: " + poke.name);
                        }
                        case 2 -> {
                            int newHp = readInt(scan, "Enter new Pokémon max HP: ");
                            poke.MaxHp = newHp;
                            poke.CurrentHp = newHp;
                            System.out.println("New Pokémon max HP is: " + poke.MaxHp);
                        }
                        case 3 -> {
                            scan.nextLine();
                            Types newtype = readTypes(scan, "Enter new Pokémon type: ");
                            poke.type1 = newtype;
                            if (poke.type2 != null) {
                                Types newtype2 = readTypes(scan, "Enter 2nd Pokémon type: ");
                                poke.type2 = newtype2;
                                System.out.println("New Pokémon type is : " + poke.type1 + " | " + poke.type2);
                            } else if (poke.type2 == null) {
                                System.out.println("New Pokémon type is : " + poke.type1);
                            }
                        }
                        case 4 -> {
                            if (selected.attacks.size() >= 4) {
                                System.out.println("Selected Pokémon cannot learn more attacks.");
                                continue;
                            }
                            scan.nextLine();
                            String newattackname = readname(scan, "Enter new Pokémon attack name: ");
                            int newattackdmg = readInt(scan, "Enter new attack's base damage: ");
                            int newattackacc = readaccuracy(scan, "Enter new attack's accuracy: ");
                            scan.nextLine();
                            Types newtype = readTypes(scan, "Enter new attack's type: ");
                            selected.attacks.add(new Attack(newattackname, newattackdmg, newattackacc, newtype));
                            System.out.println("Attack added successfully.");
                        }
                        case 5 -> {
                            scan.nextLine();
                            String atkremove = readname(scan, "Which Pokémon attack to remove?: ");
                            boolean foundattack = false;
                            for (Attack pokeattack : selected.attacks) {
                                if (atkremove.equalsIgnoreCase(pokeattack.name)) {
                                    selected.attacks.remove(pokeattack);
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
            if (name.equalsIgnoreCase(poke.name)) {
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
        p1.attacks.add(new Attack("Pound", 40, 100, Types.NORMAL));
        Pokemon p2 = new Pokemon("Farfetch'd", Types.FLYING, Types.NORMAL, 52, 52);
        p2.attacks.add(new Attack("Steel Wing", 70, 90, Types.STEEL));
        Pokemon p3 = new Pokemon("Rayquaza", Types.DRAGON, Types.FLYING, 105, 105);
        p3.attacks.add(new Attack("Dragon Ascent", 140, 100, Types.FLYING));
        Pokemon p4 = new Pokemon("Torchic", Types.FIRE, 45, 45);
        p4.attacks.add(new Attack("Ember", 40, 100, Types.FIRE));
        Pokemon p5 = new Pokemon("Dratini", Types.DRAGON, 41, 41);
        p5.attacks.add(new Attack("Twister", 40, 100, Types.DRAGON));
        Pokemon p6 = new Pokemon("Oshawott", Types.WATER, 55, 55);
        p6.attacks.add(new Attack("Water Gun", 40, 100, Types.WATER));
        Pokemon p7 = new Pokemon("Articuno", Types.ICE, Types.FLYING, 90, 90);
        p7.attacks.add(new Attack("Blizzard", 110, 70, Types.ICE));

        pokelist.add(p1);
        pokelist.add(p2);
        pokelist.add(p3);
        pokelist.add(p4);
        pokelist.add(p5);
        pokelist.add(p6);
        pokelist.add(p7);
    }

    public static void savePokemon(ArrayList<Pokemon> pokelist, Path pokepath, Path attackpath) {
        ArrayList<String> pokeLines = new ArrayList<>();
        ArrayList<String> attackLines = new ArrayList<>();

        for (Pokemon poke : pokelist) {
            if (poke.type2 != null) {
                pokeLines.add(poke.name + "," + poke.type1 + "," + poke.type2 + "," + poke.MaxHp + "," + poke.CurrentHp);
            } else if (poke.type2 == null) {
                pokeLines.add(poke.name + "," + poke.type1 + "," + poke.MaxHp + "," + poke.CurrentHp);
            }
            for (Attack attack : poke.attacks) {
                attackLines.add(attack.name + "," + attack.baseDmg + "," + attack.accuracy + "," + attack.atkType + "," + poke.name);
            }
        }
        String allPokemon = String.join("\n", pokeLines);
        String allAttacks = String.join("\n", attackLines);
        try {
            Files.writeString(pokepath, allPokemon);
            Files.writeString(attackpath, allAttacks);
            System.out.println("Save successful.");
        } catch (IOException e) {
            System.err.println("Save not possible.");
        }
    }

    public static void uploadPokemon(Path pokepath, Path attackpath) {
        ArrayList<Pokemon> uploadedPokemon = new ArrayList<>();
        try {
            List<String> pokeLines = Files.readAllLines(pokepath);
            for (String line : pokeLines) {
                if (line.isBlank()) continue;
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    String name = parts[0];
                    Types types1 = Types.valueOf(parts[1]);
                    int maxhp = Integer.parseInt(parts[2]);
                    int curhp = Integer.parseInt(parts[3]);
                    Pokemon newpoke = new Pokemon(name, types1, maxhp, curhp);
                    uploadedPokemon.add(newpoke);
                } else if (parts.length == 5) {
                    String name = parts[0];
                    Types types1 = Types.valueOf(parts[1]);
                    Types types2 = Types.valueOf(parts[2]);
                    int maxhp = Integer.parseInt(parts[3]);
                    int curhp = Integer.parseInt(parts[4]);
                    Pokemon newpoke = new Pokemon(name, types1, types2, maxhp, curhp);
                    uploadedPokemon.add(newpoke);
                }
            }
            System.out.println("New Pokémon added successfully");
            if (!Files.exists(attackpath)) {
                pokelist = uploadedPokemon;
                return;
            }
            List<String> attackLines = Files.readAllLines(attackpath);
            for (String line : attackLines) {
                if (line.isBlank()) continue;
                String[] parts = line.split(",");
                String attackname = parts[0];
                int basedmg = Integer.parseInt(parts[1]);
                int accuracy = Integer.parseInt(parts[2]);
                Types atktype = Types.valueOf(parts[3]);
                String atktoPoke = parts[4];
                Attack attack = new Attack(attackname, basedmg, accuracy, atktype);
                for (Pokemon newpoke : uploadedPokemon) {
                    if (newpoke.name.equalsIgnoreCase(atktoPoke)) {
                        newpoke.attacks.add(attack);
                        break;
                    }
                }
            }
            System.out.println("New attacks added successfully");
        } catch (IOException e) {
            System.err.println("Upload unsuccessful.");
        }
        pokelist = uploadedPokemon;
    }


    public static void initializepokedex(Path pokepath, Path attackpath) {
        if (Files.exists(pokepath) && (Files.exists(attackpath))) {         //if both files exist execute this.
            uploadPokemon(pokepath, attackpath);
        } else if (Files.exists(pokepath) && (!Files.exists(attackpath))) {  //if only pokemon file exists but not its attacks
            uploadPokemon(pokepath, attackpath);
        } else {
            resetData();
        }                                                //any other possibility (for example no pokemon file but attack file)
    }

    public static int readInt(Scanner scan, String prompt) {
        while (true) {
            System.out.print(prompt);
            if (scan.hasNextInt()) {
                int input = scan.nextInt();
                if (input >= 1) {
                    return input;
                }
                scan.nextLine();
                System.out.println("Enter positive integer. ");
                continue;
            }
            System.out.println("Enter positive integer. ");
            scan.next();
        }
    }

    public static boolean readBoolean(Scanner scan, String prompt) {
        while (true) {
            System.out.print(prompt);
            if (scan.hasNextBoolean()) {
                return scan.nextBoolean();
            }
            System.out.println("Enter valid option. ");
            scan.next();
        }
    }

    public static Types readTypes(Scanner scan, String prompt) {
        while (true) {
            System.out.print(prompt);
            String stringtype = scan.nextLine();
            stringtype = stringtype.toUpperCase();
            try {
                Types type = Types.valueOf(stringtype);
                return type;
            } catch (IllegalArgumentException e) {
                System.out.println("Enter a valid type. ");
            }
        }
    }

    public static String readname(Scanner scan, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scan.nextLine().trim();
            if (input.length() < 100 && !input.isEmpty() && !input.contains(",")) {
                return input;
            }
            System.out.println("Enter a valid name.");
        }
    }

    public static int readaccuracy(Scanner scan, String prompt) {
        while (true) {
            System.out.print(prompt);
            if (scan.hasNextInt()) {
                int input = scan.nextInt();
                if (input <= 100 && input >= 0) {
                    return input;
                }
                System.out.println("Invalid accuracy, it needs to be between 0-100.");
                scan.nextLine();
                continue;
            }
            System.out.println("Invalid accuracy, it needs to be between 0-100.");
            scan.next();
        }
    }
}