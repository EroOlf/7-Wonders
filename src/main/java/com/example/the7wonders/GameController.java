package com.example.the7wonders;
import com.example.the7wonders.domain.game.Game;
import com.example.the7wonders.domain.game.Player;
import com.example.the7wonders.domain.cards.*;
import com.example.the7wonders.domain.tokens.ProgressToken;
import com.example.the7wonders.domain.tokens.ProgressTokens;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import static com.example.the7wonders.HelloApplication.stage;

public class GameController {
    // Pour l'instant juste afficher les merveilles
    @FXML
    private static final FlowPane root = new FlowPane();
    @FXML
    private static final Label infoLabel = new Label();

    private static List<CardType> leftDeck;
    private static List<CardType> playerDeck;
    private static List<CardType> centralDeck = new ArrayList<>();

    private static int currentPlayer = 0;
    private static final int nbPlayers = Game.getContext().getNbPlayers();
    private static final List<Player> players = Game.getContext().getPlayers();


    @FXML
    private static Button nameplayer1 = new Button();
    @FXML
    private static Button nameplayer2 = new Button();


    @FXML
    private ImageView imageViewCentralDeck = new ImageView();
    @FXML
    private ImageView imageViewPlayerDeck = new ImageView();
    @FXML
    private ImageView ImageViewLeftNeighbor = new ImageView();
    @FXML
    private ImageView imageView1 = new ImageView();
    @FXML
    private ImageView imageScienceToken = new ImageView();
    @FXML
    private static Button buttonScienceToken = new Button();


    /**
     * Affichage d'une carte sur la pioche
     * @param type : Carte à afficher
     */
    private void setRessources(CardType type) {
        Image Ressource1 = new Image(String.valueOf(HelloApplication.class.getResource(type.imageResource)));
        System.out.println(String.valueOf(HelloApplication.class.getResource(centralDeck.get(0).imageResource)));
        imageView1.setImage(Ressource1);
        centralDeck.remove(0);
        players.get(currentPlayer).getWonder().returnPiece(players.get(currentPlayer));
    }

    /**
     * Initialisation de la page
     */
    public static void initializeGame()  {
            try {
                FXMLLoader fxmlLoader1 = new FXMLLoader(HelloApplication.class.getResource("plateau.fxml"));
                Scene scene1 = new Scene(fxmlLoader1.load(), 720, 500);
                stage.setTitle("Menu");
                stage.setScene(scene1);
                stage.show();
                initializeData();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            initializeData();
        }


    /**
     * Initialisation des éléments sur la page de jeu
     */
    private static void initializeData(){
        Game.getContext().shuffleCentralDeck();
        centralDeck = Game.getContext().getTable().getCentralDeck();
        for(Player p : players){
            Game.getContext().shuffleDeck(p);
        }
        // Initialiser les joueurs
        displayPlayers();
    }

    /**
     * Affichage des joueurs
     */
    private static void displayPlayers(){
        for(Player p : players){
            for (Button button : Arrays.asList(nameplayer1, nameplayer2)) {
                button.setText(p.getName());
            }
            System.out.println(p.getWonder().getName());
            //Afficher les pioches
            p.getWonderDeck();
        }
    }

    /**
     * Méthode permettant de piocher sur la pioche centrale
     * @param actionEvent
     */
    public void centralDeckClick(ActionEvent actionEvent) {
        CardType card = centralDeck.get(0);
        Image imageCentralDeckCard = new Image(String.valueOf(HelloApplication.class.getResource(card.imageResource)));
        System.out.println(HelloApplication.class.getResource(centralDeck.get(0).imageResource));
        imageViewCentralDeck.setImage(imageCentralDeckCard);
        centralDeck.remove(0);

        players.get(currentPlayer).getWonder().returnPiece(players.get(currentPlayer));
        nextPlayer();
        setRessources(card);

    }

    /**
     * Méthode permettant de piocher sur sa pioche
     */
    public void playerDeckClick(){
        CardType card = players.get(currentPlayer).getWonderDeck().get(0);
        Image imagePlayerDeck = new Image(String.valueOf(HelloApplication.class.getResource(card.imageResource)));
        imageViewPlayerDeck.setImage(imagePlayerDeck);
        giveResources(card);
        players.get(currentPlayer).getWonderDeck().remove(0);

        players.get(currentPlayer).getWonder().returnPiece(players.get(currentPlayer));
        nextPlayer();
        setRessources(card);
    }

    /**
     * Méthode permettant de piocher dans la pioche du voisin de gauche
     */
    public void NeighborLeftDeckClick(){
        CardType card = players.get(currentPlayer).getVoisinGauche().getWonderDeck().get(0);
        Image imageNeighborLeft = new Image(String.valueOf(HelloApplication.class.getResource(card.imageResource)));
        ImageViewLeftNeighbor.setImage(imageNeighborLeft);
        giveResources(card);
        players.get(currentPlayer).getWonderDeck().remove(0);
        players.get(currentPlayer).getWonder().returnPiece(players.get(currentPlayer));
        nextPlayer();
        setRessources(card);

    }


    /**
     * Méthode permettant de piocher les jetons scientifiques
     * @param actionEvent
     */
    public void scienceTokenDeckClick(ActionEvent actionEvent){
        ProgressToken pT = Game.getContext().getTable().getTokens().getProgressTokens().get(0);
        Image imageScience = new Image(String.valueOf(HelloApplication.class.getResource(pT.imageResource)));
        imageScienceToken.setImage(imageScience);
        Game.getContext().getTable().getTokens().getProgressTokens().remove(0);
        buttonScienceToken.setDisable(true);
    }

    /**
     * Changement de joueur
     */
    private static void nextPlayer(){
        if(currentPlayer == players.size() - 1){
            currentPlayer = 0;
        }else{
            currentPlayer++;
        }
    }

    private static void enablePickToken(){
        //Activer le bouton de pioche token
        buttonScienceToken.setDisable(false);
    }

    /**
     * Lorqu'un joueur pioche une carte, on associe ses différentes propriétés au joueur
     * @param card : carte que le joueur a pioché
     */
    private static void giveResources(CardType card){
        Player p = players.get(currentPlayer);
        Material materialt = card.material;
        ScienceCategory science = card.scienceCategory;
        if(materialt != null){
            p.setMaterials(card.material, 1);
            p.getWonder().returnPiece(players.get(currentPlayer));
        }
        if(science != null){
            p.setScienceTokens(card.scienceCategory, 1);
            // Si le joueur peut choisir un token
            if(p.couldChooseToken()){
                enablePickToken();
            }
        }
        p.setLaurelCount(card.laurelCount);
        p.setShieldCount(card.shieldCount);
        Game.getContext().getTable().setCornCount(card.cornCount);
        nextPlayer();
    }

    /**
     * Lors d'un click sur le bouton exit, on retrouve la 1ère page du jeu
     * @throws IOException
     * @throws UnsupportedAudioFileException
     * @throws LineUnavailableException
     */
    public void onExitButtonAction() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        FXMLLoader fxmlLoader = new FXMLLoader(GameController.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage = stage;
        Game.play();
        stage.setTitle("7 Wonders Architects");
        stage.setScene(scene);
        stage.show();
    }}
