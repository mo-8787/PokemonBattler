package org.example.utility;

import org.example.model.Types;

import java.util.Scanner;

public class InputHelper {
    public static int readInt(Scanner scan, String prompt) {
        while (true) {
            System.out.print(prompt);
            if (scan.hasNextInt()) {
                int input = scan.nextInt();
                if (input >= 1) {
                    return input;
                }
                scan.nextLine();
                System.out.println("Enter positive integer. ");
                continue;
            }
            System.out.println("Enter positive integer. ");
            scan.next();
        }
    }

    public static boolean readBoolean(Scanner scan, String prompt) {
        while (true) {
            System.out.print(prompt);
            if (scan.hasNextBoolean()) {
                return scan.nextBoolean();
            }
            System.out.println("Enter valid option. ");
            scan.next();
        }
    }

    public static Types readTypes(Scanner scan, String prompt) {
        while (true) {
            System.out.print(prompt);
            String stringtype = scan.nextLine();
            stringtype = stringtype.toUpperCase();
            try {
                Types type = Types.valueOf(stringtype);
                return type;
            } catch (IllegalArgumentException e) {
                System.out.println("Enter a valid type. ");
            }
        }
    }

    public static String readname(Scanner scan, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scan.nextLine().trim();
            if (input.length() < 100 && !input.isEmpty() && !input.contains(",")) {
                return input;
            }
            System.out.println("Enter a valid name.");
        }
    }

    public static int readaccuracy(Scanner scan, String prompt) {
        while (true) {
            System.out.print(prompt);
            if (scan.hasNextInt()) {
                int input = scan.nextInt();
                if (input <= 100 && input >= 0) {
                    return input;
                }
                System.out.println("Invalid accuracy, it needs to be between 0-100.");
                scan.nextLine();
                continue;
            }
            System.out.println("Invalid accuracy, it needs to be between 0-100.");
            scan.next();
        }
    }
}
