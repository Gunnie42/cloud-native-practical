package com.ezgroceries.shoppinglist.shoppinglists;

import com.ezgroceries.shoppinglist.cocktails.CocktailId;
import com.ezgroceries.shoppinglist.cocktails.CocktailService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ShoppingListService {

    private final CocktailService cocktailService;
    private final List<ShoppingList> shoppingLists = new ArrayList<>();

    ShoppingListService(CocktailService cocktailService){
        this.cocktailService = cocktailService;
    }

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

    public List<ShoppingList> getShoppingLists() {
        return shoppingLists;
    }

    public void addCocktails(ShoppingList shoppingList, List<CocktailId> cocktailIds){
        for (CocktailId cocktailId : cocktailIds){
            List<String> ingredients = cocktailService.getIngredients(cocktailId.getCocktailId());
            shoppingList.addIngredients(ingredients);
        }
    }


}