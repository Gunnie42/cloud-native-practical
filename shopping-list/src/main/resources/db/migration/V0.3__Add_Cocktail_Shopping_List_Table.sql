create table COCKTAIL_SHOPPING_LIST (
    COCKTAIL_ID UUID references COCKTAIL(ID),
    SHOPPING_LIST_ID UUID references SHOPPING_LIST(ID),
    primary key (COCKTAIL_ID,SHOPPING_LIST_ID)
)