package org.example;

import org.example.Types;

public class Attack {
    private String name;
    private int baseDmg;
    private int accuracy;
    private Types atkType;

    public Attack(String name, int baseDmg, int accuracy, Types atkType) { //constructor attack
        this.name = name;
        this.baseDmg = baseDmg;
        this.accuracy = accuracy;
        this.atkType = atkType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBaseDmg() {
        return baseDmg;
    }

    public void setBaseDmg(int baseDmg) {
        this.baseDmg = baseDmg;
    }

    public int getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(int accuracy) {
        this.accuracy = accuracy;
    }

    public Types getAtkType() {
        return atkType;
    }

    public void setAtkType(Types atkType) {
        this.atkType = atkType;
    }

    @Override     // so it becomes readable output
    public String toString() {
        return "Attack: " + name + " (BaseDmg: " + baseDmg + " | Accuracy: " + accuracy + " | Type: " + atkType + ")";













    }
}
