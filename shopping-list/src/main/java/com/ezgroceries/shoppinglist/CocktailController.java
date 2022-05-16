package com.ezgroceries.shoppinglist;

import com.ezgroceries.shoppinglist.Cocktail;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/cocktails", produces = "application/json")
@RequiredArgsConstructor
public class CocktailController {

    private final CocktailService cocktailService;

    @GetMapping
    public ResponseEntity<List<Cocktail>> get(@RequestParam String search) {
        List<Cocktail> res = cocktailService.searchCocktail(search);
        return ResponseEntity.ok(res);
    }

}