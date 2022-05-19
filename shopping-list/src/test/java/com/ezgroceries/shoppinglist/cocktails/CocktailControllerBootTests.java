package com.ezgroceries.shoppinglist.cocktails;

import com.ezgroceries.shoppinglist.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;

import static org.mockito.BDDMockito.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.web.servlet.function.RequestPredicates.param;

@WebMvcTest(CocktailController.class)
public class CocktailControllerBootTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CocktailDBClient cocktailDBClient;

    @Test
    void contextLoads() {

    }

    @Test
    void shouldGetDummyCocktails() throws Exception {

      given(cocktailDBClient.searchCocktails(anyString()))
                .willReturn(new CocktailDBResponse(
                        Arrays.asList(
                        new CocktailDBResponse.DrinkResource(
                                "23b3d85a-3928-41c0-a533-6538a71e17c4",
                                "Margerita",
                                "Cocktail glass",
                                "Rub the rim of the glass with the lime slice to make the salt stick to it. Take care to moisten..",
                                "https://www.thecocktaildb.com/images/media/drink/wpxpvu1439905379.jpg",
                                Arrays.asList("Tequila", "Triple sec", "Lime juice", "Salt")),
                        new CocktailDBResponse.DrinkResource(
                                "d615ec78-fe93-467b-8d26-5d26d8eab073",
                                "Blue Margerita",
                                "Cocktail glass",
                                "Rub rim of cocktail glass with lime juice. Dip rim in coarse salt..",
                                "https://www.thecocktaildb.com/images/media/drink/qtvvyq1439905913.jpg",
                                Arrays.asList("Tequila", "Blue Curacao", "Lime juice", "Salt")))));

      mockMvc.perform(get("/cocktails").param("search","russian"))
              .andDo(print())
              .andExpect(status().isOk())
              .andExpect(content().contentType(MediaType.APPLICATION_JSON))

              .andExpect(jsonPath("$[0].name").value("Margerita"))
              .andExpect(jsonPath("$[0].glass").value("Cocktail glass"))
              .andExpect(jsonPath("$[0].instructions").value("Rub the rim of the glass with the lime slice to make the salt stick to it. Take care to moisten.."))
              .andExpect(jsonPath("$[0].image").value("https://www.thecocktaildb.com/images/media/drink/wpxpvu1439905379.jpg"))
              .andExpect(jsonPath("$[0].ingredients[0]").value("Tequila"))
              .andExpect(jsonPath("$[0].ingredients[1]").value("Triple sec"))
              .andExpect(jsonPath("$[0].ingredients[2]").value("Lime juice"))
              .andExpect(jsonPath("$[0].ingredients[3]").value("Salt"))

              .andExpect(jsonPath("$[1].name").value("Blue Margerita"))
              .andExpect(jsonPath("$[1].glass").value("Cocktail glass"))
              .andExpect(jsonPath("$[1].instructions").value("Rub rim of cocktail glass with lime juice. Dip rim in coarse salt.."))
              .andExpect(jsonPath("$[1].image").value("https://www.thecocktaildb.com/images/media/drink/qtvvyq1439905913.jpg"))
              .andExpect(jsonPath("$[1].ingredients[0]").value("Tequila"))
              .andExpect(jsonPath("$[1].ingredients[1]").value("Blue Curacao"))
              .andExpect(jsonPath("$[1].ingredients[2]").value("Lime juice"))
              .andExpect(jsonPath("$[1].ingredients[3]").value("Salt"))

      ;

    }


}
