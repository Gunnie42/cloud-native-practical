package com.ezgroceries.shoppinglist;

import com.ezgroceries.shoppinglist.ShoppingList;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShoppingListService {

    private final List<ShoppingList> shoppingLists = new ArrayList<>();

    public ShoppingList addShoppingList(ShoppingList shoppingList) {
        shoppingLists.add(shoppingList);
        return shoppingList;
    }

    public ShoppingList getShoppingList(String name ) {
        return shoppingLists.stream()
                .filter(shoppingList -> name.equals(shoppingList.getName()))
                .findFirst()
                .orElse(null);
    }

}
