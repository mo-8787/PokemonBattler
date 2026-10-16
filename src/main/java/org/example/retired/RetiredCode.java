package org.example.retired;

public class RetiredCode {
    /*  public static void editPokemon(Scanner scan) {    // code that was in main but no longer used for this assignment.
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
    } */
}
