package org.example;

public class Bank {

    public Integer getBalance() {
        return readBalance();
    }

    public boolean authorizeWithdrew(Integer value) {
        var currentBalance = readBalance();
        if ( currentBalance != null && currentBalance > 0 ) {
            return currentBalance >= value;
        }
        return false;
    }

    public void doWithdrew(Integer input) {
        Integer currentBalance = readBalance();
        Integer updatedBalance = currentBalance - input;
        updateBalance(updatedBalance);
    }

    Integer readBalance() {
        return FileIO.readLastLine("src/main/resources/bank.txt",
                "Sorry can not reach your bank");
    }

    void updateBalance(Integer newBalance) {
        FileIO.writeNewLine(newBalance, "src/main/resources/bank.txt");
    }



}
