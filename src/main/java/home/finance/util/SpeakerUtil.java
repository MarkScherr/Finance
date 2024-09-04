package home.finance.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class SpeakerUtil {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy/MM/dd");

    public static LocalDate handleInputMonths(Scanner scanner) {
        String message = "Please enter how many months you would like to view: ";
        int result = -10;
        while (result == -10) {
            System.out.print(message);
            try {
                result = scanner.nextInt();
                if (result < 0) {
                    System.out.println("gotta have a number greater than zero");
                }
            } catch (Exception ex) {
                System.out.println("!!!!!!Unable to understand request please attempt again!!!!!!\n");
            }
        }
        LocalDate now = LocalDate.now();
        LocalDate monthsAgoTime = now.minusMonths(result);
        return monthsAgoTime.withDayOfMonth(1);
    }

    public static LocalDate handleInputDate(Scanner scanner, String message) {
        boolean isCorrectDateFormat = false;
        LocalDate result = LocalDate.now();
        while (!isCorrectDateFormat) {
            System.out.print(message);
            try {
                String input = "";
                while (input.isBlank()) {
                    input = scanner.nextLine();
                }
                if (input.equalsIgnoreCase("t")) {
                    isCorrectDateFormat = true;
                    result = LocalDate.now();
                } else {
                    result = LocalDate.parse(input, FORMATTER);
                    isCorrectDateFormat = true;
                }
            } catch (Exception ex) {
                System.out.println("!!!!!!Unable to understand request please attempt again!!!!!!\n");
            }
        }
        return result;
    }

    public static int handleInputInteger(Scanner scanner, String message, boolean isNullable) {
        int result = -10;
        while (result == -10) {
            System.out.print(message);
            try {
                result = scanner.nextInt();
                if (result < 0) {
                    if (result == -1 && isNullable) {
                        System.out.println("Setting value to Not Applicable");
                    } else {
                        System.out.println("gotta have a positive integer  other than specifically -1 if stated before");
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
                String value = "";
                while(value.isBlank()) {
                    value = scanner.nextLine();
                }
                if (value.equalsIgnoreCase("y") ||
                        value.equalsIgnoreCase("yes")) {
                    result = true;
                    isCorrectData = true;
                } else if (value.equalsIgnoreCase("n") ||
                        value.equalsIgnoreCase("no")) {
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
