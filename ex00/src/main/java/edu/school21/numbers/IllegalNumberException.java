package edu.school21.numbers;

public class IllegalNumberException extends RuntimeException {
    IllegalNumberException() {
        super("number is lower than 1");
    }
}
