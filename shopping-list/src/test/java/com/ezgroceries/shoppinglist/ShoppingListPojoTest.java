package com.ezgroceries.shoppinglist;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ShoppingListPojoTest {

    @Test
    void shouldBeAbleToAddCocktails(){
        ShoppingList shoppingList = new ShoppingList("Test");

        List<CocktailId> cocktailIds = Arrays.asList(
                new CocktailId(UUID.randomUUID()),
                new CocktailId(UUID.randomUUID())
        );

        assertThat(shoppingList.getCocktailIds())
                .isNotNull()
                .isEmpty();

        shoppingList.addCocktails(cocktailIds);

        assertThat(shoppingList.getCocktailIds()).hasSize(2);


    }

}