package com.bitvavo.verifier.types;

public enum OrderType {
    BID,
    ASK;

    public static OrderType type(String side) {
        if (side.equals('B')) {
            return BID;
        } else if (side.equals('S')) {
            return ASK;
        } else {
            throw new IllegalArgumentException("Invalid order side: " + side);
        }
    }

}