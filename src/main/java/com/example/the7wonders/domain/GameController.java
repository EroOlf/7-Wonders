package com.example.the7wonders.domain;

import com.example.the7wonders.Player;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.util.List;

public class GameController {
    // Pour l'instant juste afficher les merveilles

    @FXML
    private static Label infoLabel = new Label();

    private static List<CardType> leftDeck;
    private static List<CardType> playerDeck;

    private static int currentPlayer = 0;
    private static final int nbPlayers = Game.getContext().getNbPlayers();
    private static List<Player> players = Game.getContext().getPlayers();

    public static void initializeGame(){
        initializeData();
    }

    private static void initializeData(){
        for(Player p : players){
            Game.getContext().shuffleDeck(p);
        }
        // Intialiser le deck central
        // Initialiser les jetons
        // Initialiser le chat
        // Initialiser le
    }

}
