package org.example.cart.Utility;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ConsoleColor {

    // Console Coloring for improved logging visibility
    private static final String RESET = "\u001B[0m";
    private static final String GREEN = "\u001B[32m";  // Green for successful operations
    private static final String YELLOW = "\u001B[33m"; // Yellow for warnings
    private static final String RED = "\u001B[31m";    // Red for errors

    public static String Green(String message) {
        return GREEN + message + RESET;
    }

    public static String Yellow(String message) {
        return YELLOW + message + RESET;
    }

    public static String Red(String message) {
        return RED + message + RESET;
    }
}