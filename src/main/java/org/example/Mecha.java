package org.example;

public class Mecha {

    private boolean jamed = false;

    public boolean isJamed() {
        return jamed;
    }

    public boolean reserveIsEnought(Integer desired) {
        var reserveAmound = readBalance();
        if (reserveAmound != null) {
            return reserveAmound >= desired;
        }
        return false;
    }

    Integer readBalance() {
        return FileIO.readLastLine("src/main/resources/atm-reserve.txt",
                "Sorry mechanical problem in the ATM");
    }

    public void doWithdrew(Integer desired) {
        var actualReserve = readBalance();
        var updatedReserve = actualReserve - desired;
        updateReserve(updatedReserve);
        UserInterface.message("BRR-BRR-BRR");
    }

    void updateReserve(Integer newReserve) {
        FileIO.writeNewLine(newReserve, "src/main/resources/atm-reserve.txt");
    }
}
