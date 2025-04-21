package org.example.ex4;

public class DebitImpossibleException extends Exception {
    public DebitImpossibleException() {
        super("Débit impossible");
    }
}