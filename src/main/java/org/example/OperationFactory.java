package org.example;

import java.util.Objects;

public class OperationFactory {

    public Operation create(String input) {
        if (Objects.equals(input, "1")) {
            return new Withdrew();
        } else if (Objects.equals(input, "2")) {
            return new Balance();
        }
        return null;
    }
}
