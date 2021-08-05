package org.testmockito.rest;

public class UnknownResourceException extends RuntimeException {

    public UnknownResourceException(String msg) {
        super(msg);
    }
}
