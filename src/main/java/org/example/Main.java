package org.example;


import java.util.ArrayList;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static ArrayList<Pokemon> pokelist = new ArrayList<>();

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        boolean running = true;

        resetData();

     /*   Pokemon p1 = new Pokemon("Piplup",Types.WATER,53,53);
        p1.attacks.add(new Attack("Pound",40,100,Types.NORMAL));
        Pokemon p2 = new Pokemon("Farfetch'd",Types.FLYING,Types.NORMAL,52,52);
        p2.attacks.add(new Attack("Steel Wing",70,90,Types.STEEL));
        Pokemon p3 = new Pokemon("Rayquaza",Types.DRAGON,Types.FLYING,105,105);
        p3.attacks.add(new Attack("Dragon Ascent",140,100,Types.FLYING));
        Pokemon p4 = new Pokemon("Torchic",Types.FIRE,45,45);
        p4.attacks.add(new Attack("Ember",40,100,Types.FIRE));
        Pokemon p5 = new Pokemon("Dratini",Types.DRAGON,41,41);
        p5.attacks.add(new Attack("Twister",40,100,Types.DRAGON));
        Pokemon p6 = new Pokemon("Oshawott",Types.WATER,55,55);
        p6.attacks.add(new Attack("Water Gun",40,100,Types.WATER));
        Pokemon p7 = new Pokemon("Articuno",Types.ICE,Types.FLYING,90,90);
        p7.attacks.add(new Attack("Blizzard",110,70,Types.ICE));


        pokelist.add(p1); pokelist.add(p2); pokelist.add(p3); pokelist.add(p4); pokelist.add(p5);
        pokelist.add(p6); pokelist.add(p7); */

        while (running) {
            System.out.println("=== Pokédex Menu ===");
            System.out.println("1.Show all Pokémon  2.Add a new Pokémon  3.Edit Pokémon  4.Remove Pokémon");
            System.out.println("5.Save to file  6.Upload from file  7.Reset Pokédex  8.Save and quit.");
            System.out.print("Choose option: ");
            int option = scan.nextInt();
            switch (option) {
                case 1 -> {
                    for (Pokemon p : pokelist) {
                        System.out.println(p);
                    }
                }
                case 2 -> {
                    System.out.print("Enter new Pokémon name: ");
                    scan.nextLine();
                    String newName = scan.nextLine();
                    System.out.print("Enter the Pokemon type: ");
                    String newType = scan.nextLine();
                    Types type = Types.valueOf(newType);
                    System.out.print("Is the Pokémon a dual type? (True or False): ");
                    boolean dualtype = scan.nextBoolean();
                    Types type2 = null;
                    if (dualtype) {
                        System.out.print("Enter 2nd Type: ");
                        scan.nextLine();
                        String newType2 = scan.nextLine();
                        type2 = Types.valueOf(newType2);
                    }
                    System.out.print("Enter the Pokemon max Hp: ");
                    int newMaxhp = scan.nextInt();
                    System.out.print("Enter the Pokemon current Hp: ");
                    int newCurhp = scan.nextInt();
                    Pokemon p8 = new Pokemon(newName, type, type2, newMaxhp, newCurhp);
                    pokelist.add(p8);
                    System.out.println("Pokémon added successfully.");
                }
                case 3 -> editPokemon(scan);
                case 4 -> removePokemon(scan);

                case 7 -> resetData();
                case 8 -> {
                    System.out.println("System quitting...");
                    running = false;
                }
                default -> System.out.println("Option does not exist, try again.");
            }
        }
    }

    public static void editPokemon(Scanner scan) {

        boolean running = true;

        System.out.print("Select the Pokémon you wish to edit: ");
        scan.nextLine();
        String name = scan.nextLine();
        Pokemon selected = null;
        for (Pokemon poke : pokelist) {
            if (name.equalsIgnoreCase(poke.name)) {
                selected = poke;
                while (running) {
                    System.out.println(selected);
                    System.out.println("1.Change Pokémon name  2.Change Pokémon HP  ");
                    System.out.println("3.Change Pokémon type  4.Teach a new attack  5.Remove attack  6.Cancel");
                    System.out.print("Select option: ");
                    int option = scan.nextInt();
                    switch (option) {
                        case 1 -> {
                            System.out.print("Enter new Pokémon name: ");
                            scan.nextLine();
                            String newName = scan.nextLine();
                            poke.name = newName;
                            System.out.println("New name is: " + poke.name);
                        }
                        case 2 -> {
                            System.out.print("Enter new Pokémon max HP: ");
                            scan.nextLine();
                            int newHp = scan.nextInt();
                            poke.MaxHp = newHp;
                            poke.CurrentHp = newHp;
                            System.out.println("New Pokémon max HP is: " + poke.MaxHp);
                        }
                        case 3 -> {
                            System.out.print("Enter new Pokémon type: ");
                            scan.nextLine();
                            String newType = scan.nextLine();
                            Types newtype = Types.valueOf(newType);
                            poke.type1 = newtype;
                            if (poke.type2 != null) {
                                System.out.print("Enter 2nd Pokémon type: ");
                                String newType2 = scan.nextLine();
                                Types newtype2 = Types.valueOf(newType2);
                                poke.type2 = newtype2;
                                System.out.println("New Pokémon type is : " + poke.type1 + " | " + poke.type2);
                            } else if (poke.type2 == null) {
                                System.out.println("New Pokémon type is : " + poke.type1);
                            }
                        }
                        case 4 -> {
                            System.out.print("Enter new Pokémon attack name: ");
                            scan.nextLine();
                            String newattackname = scan.nextLine();
                            System.out.print("Enter new attack's base damage: ");
                            int newattackdmg = scan.nextInt();
                            System.out.print("Enter new attack's accuracy: ");
                            int newattackacc = scan.nextInt();
                            scan.nextLine();
                            System.out.print("Enter new attack's type: ");
                            String newattacktype = scan.nextLine();
                            Types newtype = Types.valueOf(newattacktype);
                            selected.attacks.add(new Attack(newattackname, newattackdmg, newattackacc, newtype));
                            System.out.println("Attack added successfully.");
                        }
                        case 5 -> {
                            System.out.print("Which Pokémon attack to remove?: ");
                            scan.nextLine();
                            String atkremove = scan.nextLine();

                            for (Attack pokeattack : selected.attacks) {
                                if (atkremove.equalsIgnoreCase(pokeattack.name)) {
                                    selected.attacks.remove(pokeattack);
                                    System.out.println("Attack removed successfully.");
                                    break;
                                } else if (atkremove != (pokeattack.name)) {
                                    System.out.println("Attack doesn't exist. Try again.");
                                    break;
                                }
                            }
                        }
                        case 6 -> {
                            System.out.println("Going back.");
                            running = false;
                        }
                        default -> System.out.println("Selected option does not exist.");
                    }
                }
                    break;
                }
            }
            if (selected == null) {
                System.out.println("Selected Pokémon does not exist.");
            }
        }

        public static void removePokemon (Scanner scan){
            System.out.print("Select the Pokémon you wish to remove: ");
            scan.nextLine();
            String name = scan.nextLine();
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


        public static void resetData () {
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

            System.out.println("Pokédex cleared successfully.");
        }


    }