package com.example.the7wonders;

import com.example.the7wonders.domain.cards.CardDecks;
import com.example.the7wonders.domain.cards.CardType;
import com.example.the7wonders.domain.wonder.Wonder;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private Player voisinDroite;
    private Player voisinGauche;
    private List<CardType> cards;
    private Wonder wonder;
    private List<CardType> wonderDeck;

    public Player(String name, Wonder wonder) throws Exception {
        this.name = name;
        this.voisinDroite = null;
        this.voisinGauche = null;
        this.cards = new ArrayList<>();
        cards.add(CardType.CardMaterialWood);
        cards.add(CardType.CardScienceLaw);
        this.wonder = wonder;
        initializeWonderDeck();
    }

    public Wonder getWonder() {
        return wonder;
    }

    public void setVoisinDroite(Player p){
        voisinDroite = p;
    }

    public void setVoisinGauche(Player p){
        voisinGauche = p;
    }

    public List<CardType> getWonderDeck(){
        return  wonderDeck;
    }

    // /!\ Normalement, le dexk de merveille devrait être associé à la merveille mais code du prof ne fonctionne pas comme ça
    private void initializeWonderDeck() throws Exception {
        wonderDeck = new ArrayList<>();
        switch (wonder.frenchName){
            case "Alexandrie":
                cardsListToCards(CardDecks.deckCardQuantities_Alexandrie);
                break;
            case "Halicarnasse":
                cardsListToCards(CardDecks.deckCardQuantities_Halicarnasse);
                break;
            case "Ephese":
                cardsListToCards(CardDecks.deckCardQuantities_Ephese);
                break;
            case "Olympie":
                cardsListToCards(CardDecks.deckCardQuantities_Olympie);
                break;
            case "Babylone":
                cardsListToCards(CardDecks.deckCardQuantities_Babylon);
                break;
            case "Rhodes":
                cardsListToCards(CardDecks.deckCardQuantities_Rhodes);
                break;
            case "Gizeh":
                cardsListToCards(CardDecks.deckCardQuantities_Gizeh);
                break;
            default:
                throw new Exception("Merveille sans deck");
        }
    }

    private void cardsListToCards(List<CardDecks.CardTypeQuantity> deck){
        for(CardDecks.CardTypeQuantity c : deck){
            for(int i = 0; i < c.quantity; ++i){
                wonderDeck.add(c.cardType);
            }
        }
    }
}
