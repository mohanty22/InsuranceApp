package com.MyProject.InsuranceApp.exception;

public class AppAlreadyExistsException  extends RuntimeException {
    public AppAlreadyExistsException(String message) {
        super(message);
    }
}
