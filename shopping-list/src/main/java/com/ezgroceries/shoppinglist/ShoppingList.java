package com.ezgroceries.shoppinglist;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class ShoppingList {

    private final UUID shoppingListId;
    private String name;

    @JsonCreator
    public ShoppingList(String name){
        shoppingListId = UUID.randomUUID();
        this.name = name;
    }

}
