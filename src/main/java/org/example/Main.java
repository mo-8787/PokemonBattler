package org.example;

import java.util.ArrayList;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        Pokemon p1 = new Pokemon("Piplup", Types.WATER, 53, 53);
        p1.attacks.add(new Attack("Surf", 90, 100, Types.WATER));

        Pokemon p2 = new Pokemon("Farfetch'd",Types.FLYING, Types.NORMAL, 52, 52);
        p2.attacks.add(new Attack("Peck", 35, 100, Types.FLYING));

        Pokemon p3 = new Pokemon("Rayquaza",Types.DRAGON,Types.FLYING, 105, 105);
        p3.attacks.add(new Attack("Dragon Ascent",140, 100,Types.FLYING));

        Pokemon p4 = new Pokemon("Torchic", Types.FIRE, 45,45);
        p4.attacks.add(new Attack("Ember",40, 100,Types.FIRE));

        Pokemon p5 = new Pokemon("Dratini",Types.DRAGON,41,41);
        p5.attacks.add(new Attack("Twister",40,100, Types.DRAGON));

        Pokemon p6 = new Pokemon("Oshawott", Types.WATER, 55,55);
        p6.attacks.add(new Attack("Water Gun",40,100,Types.WATER));

        Pokemon p7 = new Pokemon("Articuno",Types.ICE,Types.FLYING,90,90);
        p7.attacks.add(new Attack("Blizzard",110, 70,Types.ICE));







        ArrayList <Pokemon> pokelist = new ArrayList<>();
        pokelist.add(p1);
        pokelist.add(p2);
        pokelist.add(p3);
        pokelist.add(p4);
        pokelist.add(p5);
        pokelist.add(p6);
        pokelist.add(p7);




        }
    }