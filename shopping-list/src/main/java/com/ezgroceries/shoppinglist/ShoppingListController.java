package com.ezgroceries.shoppinglist;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class ShoppingListController {

    private final ShoppingListService shoppingListService;

    @PostMapping("shopping-lists")
    @ResponseBody
    public ResponseEntity<ShoppingList> createShoppingList (@RequestBody ShoppingList shoppingList){
        shoppingListService.addShoppingList(shoppingList);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{childId}")
                .buildAndExpand(shoppingList.getShoppingListId())
                .toUri();

        return ResponseEntity
                .created(location)
                .body(shoppingList);
    }

    @GetMapping("shopping-lists/{shoppingListId}")
    public ShoppingList getShoppingList(@PathVariable UUID shoppingListId){
        return shoppingListService.getShoppingList(shoppingListId);
    }

    @GetMapping("shopping-lists")
    @ResponseStatus(HttpStatus.OK)
    public List<ShoppingList> getShoppingLists(){
        return shoppingListService.getShoppingLists();
    }

    @PostMapping(value = "/shopping-lists/{shoppingListId}/cocktails")
    public ResponseEntity<List<CocktailId>> addCocktails(@PathVariable UUID shoppingListId, @RequestBody List<CocktailId> cocktailIds) {

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("")
                .buildAndExpand()
                .toUri();

        ShoppingList shoppingList = shoppingListService.getShoppingList(shoppingListId);
        shoppingListService.addCocktails(shoppingList,cocktailIds);

        return ResponseEntity
                .created(location)
                .body(cocktailIds);
    }

}
