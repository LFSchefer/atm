package org.example;

public class Card {

    private String pinCode = "1234";
    private boolean locked = true;

    public Card() {
    }

    public boolean validePincode(String code) {
        if (code.equals(pinCode)) {
            locked = false;
            return true;
        }
        return false;
    }

}
