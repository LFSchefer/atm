package org.example;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.stream.Stream;

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
        Path path = Path.of("src/main/resources/bank.txt");
        File file = new File(path.toUri());
        var exist = file.exists();
        if (!exist) {
            System.out.println("Sorry can not reach your bank");
        } else {
            try {
            Stream<String> lines = Files.lines(path);
            Integer currentBalance = lines.map(Integer::valueOf).toList().getLast();
            return currentBalance;
            } catch (IOException e) {
                System.out.println("Sorry, an error as occurred");
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    void updateBalance(Integer newBalance) {
        String newLine = System.lineSeparator() + String.valueOf(newBalance);
        try {
            Files.write(Paths.get("src/main/resources/bank.txt"), newLine.getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);
            System.out.println("DONE");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



}
