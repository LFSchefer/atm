package co.simplon.atm.core;

import java.util.Objects;

public class OperationFactory {

    public Operation create(String input, Card card) {
        if (Objects.equals(input, "1")) {
            return new Withdraw(card);
        } else if (Objects.equals(input, "2")) {
            return new Balance(card);
        }
        return null;
    }
}
