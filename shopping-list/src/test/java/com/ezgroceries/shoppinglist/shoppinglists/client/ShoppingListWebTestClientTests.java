package com.ezgroceries.shoppinglist.shoppinglists.client;

import com.ezgroceries.shoppinglist.controller.ShoppingListOut;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.net.URISyntaxException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
public class ShoppingListWebTestClientTests {

    @Autowired
    WebTestClient webTestClient;

    @Test
    void createShoppingList_WebTestClient() {
        String url = "shopping-lists";

        ShoppingListOut shoppingList = new ShoppingListOut("Test");

        webTestClient.post()
                .uri(url)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(shoppingList), ShoppingListOut.class)
                .exchange()
                .expectStatus().isCreated()
                .expectHeader().value("Location", location -> {
                    try {
                        URI newShoppingLocation = new URI(location);
                        webTestClient.get()
                                .uri(newShoppingLocation)
                                .exchange()
                                .expectStatus().isOk()
                                .expectBody(ShoppingListOut.class)
                                .consumeWith(response -> {
                                    ShoppingListOut retrievedShoppingList = response.getResponseBody();
                                    assertThat(retrievedShoppingList).isNotNull();
                                    assertThat(retrievedShoppingList.getName()).isEqualTo("Test");
                                });
                    } catch (URISyntaxException e) {
                        e.printStackTrace();
                    }
                })
                .expectBody(ShoppingListOut.class)
                .consumeWith(response -> {
                    ShoppingListOut retreivedShoppingListOut = response.getResponseBody();
                    assertThat(retreivedShoppingListOut).isNotNull();
                    assertThat(retreivedShoppingListOut.getName()).isEqualTo("Test");
                });


    }

}
