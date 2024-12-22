package com.bitvavo.verifier.types;

public enum ExceptionTypes {
    WRONG_PRICE_FORMAT ("The price can be up to 999999 or can not be negative! Your Price input ->>"),
    WRONG_QUANTITY_FORMAT("The quantity can be up to 999999999 or can not be negative! Your Quantity input ->>"),
    WRONG_ARG_FORMAT("Invalid input format"),
    WRONG_SIDE_FORMAT("Invalid side value "),
    ;

    ExceptionTypes(String exceptionMessage) {

    }
}
