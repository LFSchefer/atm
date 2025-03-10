package org.example;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.stream.Stream;

public class Mecha {

    public boolean reserveIsEnought(Integer desired) {
        var reserveAmound = readBalance();
        if (reserveAmound != null) {
            return reserveAmound >= desired;
        }
        return false;
    }

    Integer readBalance() {
        Path path = Path.of("src/main/resources/atm-reserve.txt");
        File file = new File(path.toUri());
        var exist = file.exists();
        if (!exist) {
            System.out.println("Sorry mechanical problem in the ATM");
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

    public void doWithdrew(Integer desired) {
        var actualReserve = readBalance();
        var updatedReserve = actualReserve - desired;
        updateReserve(updatedReserve);
        System.out.println("BRR-BRR-BRR");
    }

    void updateReserve(Integer newReserve) {
        String newLine = System.lineSeparator() + String.valueOf(newReserve);
        try {
            Files.write(Paths.get("src/main/resources/atm-reserve.txt"), newLine.getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
