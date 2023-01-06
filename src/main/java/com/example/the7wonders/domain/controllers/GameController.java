package com.example.the7wonders.domain.controllers;

import com.example.the7wonders.HelloApplication;
import com.example.the7wonders.domain.game.Player;
import com.example.the7wonders.domain.cards.CardType;
import com.example.the7wonders.domain.game.Game;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;

import java.util.ArrayList;
import java.util.List;

public class GameController {
    // Pour l'instant juste afficher les merveilles
    @FXML
    private static FlowPane root = new FlowPane();
    @FXML
    private static Label infoLabel = new Label();

    private static List<CardType> leftDeck;
    private static List<CardType> playerDeck;
    private static List<CardType> centralDeck = new ArrayList<>();
    private static int cptCentralDeck = 0;

    private static int currentPlayer = 0;
    private static final int nbPlayers = Game.getContext().getNbPlayers();
    private static List<Player> players = Game.getContext().getPlayers();

    public static void initializeGame(){
        Scene scene = new Scene(root, 800, 800);
        HelloApplication.stage.setTitle("Choose a Wonder and a name");
        HelloApplication.stage.setScene(scene);
        HelloApplication.stage.show();
        initializeData();
    }

    private static void initializeData(){
        Game.getContext().shuffleCentralDeck();
        centralDeck = Game.getContext().getTable().getCentralDeck();
        for(Player p : players){
            Game.getContext().shuffleDeck(p);
        }
        // Initialiser les joueurs
        displayPlayers();
        // Intialiser le deck central
        displayCentralDeck();
        // Initialiser les jetons
        // Initialiser le chat
        // Initialiser le
    }

    private static void displayPlayers(){

    }
    private static void displayCentralDeck(){
        System.out.println("Display Central Deck");
        Image imageQuestion = new Image(String.valueOf(HelloApplication.class.getResource("images/cards/card-back/card-back-question.png")), 200,300 ,false,true);
        ImageView imageViewQuestion = new ImageView(imageQuestion);
        root.getChildren().add(imageViewQuestion);
        Button buttonCentralDeck = new Button("Pick");
        root.getChildren().add(buttonCentralDeck);
        buttonCentralDeck.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                root.getChildren().remove(imageViewQuestion);
                Image imageCentralDeckCard = new Image(String.valueOf(HelloApplication.class.getResource(centralDeck.get(cptCentralDeck).imageResource)), 200,300 ,false,true);
                ImageView imageViewCentralDeckCard = new ImageView(imageCentralDeckCard);
                root.getChildren().add(imageViewCentralDeckCard);
                centralDeck.remove(cptCentralDeck);
                cptCentralDeck++;
            }
        });
    }

}
