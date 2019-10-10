package com.github.ankurpathak.isobarwallet.exception;

public class InvalidException extends RuntimeException {
    private final String property;
    private final String value;


    public InvalidException(String property, String value) {
        this.property = property;
        this.value = value;
    }



    public String getProperty() {
        return property;
    }

    public String getValue() {
        return value;
    }
}
