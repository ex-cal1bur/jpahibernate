package com.example.curso.domain;
import java.math.BigDecimal;

public class Ingredients {

    private final BigDecimal price;
    private final String description;


public Ingredients(BigDecimal price, String description) {
    //VALIDACIONES Y ASIGNACIONES
    if (price.compareTo(BigDecimal.ZERO) < 0){
        throw new IllegalArgumentException("The number needs to be positive or up to 0");} else {
             this.price = price;
        }
    if (description == null  || description.isEmpty()) {
        throw new IllegalArgumentException("Description needs almost 1 character");} else {
            this.description = description;
        }
    }

    //getter
public BigDecimal getPrice() {
    return price;
}

public String getDescription() {
    return description;
}
    }

