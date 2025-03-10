package org.example;

import java.util.Objects;

public class ATM {

    private final Card card;
    private int remainingTry = 3;
    private boolean validated = false;
    private boolean firstInput = true;
    private Operation operation;
    private final OperationFactory factory;

    public ATM() {
        this.card = new Card();
        this.factory = new OperationFactory();
    }

    public void doExecute(String input) {
        if (!validated) {
            validatePin(input);
        }
        else {
            choseOperation(input);
        }
    }

    void validatePin(String input) {
        if (firstInput) {
            UserInterface.message("Please enter your pin code:" + System.lineSeparator() + "remaining try: " + remainingTry);
            firstInput = false;
        } else if (!validated && remainingTry>0) {
            validated = card.validePincode(input);
            if (validated) {
                UserInterface.message("SUCCESS");
                doExecute(null);
            } else {
                UserInterface.message("Please enter your pin code:" + System.lineSeparator() + "remaining try: " + remainingTry);
            }
        } else if (!validated && remainingTry<= 0) {
            UserInterface.message("To much try, your card as been deactivated");
            System.exit(0);
        }
        remainingTry--;
    }

    void choseOperation(String input) {
        if (input == null && operation == null) {
            UserInterface.message(String.format("Please choose an operation:%s1. Withdrew%s2. Balance", System.lineSeparator(), System.lineSeparator()));
        } else if (operation == null) {
            operation = factory.create(input);
            choseOperation(null);
        } else if (Objects.equals(input, "exit")) {
            operation = null;
            choseOperation(null);
        } else {
            operation.doExecute(input);
        }
    }
}
