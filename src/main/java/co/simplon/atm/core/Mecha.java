package co.simplon.atm.core;

import co.simplon.atm.persistance.FileIO;
import co.simplon.atm.ui.UserInterface;

public class Mecha {

    private boolean jamed = false;
    private final String path = "src/main/resources/atm-reserve.txt";

    public boolean isJamed() {
        return jamed;
    }

    public boolean reserveIsEnought(Integer desired) {
        var reserveAmound = readReserve();
        if (reserveAmound != null) {
            return reserveAmound >= desired;
        }
        return false;
    }

    private Integer readReserve() {
        return FileIO.readLastLine(path,
                "Sorry mechanical problem in the ATM");
    }

    public void doWithdrew(Integer desired) {
        var actualReserve = readReserve();
        var updatedReserve = actualReserve - desired;
        updateReserve(updatedReserve);
        UserInterface.message("BRR-BRR-BRR");
    }

    private void updateReserve(Integer newReserve) {
        FileIO.writeNewLine(newReserve, path);
    }
}
