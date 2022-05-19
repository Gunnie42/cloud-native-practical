package com.ezgroceries.shoppinglist.shoppinglists;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ShoppingListServiceTest {

    @Autowired
    private ShoppingListService shoppingListService;

    @Test
    void getShoppingListShouldWork(){
        ShoppingList shoppingList = new ShoppingList("Test");
        UUID shoppingListId = shoppingList.getShoppingListId();
        shoppingListService.addShoppingList(shoppingList);
        ShoppingList test = shoppingListService.getShoppingList(shoppingListId);
        assertThat(test).isNotNull();
        assertThat(test.getName()).isEqualTo("Test");

    }

}