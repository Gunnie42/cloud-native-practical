package com.ezgroceries.shoppinglist;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Setter
@Getter
public class ShoppingList {

    private final UUID shoppingListId;
    private String name;
    private final Set<CocktailId> cocktailIds = new HashSet<>();

    @JsonCreator
    public ShoppingList(String name){
        shoppingListId = UUID.randomUUID();
        this.name = name;
    }

    public void addCocktails(List<CocktailId> cocktailIds) {
        this.cocktailIds.addAll(cocktailIds);
    }
}
