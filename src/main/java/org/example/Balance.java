package org.example;

public class Balance extends Operation{

    private final Bank bank;

    public Balance() {
        this.bank = new Bank();
    }

    @Override
    public void doExecute(String input) {
        Integer balance = bank.getBalance();
        if (balance != null) {
            System.out.println(String.format("Your account balance: %s $", balance));
        }
        System.out.println("'exit' to quit current operation");
    }
}
