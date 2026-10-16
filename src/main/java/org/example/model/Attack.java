package org.example.model;

public class Attack {
    private final String name;
    private final int baseDmg;
    private final int accuracy;
    private final Types atkType;

    public Attack(String name, int baseDmg, int accuracy, Types atkType) { //constructor attack
        this.name = name;
        this.baseDmg = baseDmg;
        this.accuracy = accuracy;
        this.atkType = atkType;
    }

    public String getName() {
        return name;
    }


    public int getBaseDmg() {
        return baseDmg;
    }


    public int getAccuracy() {
        return accuracy;
    }


    public Types getAtkType() {
        return atkType;
    }


    @Override     // so it becomes readable output
    public String toString() {
        return "Attack: " + name + " (BaseDmg: " + baseDmg + " | Accuracy: " + accuracy + " | Type: " + atkType + ")";


    }
}
