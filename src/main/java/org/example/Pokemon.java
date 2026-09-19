package org.example;

import org.example.Types;

public class Pokemon {
    String name;
    Types type1;
    Types type2;
    int MaxHp;
    int CurrentHp;


    public Pokemon(String name, Types type1, Types type2, int maxHp, int currentHp) {
        this.name = name;
        this.type1 = type1;
        this.type2 = type2;
        MaxHp = maxHp;
        CurrentHp = currentHp;
    }

    public Pokemon(String name, Types type1, int maxHp, int currentHp) {
        this.name = name;
        this.type1 = type1;
        MaxHp = maxHp;
        CurrentHp = currentHp;
    }

}
