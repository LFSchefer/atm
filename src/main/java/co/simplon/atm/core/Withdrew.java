package co.simplon.atm.core;

import static co.simplon.atm.ui.UserInterface.internalError;
import static co.simplon.atm.ui.UserInterface.message;

public class Withdrew extends Operation {

    private final Card card;
    private final Bank bank;
    private final Mecha mecha;

    public Withdrew(Card card) {
        this.bank = new Bank();
        this.mecha = new Mecha();
        this.card = card;
    }

    @Override
    public void doExecute(String input) {
        message("Withdrew");
        if (input != null && !input.isBlank()) {
            if (validateAmount(input)) {
                var operationAuthorized = bank.authorizeWithdrew(Integer.valueOf(input), card);
                if (operationAuthorized) {
                    message("Transaction accepted");
                    var reserveResponse = mecha.reserveIsEnought(Integer.valueOf(input));
                    if (reserveResponse) {
                        if (!mecha.isJamed()) {
                        mecha.doWithdrew(Integer.valueOf(input));
                        bank.doWithdrew(Integer.valueOf(input), card);
                        message("Take your cash");
                        } else {
                            internalError("Mechanical system is jamed"
                                    + System.lineSeparator()
                                    + "No money have been withdrew from your account");
                        }
                    } else {
                        internalError("Sorry not enough money in the ATM");
                    }
                } else {
                    message("Amount unauthorized");
                }
            } else {
                message(String.format("Please enter a valid amount ! %s %s is not a valid value", System.lineSeparator(), input));
            }
        } else {
            message("Please enter the desired amount:");
        }
        message("'exit' to quit current operation");
    }

    private boolean validateAmount(String amount) {
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
