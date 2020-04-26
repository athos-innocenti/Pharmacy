package com.pharmacy;

public class NoEmployeeException extends Exception {
    public NoEmployeeException() {
        super("Non possono non esserci dipedenti!");
    }
}
