package utils;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class InputUtils {

    private static Scanner input = new Scanner(System.in);

    public static boolean readBoolean(String message) {
        boolean data = true;
        boolean dataOk = false;
        String answer = "";

        do {
            try {
                System.out.println(message);
                answer = input.nextLine();
                if (answer.equalsIgnoreCase("yes")) {
                    dataOk = true;
                    data = false;
                } else if (answer.equalsIgnoreCase("no")) {
                    dataOk = true;
                }
            } catch (Exception e) {
                System.err.println("Format error. Please, try again. Type only Yes or No.");
            }

        } while (!dataOk);
        return data;
    }

    public static int readInt(String message) {
        int data = 0;
        boolean dataOk = false;

        while (!dataOk) {
            try {
                System.out.println(message);
                data = input.nextInt();
                dataOk = true;
            } catch (InputMismatchException e) {
                System.err.println("Format error. Please, try again.");
                input.nextLine();
            } finally {
                input.nextLine();
            }
        }
        return data;

    }

    public static String readString(String message) {
        String data = "";
        boolean dataOk = false;

        while (!dataOk) {
            try {
                System.out.println(message);
                data = input.nextLine();
                dataOk = true;
            } catch (Exception e) {
                System.err.println("Error. Please, try again.");
                input.nextLine();
            }
        }
        return data;
    }

    public static double readDouble(String message) {
        double data = 0d;
        boolean dataOk = false;

        while (!dataOk) {
            try {
                System.out.println(message);
                data = input.nextDouble();
                dataOk = true;
            } catch (InputMismatchException e) {
                System.err.println("Format error. Please, try again.");
                input.nextLine();
            } finally {
                input.nextLine();
            }
        }
        return data;
    }

    public static <E extends Enum<E>> E readEnum(String message, Class<E> enumType) {
        E[] enumValues = enumType.getEnumConstants();
        E selectedValue = null;
        boolean dataOk = false;
        while (!dataOk) {
            try {
                System.out.println(message);
                for (int i = 0; i < enumValues.length; i++) {
                    System.out.println(i + 1 + "." + enumValues[i]);
                }
                System.out.println("Choose an option (1-" + enumValues.length + "):");
                int ordinal = input.nextInt();

                if (ordinal > 0 && ordinal <= enumValues.length) {
                    selectedValue = enumValues[ordinal - 1];
                    dataOk = true;
                } else {
                    System.out.println("Invalid option, choose an option between (1 and " + enumValues.length + "):");
                }
            } catch (InputMismatchException e) {
                System.err.println("Format error. Please, try again.");
                input.nextLine();
            } finally {
                input.nextLine();
            }
        }
        return selectedValue;
    }

    public static String readEmail(String message) {
        String email = "";
        String regex = "^(?=.{1,45}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        boolean dataOk = false;

        while (!dataOk) {
            System.out.println(message);
            email = input.nextLine();
            if (!Pattern.compile(regex).matcher(email).matches()) {
                System.out.println("The email format is invalid, try again.");
            } else {
                dataOk = true;
            }
        }
        return email;
    }
}
