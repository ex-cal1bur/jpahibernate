package com.example.curso.domain;
import java.time.LocalDateTime;

public class Comment {

    private final String usermail;
    private final String description;
    private final String reference;


public Comment(String reference, String usermail, String description) {
    //VALIDACIONES Y ASIGNACIONES
    LocalDateTime now = LocalDateTime.now(); // Current date & time

    if (usermail == null || usermail.isEmpty()) {
        throw new IllegalArgumentException("mail needs almost 1 character");}
    else if (!usermail.contains("@")){
        throw new IllegalArgumentException("mail needs the '@' character");}
    else {
            this.usermail = usermail;}
    if (description == null || description.isEmpty()) {
        throw new IllegalArgumentException("mail needs almost 1 character");}
    else {
            this.description = description;}
    if (reference == null || reference.isEmpty()) {
        throw new IllegalArgumentException("mail needs almost 1 character");}
    else {
            this.reference = reference;}
        } 

public String getUsermail() {
    return usermail;
}

public String getDescription() {
    return description;
}

public String getReference() {
    return reference;
}


    }