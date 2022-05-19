package com.ezgroceries.shoppinglist.service;

import com.ezgroceries.shoppinglist.controller.CocktailId;
import com.ezgroceries.shoppinglist.controller.ShoppingListOut;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ShoppingListService {

    private final CocktailService cocktailService;
    private final List<ShoppingListOut> shoppingLists = new ArrayList<>();

    ShoppingListService(CocktailService cocktailService){
        this.cocktailService = cocktailService;
    }

    public ShoppingListOut addShoppingList(ShoppingListOut shoppingList) {
        shoppingLists.add(shoppingList);
        return shoppingList;
    }

    public ShoppingListOut getShoppingList(UUID shoppingListId ) {
        return shoppingLists.stream()
                .filter(shoppingList -> shoppingListId.equals(shoppingList.getShoppingListId()))
                .findFirst()
                .orElse(null);
    }

    public List<ShoppingListOut> getShoppingLists() {
        return shoppingLists;
    }

    public void addCocktails(ShoppingListOut shoppingList, List<CocktailId> cocktailIds){
        for (CocktailId cocktailId : cocktailIds){
            List<String> ingredients = cocktailService.getIngredients(cocktailId.getCocktailId());
            shoppingList.addIngredients(ingredients);
        }
    }


}
