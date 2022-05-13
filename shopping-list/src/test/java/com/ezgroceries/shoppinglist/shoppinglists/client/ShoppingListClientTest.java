package com.ezgroceries.shoppinglist.shoppinglists.client;

import com.ezgroceries.shoppinglist.ShoppingList;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import java.net.URI;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ShoppingListClientTest {

    public static final String BIRTHDAY = "Stephanie's birthday";
    @Autowired
    TestRestTemplate restTemplate;

    @Test
    public void createShoppingList() {
        String url = "/shopping-lists";

        ShoppingList shoppingList = new ShoppingList(BIRTHDAY);
        URI newShoppingListLocation = restTemplate.postForLocation(url, shoppingList);

        assertThat(newShoppingListLocation).isNotNull();

        System.out.println(newShoppingListLocation);

        ShoppingList retrievedShoppingList = restTemplate.getForObject(newShoppingListLocation, ShoppingList.class);
        assertThat(retrievedShoppingList.getName()).isEqualTo(BIRTHDAY);

    }

}
