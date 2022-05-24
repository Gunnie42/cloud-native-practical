package com.ezgroceries.shoppinglist.shoppinglists.web;

import com.ezgroceries.shoppinglist.controller.CocktailId;
import com.ezgroceries.shoppinglist.controller.ShoppingListResource;
import com.ezgroceries.shoppinglist.controller.ShoppingListController;
import com.ezgroceries.shoppinglist.service.ShoppingListService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(ShoppingListController.class)
public class ShoppingListControllerBootTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ShoppingListService shoppingListService;

    @Test
    public void getShoppingList() throws Exception {
        given(shoppingListService.getShoppingList(any(UUID.class)))
                .willReturn(new ShoppingListResource("Test"));

        mockMvc.perform(get("/shopping-lists/" + UUID.randomUUID()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("name").value("Test"));

        verify(shoppingListService).getShoppingList(any(UUID.class));
    }

    @Test
    void createShoppingList() throws Exception {
        ShoppingListResource testShoppingList = new ShoppingListResource("Test");

        given(shoppingListService.addShoppingList(any(ShoppingListResource.class)))
                .willReturn(testShoppingList);

        mockMvc.perform(post("/shopping-lists")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(testShoppingList)))
                .andExpect(status().isCreated());

        verify(shoppingListService).addShoppingList((any(ShoppingListResource.class)));

    }

    @Test
    void addCocktails() throws Exception {
        ShoppingListResource testShoppingList = new ShoppingListResource("Test");

        given(shoppingListService.getShoppingList(any(UUID.class)))
                .willReturn(testShoppingList);

        assertThat(testShoppingList.getShoppingListId()).isNotNull();

        List<CocktailId> cocktailIds = Arrays.asList(new CocktailId(UUID.fromString("23b3d85a-3928-41c0-a533-6538a71e17c4")),
                new CocktailId(UUID.fromString("d615ec78-fe93-467b-8d26-5d26d8eab073")));

        mockMvc.perform(post("/shopping-lists/{shoppingListId}/cocktails", testShoppingList.getShoppingListId().toString())
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(cocktailIds)))
                .andExpect(status().isCreated())
                .andExpect(content().string(asJsonString(cocktailIds)));

        verify(shoppingListService).getShoppingList(any(UUID.class));

    }

    @Test
    void getAllShoppingLists() throws Exception {

        List<ShoppingListResource> allLists = Arrays.asList(
                new ShoppingListResource("Stephanie's Birthday"),
                new ShoppingListResource("My Birthday")
        );

        given(shoppingListService.getShoppingLists()).willReturn(allLists);

        mockMvc.perform(get("/shopping-lists"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]['name']").value("Stephanie's Birthday"))
                .andExpect(jsonPath("$[1]['name']").value("My Birthday"));

        verify(shoppingListService).getShoppingLists();

    }

    protected static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
