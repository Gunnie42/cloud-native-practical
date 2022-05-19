package com.ezgroceries.shoppinglist.controller;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Setter
@Getter
public class ShoppingListOut {

    private final UUID shoppingListId;
    private String name;
    private final Set<String> ingredients = new HashSet<>();

    @JsonCreator
    public ShoppingListOut(String name){
        shoppingListId = UUID.randomUUID();
        this.name = name;
    }

    public void addIngredients(List<String> ingredients) {
        this.ingredients.addAll(ingredients);
    }
}
