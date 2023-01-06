package com.example.the7wonders.domain;

import java.util.ArrayList;
import java.util.List;

public class Table {
    private static Table table = new Table();
    // Deck central
    private List<CardType> centralDeck;
    // Tokens sciences
    private ProgressTokens tokens;
    // Conflict Tokens
    private final int nbConflictTockens = 4;
    private ConflictTokens[] conflictTokens = new ConflictTokens[nbConflictTockens];

    //Chaaaaaaaaat

    public static Table getTable(){
        return table;
    }

    private Table(){
        List<CardDecks.CardTypeQuantity> temp = new ArrayList<>();
        temp = CardDecks.deckCardQuantities_Extra;
        centralDeck = new ArrayList<>();
        for(CardDecks.CardTypeQuantity c : temp){
            for(int i = 0; i < c.quantity; ++i){
                centralDeck.add(c.cardType);
            }
        }
    }

    public List<CardType> getCentralDeck(){
        return centralDeck;
    }
}
