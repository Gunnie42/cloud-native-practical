package com.ezgroceries.shoppinglist;

import com.ezgroceries.shoppinglist.ShoppingList;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ShoppingListService {

    private final List<ShoppingList> shoppingLists = new ArrayList<>();

    public ShoppingList addShoppingList(ShoppingList shoppingList) {
        shoppingLists.add(shoppingList);
        return shoppingList;
    }

    public ShoppingList getShoppingList(UUID shoppingListId ) {
        return shoppingLists.stream()
                .filter(shoppingList -> shoppingListId.equals(shoppingList.getShoppingListId()))
                .findFirst()
                .orElse(null);
    }

}
