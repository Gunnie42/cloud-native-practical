package com.ezgroceries.shoppinglist.cocktails;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@RequiredArgsConstructor
public class Cocktail {

    private final UUID cocktailId;
    private final String name;
    private final String glass;
    private final String instructions;
    private final String image;
    private final List<String> ingredients;

    Cocktail(CocktailDBResponse.DrinkResource drinkResource){
        cocktailId = UUID.nameUUIDFromBytes(drinkResource.getIdDrink().getBytes(StandardCharsets.UTF_8));
        name = drinkResource.getStrDrink();
        glass = drinkResource.getStrGlass();
        instructions = drinkResource.getStrInstructions();
        image = drinkResource.getStrImageSource();
        ingredients = drinkResource.getIngredients();
    }

}
