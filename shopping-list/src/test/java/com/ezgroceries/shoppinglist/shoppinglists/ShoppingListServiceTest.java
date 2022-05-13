package com.ezgroceries.shoppinglist.shoppinglists;

import com.ezgroceries.shoppinglist.ShoppingList;
import com.ezgroceries.shoppinglist.ShoppingListService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ShoppingListServiceTest {

    private final ShoppingListService shoppingListService = new ShoppingListService();

    @Test
    void getShoppingListShouldWork(){
        ShoppingList shoppingList = new ShoppingList("Test");
        shoppingListService.addShoppingList(shoppingList);
        ShoppingList test = shoppingListService.getShoppingList("Test");
        assertThat(test).isNotNull();
        assertThat(test.getName()).isEqualTo("Test");

    }

}