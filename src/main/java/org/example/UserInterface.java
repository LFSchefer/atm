package org.example;

import java.util.Scanner;

public class UserInterface {

    public UserInterface() {
    }

    public void begin() {
        System.out.println("coucou");
        Scanner scanner = new Scanner(System.in);
        String userInput = "null";
        while (!userInput.equals("q")) {
            userInput = scanner.nextLine();
            System.out.println(userInput);
        }
        System.out.println("Cya");
    }
}
