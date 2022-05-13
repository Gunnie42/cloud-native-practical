package com.ezgroceries.shoppinglist.cocktails;

import com.ezgroceries.shoppinglist.Cocktail;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CocktailControllerBootTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void shouldGetDummyCocktails() {
        String url = "/cocktails?search=Russian";

        ResponseEntity<Cocktail[]> response = restTemplate.getForEntity(url, Cocktail[].class);

        Cocktail[] result = response.getBody();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result)
                .isNotNull()
                .hasSize(2);
        assertThat(result[0]).isNotNull();
        assertThat(result[0].getName()).isEqualTo("Margerita");

    }


}
