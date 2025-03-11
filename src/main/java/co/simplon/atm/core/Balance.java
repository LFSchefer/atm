package co.simplon.atm.core;

import static co.simplon.atm.ui.UserInterface.message;

public class Balance extends Operation {

    private final Card card;
    private final Bank bank;

    public Balance(Card card) {
        this.bank = new Bank();
        this.card = card;
    }

    @Override
    public boolean doExecute(String input) {
        Integer balance = bank.getBalance(card);
        if (balance != null) {
            message(String.format("Your account balance: %s $", balance));
        }
        return true;
    }
}
