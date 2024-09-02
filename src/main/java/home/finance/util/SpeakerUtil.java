package home.finance.util;

import java.util.Scanner;

public class SpeakerUtil {

    public static int handleInputInteger(Scanner scanner, String message, boolean isNullable) {
        int result = -10;
        while (result == -10) {
            System.out.print(message);
            try {
                result = scanner.nextInt();
                if (result < 1) {
                    if (result == -1 && isNullable) {
                        System.out.println("Setting value to Not Applicable");
                    } else {
                        System.out.println("gotta have a number greater than zero other than specifically -1 if stated before");
                    }
                }
            } catch (Exception ex) {
                System.out.println("!!!!!!Unable to understand request please attempt again!!!!!!\n");
            }
        }
        return result;
    }

    public static String handleInputString(Scanner scanner, String message) {
        String result = null;
        while (result == null) {
            System.out.print(message);
            try {
                result = scanner.nextLine();
            } catch (Exception ex) {
                System.out.println("!!!!!!What the fuck you say? Unable to understand request please attempt again!!!!!!\n");
            }
        }
        return result;
    }

    public static boolean handleInputBooleanString(Scanner scanner, String message) {
        boolean isCorrectData = false;
        boolean result = false;
        while (!isCorrectData) {
            System.out.print(message);
            try {
                String value = scanner.nextLine();
                if (value.equalsIgnoreCase("y") ||
                        value.equalsIgnoreCase("yes")) {
                    result = true;
                    isCorrectData = true;
                } else if (value.equalsIgnoreCase("n") ||
                        value.equalsIgnoreCase("no")) {
                    result = false;
                    isCorrectData = true;
                } else {
                    throw new Exception();
                }

            } catch (Exception ex) {
                System.out.println("!!!!!!unable to understand request please attempt again!!!!!!\n");
            }
        }
        return result;
    }
}
