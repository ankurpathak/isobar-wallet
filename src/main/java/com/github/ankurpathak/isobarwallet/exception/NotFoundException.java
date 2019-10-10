package com.github.ankurpathak.isobarwallet.exception;


public class NotFoundException extends RuntimeException {
    private final String entity;
    private final String id;
    private final String property;
    public NotFoundException(String id , String property, String entity) {
        this.id = id;
        this.property = property;
        this.entity = entity;
    }
    public String getEntity() {
        return entity;
    }


    public String getId() {
        return id;
    }


    public String getProperty() {
        return property;
    }
}
