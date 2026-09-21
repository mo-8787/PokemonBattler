package org.example;

import org.example.Types;

import java.util.ArrayList;

public class Pokemon {
    String name;
    Types type1;
    Types type2;
    int MaxHp;
    int CurrentHp;
    ArrayList<Attack> attacks = new ArrayList<>();


    public Pokemon(String name, Types type1, Types type2, int maxHp, int currentHp) {    //constructor for dual type
        this.name = name;
        this.type1 = type1;
        this.type2 = type2;
        MaxHp = maxHp;
        CurrentHp = currentHp;
    }
    public Pokemon(String name, Types type1, int maxHp, int currentHp) { //constructor for single type
        this.name = name;
        this.type1 = type1;
        MaxHp = maxHp;
        CurrentHp = currentHp;
    }

    @Override
    public String toString() {
        String result = name + " | Type: " + type1;
        if (type2 != null) {
            result += " | Type: " + type2;
        }
        result += " | MaxHp: " + MaxHp + " | CurrentHp: " + CurrentHp;

        for (Attack a : attacks) {
            result += "\n - " + a;
        }
        return result;
    }

}
