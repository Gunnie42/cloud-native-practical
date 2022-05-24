package com.ezgroceries.shoppinglist.controller;

import com.ezgroceries.shoppinglist.persistence.ShoppingListEntity;
import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Setter
@Getter
public class ShoppingListResource {

    private final UUID shoppingListId;
    private String name;
    private final Set<String> ingredients = new HashSet<>();

    @JsonCreator
    public ShoppingListResource(String name){
        shoppingListId = UUID.randomUUID();
        this.name = name;
    }

    public ShoppingListResource(ShoppingListEntity shoppingListEntity){
        this.shoppingListId = shoppingListEntity.getId();
        this.name = shoppingListEntity.getName();
    }

    public void addIngredients(List<String> ingredients) {
        this.ingredients.addAll(ingredients);
    }
}
