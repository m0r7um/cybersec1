package org.example.demo.exceptions;

public class IncorrectPasswordException extends RuntimeException {
    public IncorrectPasswordException() {
        super("Пароли не совпадают");
    }
}
