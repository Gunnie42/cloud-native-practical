package com.ezgroceries.shoppinglist.shoppinglists.client;

import com.ezgroceries.shoppinglist.controller.CocktailId;
import com.ezgroceries.shoppinglist.controller.ShoppingListResource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ShoppingListClientTest {

    private static final String BIRTHDAY = "Stephanie's birthday";

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    void createShoppingList() {
        String url = "/shopping-lists";

        ShoppingListResource shoppingList = new ShoppingListResource(BIRTHDAY);
        URI newShoppingListLocation = restTemplate.postForLocation(url, shoppingList);

        assertThat(newShoppingListLocation).isNotNull();

        ShoppingListResource retrievedShoppingList = restTemplate.getForObject(newShoppingListLocation, ShoppingListResource.class);
        assertThat(retrievedShoppingList.getName()).isEqualTo(BIRTHDAY);

    }

    @Test
    void addCocktails() {
        String url = "/shopping-lists";
        ShoppingListResource shoppingList = new ShoppingListResource(BIRTHDAY);
        restTemplate.postForLocation(url, shoppingList);
        UUID shoppingListId = shoppingList.getShoppingListId();

        url += "/"+shoppingListId+"/cocktails";

        List<CocktailId> cocktailsToAdd = Arrays.asList(
                new CocktailId(UUID.fromString("23b3d85a-3928-41c0-a533-6538a71e17c4")),
                new CocktailId(UUID.fromString("d615ec78-fe93-467b-8d26-5d26d8eab073"))
        );

        List<CocktailId> res = (List<CocktailId>) restTemplate.postForObject(url, cocktailsToAdd, List.class);

        assertThat(res).isNotNull().hasSize(2);

    }


}
