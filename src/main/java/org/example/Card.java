package org.example;

public class Card {

    private String cardNumber;
    private String pinCode;
    private String accountNumber;
    private boolean locked;

    public Card(String cardNumber, String accountNumber, String pinCode, boolean locked) {
        this.cardNumber = cardNumber;
        this.pinCode = pinCode;
        this.locked = locked;
        this.accountNumber = accountNumber;
    }

    public boolean validePincode(String code) {
        if (code.equals(pinCode)) {
            locked = false;
            return true;
        }
        return false;
    }

    public String getAccountNumber() {
        return accountNumber;
    }
}
