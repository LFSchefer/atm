package co.simplon.atm;

import java.util.Objects;

import static co.simplon.atm.UserInterface.message;

public class ATM {

    private Card card;
    private int remainingTry = 3;
    private boolean validated = false;
    private boolean firstInput = true;
    private Operation operation;
    private final OperationFactory factory;

    public ATM() {
        this.factory = new OperationFactory();
    }

    public void doExecute(String input) {
        if (card == null) {
            buildCard(input);
        } else if (!validated) {
            validatePin(input);
        }
        else {
            choseOperation(input);
        }
    }

    private void buildCard(String input) {
        try {
            String cardLine = FileIO.returnMatchingLine(input, "src/main/resources/card.csv", "Error no card found");
            String[] cardText = cardLine.split(",");
            card = new Card(cardText[0], cardText[1], cardText[2], Boolean.valueOf(cardText[3]));
            doExecute(null);
        } catch (Exception e) {
            message("no matching card found");
        }
    }

    private void validatePin(String input) {
        if (firstInput) {
            message("Please enter your pin code:" + System.lineSeparator() + "remaining try: " + remainingTry);
            firstInput = false;
        } else if (!validated && remainingTry>0) {
            validated = card.validePincode(input);
            if (validated) {
                message("SUCCESS");
                doExecute(null);
            } else {
                message("Please enter your pin code:" + System.lineSeparator() + "remaining try: " + remainingTry);
            }
        } else if (!validated && remainingTry<= 0) {
            message("To much try, your card as been deactivated");
            System.exit(0);
        }
        remainingTry--;
    }

    private void choseOperation(String input) {
        if (input == null && operation == null) {
            message(String.format("Please choose an operation:%s1. Withdrew%s2. Balance", System.lineSeparator(), System.lineSeparator()));
        } else if (operation == null) {
            operation = factory.create(input, card);
            choseOperation(null);
        } else if (Objects.equals(input, "exit")) {
            operation = null;
            choseOperation(null);
        } else {
            operation.doExecute(input);
        }
    }
}
