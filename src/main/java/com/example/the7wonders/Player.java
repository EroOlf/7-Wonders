package com.example.the7wonders;

import com.example.the7wonders.domain.cards.*;
import com.example.the7wonders.domain.wonder.*;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.layout.*;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private Player voisinDroite;
    private Player voisinGauche;
    private List<CardType> cards;
    private Wonder wonder;
    private List<CardType> wonderDeck;

    private static FlowPane root = new FlowPane();



//essai ne fonctionne pas pour ajouter le fond sur la 3 e page
    //public static void launch(){
        //BackgroundFill bgFill = new BackgroundFill(Color.valueOf("#8a6227"), new CornerRadii(0), new Insets(0));
        //BackgroundFill bgFill2 = new BackgroundFill(Color.valueOf("#DECBAA"), new CornerRadii(10), new Insets(10));
        //Background bg = new Background(bgFill, bgFill2);
        //root.setBackground(bg);

        //Scene scene = new Scene(root, 800, 800);
        ////scene.getStylesheets().add(HelloApplication.class.getResource("fight.css").toExternalForm());
        //HelloApplication.stage.setScene(scene);
        //HelloApplication.stage.show();
   // }



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

    // /!\ Normalement, le deck de merveille devrait être associé à la merveille mais le code du prof ne fonctionne pas comme ça
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
