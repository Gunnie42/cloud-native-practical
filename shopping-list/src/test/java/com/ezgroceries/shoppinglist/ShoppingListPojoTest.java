package com.ezgroceries.shoppinglist;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ShoppingListPojoTest {

    @Autowired
    ShoppingListService shoppingListService;

    @Test
    void shouldBeAbleToAddCocktails(){
        ShoppingList shoppingList = new ShoppingList("Test");

        List<CocktailId> cocktailIds = Arrays.asList(
                new CocktailId(UUID.fromString("23b3d85a-3928-41c0-a533-6538a71e17c4")),
                new CocktailId(UUID.fromString("d615ec78-fe93-467b-8d26-5d26d8eab073"))
        );

        assertThat(shoppingList.getIngredients())
                .isNotNull()
                .isEmpty();

        shoppingListService.addCocktails(shoppingList,cocktailIds);

        assertThat(shoppingList.getIngredients())
                .hasSize(5)
                .containsAll(Arrays.asList("Blue Curacao","Tequila","Triple sec","Lime juice", "Salt"));

    }

}