package org.example;

import org.example.Types;

public class Attack {
    String name;
    int baseDmg;
    int accuracy;
    Types atkType;

    public Attack (String name, int baseDmg, int accuracy, Types atkType) { //constructor attack
        this.name = name;
        this.baseDmg = baseDmg;
        this.accuracy = accuracy;
        this.atkType = atkType;
    }
}
