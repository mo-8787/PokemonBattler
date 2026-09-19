package org.example;

import java.util.ArrayList;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        Pokemon p1 = new Pokemon("Piplup", Types.WATER, 70, 70);

        Pokemon p2 = new Pokemon("Farfetch'd",Types.FLYING, Types.NORMAL, 90, 90);




        ArrayList <Pokemon> pokelist = new ArrayList<>();
        pokelist.add(p1);
        pokelist.add(p2);

        }
    }