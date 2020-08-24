package com.example.practice.exception;

public class EmployeeNotFound extends Exception{

    public EmployeeNotFound(String message){
        super(message);
    }
}
