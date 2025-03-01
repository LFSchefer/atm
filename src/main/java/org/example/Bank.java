package org.example;

import java.io.File;
import java.nio.file.Path;

public class Bank {

    public Integer getBalance() {
        readBalance();
        return null;
    }

    public boolean authorizeWithdrew(Integer value) {
        readBalance();
        return true;
    }

    private Integer readBalance() {
        Path path = Path.of("src/main/resources/bank.txt");
        File file = new File(path.toUri());
        var exist = file.exists();
//        try {
//
//        } catch (RuntimeException e) {
//            throw new RuntimeException(e);
//        }
        return null;
    }

}
