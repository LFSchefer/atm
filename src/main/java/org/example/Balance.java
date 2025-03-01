package org.example;

public class Balance extends Operation{

    private final Bank bank;

    public Balance() {
        this.bank = new Bank();
    }

    @Override
    public void doExecute(String input) {
        System.out.println("Your account balance:");
        bank.getBalance();
        System.out.println("'exit' to quit current operation");
    }
}
