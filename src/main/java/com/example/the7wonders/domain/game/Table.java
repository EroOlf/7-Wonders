package com.example.the7wonders.domain.game;

import com.example.the7wonders.domain.cards.*;
import com.example.the7wonders.domain.tokens.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Table {
    private static Table table = new Table();
    // Deck central
    private List<CardType> centralDeck;
    private ProgressTokens tokens = new ProgressTokens();
    private final int nbConflictTockens = 4;

    private int cornCount = 0;


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
        shuffle();
    }

    public List<CardType> getCentralDeck(){
        return centralDeck;
    }

    public ProgressTokens getTokens() {
        return tokens;
    }

    public void setCornCount(int corn){
        if(nbConflictTockens > this.cornCount+corn){
            this.cornCount = corn;
        }else{
            this.cornCount += corn;
        }
    }

    private void shuffle(){
        Collections.shuffle(tokens.getProgressTokens());
    }
}
