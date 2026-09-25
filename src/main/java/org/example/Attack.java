package org.example;

import org.example.Types;

public class Attack {
    public String name;
    public int baseDmg;
    public int accuracy;
    public Types atkType;

    public Attack(String name, int baseDmg, int accuracy, Types atkType) { //constructor attack
        this.name = name;
        this.baseDmg = baseDmg;
        this.accuracy = accuracy;
        this.atkType = atkType;
    }

    @Override     // so it becomes readable output
    public String toString() {
        return "Attack: " + name + " (BaseDmg: " + baseDmg + " | Accuracy: " + accuracy + " | Type: " + atkType + ")";
    }
}
