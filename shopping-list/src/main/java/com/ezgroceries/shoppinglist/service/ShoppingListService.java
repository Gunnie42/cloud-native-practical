package com.ezgroceries.shoppinglist.service;

import com.ezgroceries.shoppinglist.controller.CocktailId;
import com.ezgroceries.shoppinglist.controller.ShoppingListResource;
import com.ezgroceries.shoppinglist.persistence.ShoppingListEntity;
import com.ezgroceries.shoppinglist.persistence.ShoppingListRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ShoppingListService {

    private final CocktailService cocktailService;
    private final ShoppingListRepository shoppingListRepository;

    ShoppingListService(CocktailService cocktailService,
                        ShoppingListRepository shoppingListRepository){
        this.cocktailService = cocktailService;
        this.shoppingListRepository = shoppingListRepository;
    }

    public ShoppingListResource addShoppingList(ShoppingListResource shoppingListResource) {
        ShoppingListEntity shoppingListEntity = new ShoppingListEntity();
        shoppingListEntity.setId(shoppingListResource.getShoppingListId());
        shoppingListEntity.setName(shoppingListResource.getName());
        shoppingListRepository.save(shoppingListEntity);
        return shoppingListResource;
    }

    public ShoppingListResource getShoppingList(UUID shoppingListId ) {
        Optional<ShoppingListEntity> shoppingListEntity = shoppingListRepository.findById(shoppingListId);
        if (shoppingListEntity.isEmpty()) return null;
        return new ShoppingListResource(shoppingListEntity.get());
    }

    public List<ShoppingListResource> getShoppingLists() {
        List<ShoppingListResource> shoppingListResources = new ArrayList<>();
        shoppingListRepository.findAll()
                .forEach(x -> shoppingListResources.add(new ShoppingListResource(x)));
        return shoppingListResources;
    }

    public void addCocktails(ShoppingListResource shoppingList, List<CocktailId> cocktailIds){
        for (CocktailId cocktailId : cocktailIds){
            List<String> ingredients = cocktailService.getIngredients(cocktailId.getCocktailId());
            shoppingList.addIngredients(ingredients);
        }
    }

}
