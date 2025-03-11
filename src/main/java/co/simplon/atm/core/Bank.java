package co.simplon.atm.core;

import co.simplon.atm.persistance.FileIO;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Bank {

    private Map<String, String> accounts;
    private final String path = "src/main/resources/bank.csv";

    public Integer getBalance(Card card) {
        String line = FileIO.returnMatchingLine(card.getAccountNumber()
                ,path
                ,"Sorry can not reach your bank");
        return Integer.valueOf(line.split(",")[1]);
    }

    public boolean authorizeWithdrew(Integer value, Card card) {
        var currentBalance = getBalance(card);
        if ( currentBalance != null && currentBalance > 0 ) {
            return currentBalance >= value;
        }
        return false;
    }

    public void doWithdrew(Integer input, Card card) {
        Integer currentBalance = getBalance(card);
        Integer updatedBalance = currentBalance - input;
        updateBalance(updatedBalance, card);
    }

    private void updateBalance(Integer newBalance, Card card) {
       Set<String> accountSet =  FileIO.getAccounts(path,
               "Sorry can not reach your bank");
        accounts = new HashMap<>();
        accountSet.forEach( line -> accounts.put(line.split(",")[0],line.split(",")[1]));
        accounts.replace(card.getAccountNumber(), String.valueOf(newBalance));
       FileIO.writeFile(accounts, path);
    }



}
