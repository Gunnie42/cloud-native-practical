package com.ezgroceries.shoppinglist.cocktails;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/cocktails", produces = "application/json")
@RequiredArgsConstructor
public class CocktailController {

    private final CocktailDBClient cocktailDBClient;

    @GetMapping
    public ResponseEntity<List<Cocktail>> get(@RequestParam String search) {
        List<Cocktail> res = cocktailDBClient
                .searchCocktails(search)
                .getDrinks()
                .stream()
                .map(Cocktail::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok(res);
    }

}