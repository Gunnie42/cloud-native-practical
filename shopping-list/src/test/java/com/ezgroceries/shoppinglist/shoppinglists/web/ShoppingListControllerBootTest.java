package com.ezgroceries.shoppinglist.shoppinglists.web;

import com.ezgroceries.shoppinglist.ShoppingList;
import com.ezgroceries.shoppinglist.ShoppingListController;
import com.ezgroceries.shoppinglist.ShoppingListService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyString;
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
        given(shoppingListService.getShoppingList(anyString()))
                .willReturn(new ShoppingList("Test"));

        mockMvc.perform(get("/shopping-lists/does_not_matter"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("name").value("Test"));

        verify(shoppingListService).getShoppingList(anyString());
    }

    @Test
    void createShoppingList() throws Exception {
        ShoppingList testShoppingList = new ShoppingList("Test");

        given(shoppingListService.addShoppingList(any(ShoppingList.class)))
                .willReturn(testShoppingList);

        mockMvc.perform(post("/shopping-lists")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(testShoppingList)))
                .andExpect(status().isCreated());

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
