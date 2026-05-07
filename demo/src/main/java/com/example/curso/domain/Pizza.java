package com.example.curso.domain;
import java.util.UUID;
import java.util.Set;
import java.math.BigDecimal;

public class Pizza {

    private final UUID id;
    private final String name;
    private final String description;
    private final Set <Ingredients> ingredients;


public Pizza( String name, String description, Set <Ingredients> ingredients) {
    //VALIDACIONES Y ASIGNACIONES
    this.id = UUID.randomUUID();
    this.description = description;

    if (name == null  || name.isEmpty()) {
        throw new IllegalArgumentException("Pizza name needs almost 1 character");} else {
            this.name = name;
        }
    if (ingredients == null || ingredients.isEmpty()) {
        throw new IllegalArgumentException("name needs almost 1 character");} else {
            this.ingredients = ingredients;
        }
    }

public BigDecimal calcularPrecio () {
        BigDecimal total = BigDecimal.ZERO;
        for (Ingredients i : ingredients) {
        total = total.add(i.getPrice());
        }
        return total.multiply(new BigDecimal("1.20"));
    }
    

    //getter
public UUID getId() {
    return id;
}

public String getName() {
    return name;
}

public String getDescription() {
    return description;
}

public Set<Ingredients> getIngredients() {
    return ingredients;
}


    }

