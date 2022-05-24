package com.ezgroceries.shoppinglist.persistence;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "shopping_list")
@Getter
@Setter
public class ShoppingListEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID key;
    private String name;
}
