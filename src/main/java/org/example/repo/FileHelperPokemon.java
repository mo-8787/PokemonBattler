package org.example.repo;

import org.example.model.Attack;
import org.example.model.Pokemon;
import org.example.model.Types;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;



public class FileHelperPokemon {


    public static void savePokemon(ArrayList<Pokemon> pokelist, Path pokepath, Path attackpath) {
        ArrayList<String> pokeLines = new ArrayList<>();
        ArrayList<String> attackLines = new ArrayList<>();

        for (Pokemon poke : pokelist) {
            if (poke.getType2() != null) {
                pokeLines.add(poke.getName() + "," + poke.getType1() + "," + poke.getType2() + "," + poke.getMaxHp() + "," + poke.getCurrentHp());
            } else if (poke.getType2() == null) {
                pokeLines.add(poke.getName() + "," + poke.getType1() + "," + poke.getMaxHp() + "," + poke.getCurrentHp());
            }
            for (Attack attack : poke.getAttacks()) {
                attackLines.add(attack.getName() + "," + attack.getBaseDmg() + "," + attack.getAccuracy() + "," + attack.getAtkType() + "," + poke.getName());
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

    public static ArrayList<Pokemon> uploadPokemon(Path pokepath, Path attackpath) {
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
                return uploadedPokemon;
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
                    if (newpoke.getName().equalsIgnoreCase(atktoPoke)) {
                        newpoke.getAttacks().add(attack);
                        break;
                    }
                }
            }
            System.out.println("New attacks added successfully");
        } catch (IOException e) {
            System.err.println("Upload unsuccessful.");
        }
        return uploadedPokemon;

    }
}
