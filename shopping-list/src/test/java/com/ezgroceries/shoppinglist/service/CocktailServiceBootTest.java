package com.ezgroceries.shoppinglist.service;

import com.ezgroceries.shoppinglist.controller.CocktailResource;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CocktailServiceBootTest {

    @Autowired
    private CocktailService cocktailService;

    @Test
    void contextLoads() {}

    @Test
    void shouldBeAbleToGetACocktail(){
        CocktailResource cocktail = cocktailService.getCocktail(UUID.fromString("23b3d85a-3928-41c0-a533-6538a71e17c4"));
        assertThat(cocktail).isNotNull();
        assertThat(cocktail.getName()).isEqualTo("Margerita");
    }

}