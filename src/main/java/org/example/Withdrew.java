package org.example;

public class Withdrew extends Operation {

    private final Bank bank;

    public Withdrew() {
        this.bank = new Bank();
    }

    @Override
    public void doExecute(String input) {
        System.out.println("Withdrew:");
        System.out.println("'exit' to quit current operation");
        bank.authorizeWithdrew(null);
    }
}
