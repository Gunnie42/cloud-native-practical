package com.ezgroceries.shoppinglist.shoppinglists;

import com.ezgroceries.shoppinglist.controller.ShoppingListOut;
import com.ezgroceries.shoppinglist.service.ShoppingListService;
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
        ShoppingListOut shoppingList = new ShoppingListOut("Test");
        UUID shoppingListId = shoppingList.getShoppingListId();
        shoppingListService.addShoppingList(shoppingList);
        ShoppingListOut test = shoppingListService.getShoppingList(shoppingListId);
        assertThat(test).isNotNull();
        assertThat(test.getName()).isEqualTo("Test");

    }

}