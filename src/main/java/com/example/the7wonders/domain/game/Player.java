package com.example.the7wonders.domain.game;

import com.example.the7wonders.domain.cards.*;
import com.example.the7wonders.domain.wonder.WonderClass;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private Player voisinDroite;
    private Player voisinGauche;
    private List<CardType> cards;
    // A remplacer par WonderClass et donc suppr wonderDeck
    private WonderClass wonder;
    //private List<CardType> wonderDeck;

    public Player(String name, WonderClass wonder) throws Exception {
        this.name = name;
        this.voisinDroite = null;
        this.voisinGauche = null;
        this.cards = new ArrayList<>();
        cards.add(CardType.CardMaterialWood);
        cards.add(CardType.CardScienceLaw);
        this.wonder = wonder;
        //initializeWonderDeck();
    }

    public WonderClass getWonder() {
        return wonder;
    }

    public void setVoisinDroite(Player p){
        voisinDroite = p;
    }

    public void setVoisinGauche(Player p){
        voisinGauche = p;
    }

    public Player getVoisinGauche(){
        return voisinGauche;
    }

    public List<CardType> getWonderDeck(){
        return wonder.getDeck();
    }



}
