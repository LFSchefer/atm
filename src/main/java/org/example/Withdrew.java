package org.example;

public class Withdrew extends Operation {

    private final Bank bank;
    private final Mecha mecha;

    public Withdrew() {
        this.bank = new Bank();
        this.mecha = new Mecha();
    }

    @Override
    public void doExecute(String input) {
        UserInterface.message("Withdrew");
        if (input != null && !input.isBlank()) {
            if (validateAmount(input)) {
                var operationAuthorized = bank.authorizeWithdrew(Integer.valueOf(input));
                if (operationAuthorized) {
                    UserInterface.message("Transaction accepted");
                    var reserveResponse = mecha.reserveIsEnought(Integer.valueOf(input));
                    if (reserveResponse) {
                        if (!mecha.isJamed()) {
                        mecha.doWithdrew(Integer.valueOf(input));
                        bank.doWithdrew(Integer.valueOf(input));
                        UserInterface.message("Take your cash");
                        } else {
                            UserInterface.internalError("Mechanical system is jamed"
                                    + System.lineSeparator()
                                    + "No money have been withdrew from your account");
                        }
                    } else {
                        UserInterface.internalError("Sorry not enough money in the ATM");
                    }
                } else {
                    UserInterface.message("Amount unauthorized");
                }
            } else {
                UserInterface.message(String.format("Please enter a valid amount ! %s %s is not a valid value", System.lineSeparator(), input));
            }
        } else {
            UserInterface.message("Please enter the desired amount:");
        }
        UserInterface.message("'exit' to quit current operation");
    }

    boolean validateAmount(String amount) {
        try {
            int value = Integer.parseInt(amount);
            if (value < 2000 && value % 10 == 0 && value > 0) {
                return true;
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }
}
