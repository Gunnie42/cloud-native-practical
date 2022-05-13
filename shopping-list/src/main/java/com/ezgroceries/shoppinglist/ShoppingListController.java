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
    public ResponseEntity<Void> createShoppingList (@RequestBody ShoppingList shoppingList){
        shoppingListService.addShoppingList(shoppingList);
        return entityWithLocation(shoppingList.getName());
    }

    @GetMapping("shopping-lists/{name}")
    public ShoppingList getShoppingList(@PathVariable String name){
        return shoppingListService.getShoppingList(name);
    }

    private ResponseEntity<Void> entityWithLocation(Object resourceId) {

        // Determines URL of child resource based on the full URL of the given
        // request, appending the path info with the given resource Identifier
        URI location = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{childId}").buildAndExpand(resourceId)
                .toUri();

        // Return an HttpEntity object - it will be used to build the
        // HttpServletResponse
        return ResponseEntity.created(location).build();
    }

}
