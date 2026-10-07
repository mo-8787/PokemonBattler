package org.example.model;

import java.util.ArrayList;

public class Pokemon {
    private String name;
    private Types type1;
    private Types type2;
    private int MaxHp;
    private int CurrentHp;
    private ArrayList<Attack> attacks = new ArrayList<>();


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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Types getType1() {
        return type1;
    }

    public void setType1(Types type1) {
        this.type1 = type1;
    }

    public Types getType2() {
        return type2;
    }

    public void setType2(Types type2) {
        this.type2 = type2;
    }

    public int getMaxHp() {
        return MaxHp;
    }

    public void setMaxHp(int maxHp) {
        MaxHp = maxHp;
    }

    public int getCurrentHp() {
        return CurrentHp;
    }

    public void setCurrentHp(int currentHp) {
        CurrentHp = currentHp;
    }

    public ArrayList<Attack> getAttacks() {
        return attacks;
    }

    public void setAttacks(ArrayList<Attack> attacks) {
        this.attacks = attacks;
    }

    public void takeDamage(int amount) {
        if (amount < 0) throw new IllegalArgumentException("Damage cannot have negative value");
        setCurrentHp(Math.max(0, getCurrentHp()-amount));
    }
    public void heal(int amount) {
        if (amount < 0) throw new IllegalArgumentException("Healing cannot have negative value.");
        setCurrentHp(Math.min(getMaxHp(), getCurrentHp() + amount));
    }

    public boolean isFainted() {return getCurrentHp() <= 0;}












    @Override                         //so it becomes readable output
    public String toString() {
        String result = "\n" + name + " | Type: " + type1;
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
