package org.example;

import java.time.Instant;
import java.util.Scanner;

public class UserInterface {

    private final ATM atm;

    public UserInterface() {
        this.atm = new ATM();
    }

    public void begin() {
        message("Welcome" + System.lineSeparator() + "Enter your card number:");
        Scanner scanner = new Scanner(System.in);
        String userInput = "null";
        while (!userInput.equals("q")) {
            userInput = scanner.nextLine();
            atm.doExecute(userInput);
        }
        message("Cya");
    }

    public static void message(String msg) {
        System.out.println(msg);
    }

    public static void internalError(String msg) {
        message(msg);
        String log = String.format("%s : %s",Instant.now(), msg);
        FileIO.writeNewLine(log ,"src/main/resources/error-logs.txt");
    }
}
