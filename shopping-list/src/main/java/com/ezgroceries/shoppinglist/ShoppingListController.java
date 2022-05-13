package com.ezgroceries.shoppinglist;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

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
                .buildAndExpand(shoppingList.getName())
                .toUri();

        return ResponseEntity
                .created(location)
                .body(shoppingList);
    }

    @GetMapping("shopping-lists/{name}")
    public ShoppingList getShoppingList(@PathVariable String name){
        return shoppingListService.getShoppingList(name);
    }

}
