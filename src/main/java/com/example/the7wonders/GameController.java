package com.example.the7wonders;


import com.example.the7wonders.domain.game.Game;
import com.example.the7wonders.domain.game.Player;
import com.example.the7wonders.domain.game.Table;
import com.example.the7wonders.domain.cards.*;
import com.example.the7wonders.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.example.the7wonders.HelloApplication.stage;

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

    @FXML
    private ImageView imageViewCentralDeck = new ImageView();
    @FXML
    private ImageView imageViewPlayerDeck = new ImageView();
    @FXML
    private ImageView ImageViewLeftNeighbor = new ImageView();

    //atribut name dans le player

    public static void initializeGame()  {
        //public void onHelloButtonClick(ActionEvent event) {
            try {
                //Stage stage = new Stage();
                FXMLLoader fxmlLoader1 = new FXMLLoader(HelloApplication.class.getResource("plateau.fxml"));
                Scene scene1 = new Scene(fxmlLoader1.load(), 720, 500);
                HelloApplication.stage.setTitle("Menu");
                HelloApplication.stage.setScene(scene1);
                HelloApplication.stage.show();
                initializeData();
                //((Node) (event.getSource())).getScene().getWindow().hide();
                //HelloApplication.stage.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            initializeData();
        }
        

    private static void initializeData(){
        Game.getContext().shuffleCentralDeck();
        centralDeck = Game.getContext().getTable().getCentralDeck();
        //displayCentralDeck();
        for(Player p : players){
            Game.getContext().shuffleDeck(p);
        }
        // Initialiser les joueurs
        displayPlayers();
        // Intialiser le deck central
        //displayCentralDeck();
        // Initialiser les jetons
        // Initialiser le chat
        // Initialiser le
    }

    private static void displayPlayers(){
        for(Player p : players){
            // Afficher les meveilles en construction
            p.getWonder();
            System.out.println(p.getWonder().getName());
            //Afficher les pioches
            p.getWonderDeck();
        }
    }

    private static void displayCard(Player p){
        List<CardType> deck = p.getWonderDeck();
        Image imageCarBack = new Image(String.valueOf(HelloApplication.class.getResource("images/cards/card-back/card-back-"+ p.getWonder()+".png")), 200,300 ,false,true);

        for(CardType c : deck){
            Image imageCard = new Image(String.valueOf(HelloApplication.class.getResource("images/cards/card-back/card-back-question.png")), 200,300 ,false,true);
            ImageView imageViewCard = new ImageView(imageCard);
            root.getChildren().add(imageViewCard);
        }
    }

  /*  private static void displayCentralDeck(){
        System.out.println("Display Central Deck");
        Image imageQuestion = new Image(String.valueOf(HelloApplication.class.getResource("images/cards/card-back/card-back-question.png")), 200,300 ,false,true);
        ImageView imageViewQuestion = new ImageView(imageQuestion);
        root.getChildren().add(imageViewQuestion);
        Button boutonPioche = new Button("Pioche");
        root.getChildren().add(boutonPioche);
        boutonPioche.setOnAction(new EventHandler<ActionEvent>() {
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
    } */


    public void centralDeckClick(ActionEvent actionEvent) {
        Image imageCentralDeckCard = new Image(String.valueOf(HelloApplication.class.getResource(centralDeck.get(cptCentralDeck).imageResource)));
        System.out.println(String.valueOf(HelloApplication.class.getResource(centralDeck.get(cptCentralDeck).imageResource)));
        imageViewCentralDeck.setImage(imageCentralDeckCard);
        centralDeck.remove(cptCentralDeck);
        cptCentralDeck++;
        nextPlayer();
    }

    public void playerDeckClick(ActionEvent actionEvent){
        System.out.println(players.get(currentPlayer).getWonderDeck().size());
        Image imagePlayerDeck = new Image(String.valueOf(HelloApplication.class.getResource(players.get(currentPlayer).getWonderDeck().get(0).imageResource)));
        imageViewPlayerDeck.setImage(imagePlayerDeck);
        players.get(currentPlayer).getWonderDeck().remove(0);
        nextPlayer();
    }

    public void NeighborLeftDeckClick(ActionEvent actionEvent){
        Image imageNeighborLeft = new Image(String.valueOf(HelloApplication.class.getResource(players.get(currentPlayer).getVoisinGauche().getWonderDeck().get(0).imageResource)));
        ImageViewLeftNeighbor.setImage(imageNeighborLeft);
        players.get(currentPlayer).getWonderDeck().remove(0);
        nextPlayer();
    }

    private void nextPlayer(){
        if(currentPlayer == players.size() - 1){
            currentPlayer = 0;
        }else{
            currentPlayer++;
        }
    }

    public void onExitButtonAction(ActionEvent actionEvent) {

    }
}