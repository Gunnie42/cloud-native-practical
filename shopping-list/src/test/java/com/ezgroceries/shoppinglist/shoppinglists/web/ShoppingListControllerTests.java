package com.ezgroceries.shoppinglist.shoppinglists.web;

import com.ezgroceries.shoppinglist.controller.ShoppingListResource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;

import java.net.URI;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ShoppingListControllerTests {

    public static final String BIRTHDAY = "Stephanie's birthday";
    @Autowired
    TestRestTemplate restTemplate;

    @Test
    void createShoppingList() {
        String url = "/shopping-lists";

        ShoppingListResource shoppingList = new ShoppingListResource(BIRTHDAY);

        URI newShoppingListLocation = restTemplate.postForLocation(url, shoppingList);

        ShoppingListResource retrievedShoppingList = restTemplate.getForObject(newShoppingListLocation, ShoppingListResource.class);

        assertThat(retrievedShoppingList).isNotNull();

        assertThat(retrievedShoppingList.getName()).isEqualTo(BIRTHDAY);

    }
}
