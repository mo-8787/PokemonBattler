package org.example.repo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileHelperBattler {
    public static void saveResults(int wins, int losses, Path resultpath) {
        try {
            Files.writeString(resultpath, wins + "," + losses);
        } catch (IOException e) {
            System.err.println("Save not possible, try later.");
        }
    }

    public static int[] loadResults(Path resultpath) {
        int[] results = {0, 0};
        try {
            if (!Files.exists(resultpath)) {
                return results;
            }
            String line = Files.readString(resultpath).trim();
            if (line.isEmpty()) return results;
            String[] parts = line.split(",");
            results[0] = Integer.parseInt(parts[0]);
            results[1] = Integer.parseInt(parts[1]);
        } catch (IOException | NumberFormatException e) {
            System.err.println("Results could not be uploaded, try later.");
        }
        return results;
    }
}
