package com.example.curso.domain;
import java.time.LocalDateTime;

public class Comment {

    private final Pizza pizza;
    private final User user;
    private final String description;
    private final LocalDateTime date;



public Comment(Pizza pizza, User user, String description) {
    //VALIDACIONES Y ASIGNACIONES
    this.date = LocalDateTime.now(); // Current date & time
    if (pizza == null) {
        throw new IllegalArgumentException("Pizza cannot be empty");}
    else {
            this.pizza = pizza;}
    if (user == null) {
        throw new IllegalArgumentException("User cannot be empty");}
    else {
            this.user = user;}
    if (description == null || description.isEmpty()) {
        throw new IllegalArgumentException("mail needs almost 1 character");}
    else {
            this.description = description;}
}


public User getUser() {
    return user;
}

public Pizza getPizza() {
    return pizza;
}

public String getDescription() {
    return description;
}

public LocalDateTime getDate() {
    return date;
}



    }