package com.example.the7wonders;


import com.example.the7wonders.domain.game.Game;
import com.example.the7wonders.domain.game.Player;
import com.example.the7wonders.domain.game.Table;
import com.example.the7wonders.domain.cards.*;
import com.example.the7wonders.*;
import com.example.the7wonders.domain.wonder.WonderClass;
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

    public void centralDeckClick(ActionEvent actionEvent) {
        CardType card = centralDeck.get(0);
        Image imageCentralDeckCard = new Image(String.valueOf(HelloApplication.class.getResource(card.imageResource)));
        System.out.println(String.valueOf(HelloApplication.class.getResource(centralDeck.get(0).imageResource)));
        imageViewCentralDeck.setImage(imageCentralDeckCard);
        centralDeck.remove(0);
        nextPlayer();
    }

    public void playerDeckClick(ActionEvent actionEvent){
        CardType card = players.get(currentPlayer).getWonderDeck().get(0);
        Image imagePlayerDeck = new Image(String.valueOf(HelloApplication.class.getResource(card.imageResource)));
        imageViewPlayerDeck.setImage(imagePlayerDeck);
        giveResources(card);
        players.get(currentPlayer).getWonderDeck().remove(0);
        nextPlayer();
    }

    public void NeighborLeftDeckClick(ActionEvent actionEvent){
        CardType card = players.get(currentPlayer).getVoisinGauche().getWonderDeck().get(0);
        Image imageNeighborLeft = new Image(String.valueOf(HelloApplication.class.getResource(card.imageResource)));
        ImageViewLeftNeighbor.setImage(imageNeighborLeft);
        giveResources(card);
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

    private void giveResources(CardType card){
        players.get(currentPlayer).setLaurelCount(card.laurelCount);
        players.get(currentPlayer).setShieldCount(card.shieldCount);
        players.get(currentPlayer).setMaterials(card.material, 1);
        Game.getContext().getTable().setCornCount(card.cornCount);
    }

    private void constructWonder(){
        WonderClass wonderPlayer = players.get(currentPlayer).getWonder();
        wonderPlayer.constructWonder(root);
    }

    public void onExitButtonAction(ActionEvent actionEvent) {

    }
}