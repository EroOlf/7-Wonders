package com.example.the7wonders.domain;

import com.example.the7wonders.HelloApplication;
import com.example.the7wonders.Player;
import javafx.fxml.FXML;
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

    private static int currentPlayer = 0;
    private static final int nbPlayers = Game.getContext().getNbPlayers();
    private static List<Player> players = Game.getContext().getPlayers();

    public static void initializeGame(){
        initializeData();
    }

    private static void initializeData(){
        Game.getContext().shuffleCentralDeck();
        centralDeck = Game.getContext().getTable().getCentralDeck();
        Image imageQuestion = new Image(String.valueOf(HelloApplication.class.getResource("images/cards/card-back/card-back-question.png")), 200,300 ,false,true);
        ImageView imageViewQuestion = new ImageView(imageQuestion);
        root.getChildren().add(imageViewQuestion);
        for(Player p : players){
            Game.getContext().shuffleDeck(p);
        }

        // Intialiser le deck central
        // Initialiser les jetons
        // Initialiser le chat
        // Initialiser le
    }

}
