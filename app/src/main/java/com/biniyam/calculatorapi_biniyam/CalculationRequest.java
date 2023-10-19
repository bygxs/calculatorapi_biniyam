package com.biniyam.calculatorapi_biniyam;

public class CalculationRequest {
    private double firstNumber;
    private double secondNumber;
    private String operation;

    // Constructor
    public CalculationRequest(double firstNumber, double secondNumber, String operation) {
        this.firstNumber = firstNumber;
        this.secondNumber = secondNumber;
        this.operation = operation;
    }

    // Getters and setters if needed
    // ...
}
