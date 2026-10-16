package org.example.utility;

public class SleepHelper {
    public static void sleep(int milli) {
        try {
            Thread.sleep(milli);
        } catch (InterruptedException e) {
            System.err.println("Unable to sleep.");
        }
    }
}
