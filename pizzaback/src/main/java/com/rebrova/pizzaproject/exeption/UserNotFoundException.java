package com.rebrova.pizzaproject.exeption;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(int id){
        super("Could not found the user with id "+id);
    }
    public UserNotFoundException(String string) {
        super(string);
    }
}
