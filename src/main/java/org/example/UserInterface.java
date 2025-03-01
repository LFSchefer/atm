package org.example;

import java.util.Scanner;

public class UserInterface {

    private final ATM atm;

    public UserInterface() {
        this.atm = new ATM();
    }

    public void begin() {
        System.out.println("Welcome" + System.lineSeparator() + "Press any key");
        Scanner scanner = new Scanner(System.in);
        String userInput = "null";
        while (!userInput.equals("q")) {
            userInput = scanner.nextLine();
            atm.doExecute(userInput);
        }
        System.out.println("Cya");
    }
}
