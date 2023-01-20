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
import javafx.scene.layout.Pane;
import org.w3c.dom.ls.LSOutput;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import static com.example.the7wonders.HelloApplication.stage;
import static com.example.the7wonders.domain.cards.CardType.*;

public class GameController {
    // Pour l'instant juste afficher les merveilles
    @FXML
    private static FlowPane root = new FlowPane();
    @FXML
    private static Label infoLabel = new Label();

    @FXML
    private static Label nameplayer1;
    @FXML
    private static Label nameplayer2;

    private static List<CardType> leftDeck;
    private static List<CardType> playerDeck;
    private static List<CardType> centralDeck = new ArrayList<>();

    private static int currentPlayer = 0;
    private static final int nbPlayers = Game.getContext().getNbPlayers();
    private static List<Player> players = Game.getContext().getPlayers();


    @FXML
    private static Button nameplayer1;

    @FXML
    private static Button nameplayer2;

    @FXML
    private ImageView imageViewCentralDeck = new ImageView();
    @FXML
    private ImageView imageViewPlayerDeck = new ImageView();
    @FXML
    private ImageView ImageViewLeftNeighbor = new ImageView();
    @FXML
    private ImageView imageScienceToken = new ImageView();

    //atribut name dans le player

    public static void initializeGame()  {
        //public void onHelloButtonClick(ActionEvent event) {
            try {
                //Stage stage = new Stage();
                FXMLLoader fxmlLoader1 = new FXMLLoader(HelloApplication.class.getResource("plateau.fxml"));
                Scene scene1 = new Scene(fxmlLoader1.load(), 720, 500);
                stage.setTitle("Menu");
                stage.setScene(scene1);
                stage.show();
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
            // Récupérer le nom des joueurs et l'afficher sur le plateau

            // Afficher les meveilles en construction
            for (Button button : Arrays.asList(nameplayer1, nameplayer2)) {
                button.setText(p.getName());
            }
            System.out.println(p.getWonder().getName());
            //Afficher les pioches
            p.getWonderDeck();
        }
        /*nameplayer1.setText(players.get(0).getName());
        System.out.println(nameplayer1.getText());
        nameplayer2.setText(players.get(1).getName());*/
    }

    private static void displayCard(Player p){
        List<CardType> deck = p.getWonderDeck();
        Image imageCardBack = new Image(String.valueOf(HelloApplication.class.getResource("images/cards/card-back/card-back-"+ p.getWonder()+".png")), 200,300 ,false,true);

        for(CardType c : deck){
            Image imageCard = new Image(String.valueOf(HelloApplication.class.getResource("images/cards/card-back/card-back-question.png")), 200,300 ,false,true);
            ImageView imageViewCard = new ImageView(imageCard);
            root.getChildren().add(imageViewCard);
        }
    }

    public static void setNameplayer2(Button nameplayer2) {
        GameController.nameplayer2 = nameplayer2;
    }

    public static void setNameplayer1(Button nameplayer1) {
        GameController.nameplayer1 = nameplayer1;
    }

    public void centralDeckClick(ActionEvent actionEvent) {
        CardType card = centralDeck.get(0);
        Image imageCentralDeckCard = new Image(String.valueOf(HelloApplication.class.getResource(card.imageResource)));
        System.out.println(String.valueOf(HelloApplication.class.getResource(centralDeck.get(0).imageResource)));
        imageViewCentralDeck.setImage(imageCentralDeckCard);
        centralDeck.remove(0);
    }

    public void playerDeckClick(ActionEvent actionEvent){
        CardType card = players.get(currentPlayer).getWonderDeck().get(0);
        Image imagePlayerDeck = new Image(String.valueOf(HelloApplication.class.getResource(card.imageResource)));
        imageViewPlayerDeck.setImage(imagePlayerDeck);
        giveResources(card);
        players.get(currentPlayer).getWonderDeck().remove(0);
    }

    public void NeighborLeftDeckClick(ActionEvent actionEvent){
        CardType card = players.get(currentPlayer).getVoisinGauche().getWonderDeck().get(0);
        Image imageNeighborLeft = new Image(String.valueOf(HelloApplication.class.getResource(card.imageResource)));
        ImageViewLeftNeighbor.setImage(imageNeighborLeft);
        giveResources(card);
        players.get(currentPlayer).getWonderDeck().remove(0);
    }

    public void scienceTokenDeckClick(ActionEvent actionEvent){
        // Récupérer la liste des tokens
        // Récupérer le 1er de la liste
        ProgressToken pT = Game.getContext().getTable().getTokens().getProgressTokens().get(0);
        Image imageScience = new Image(String.valueOf(HelloApplication.class.getResource(pT.imageResource)));
        imageScienceToken.setImage(imageScience);
        Game.getContext().getTable().getTokens().getProgressTokens().remove(0);
        // Le retirer
        // Attribuer le token et son effet au joueur
    }

    private static void nextPlayer(){
        if(currentPlayer == players.size() - 1){
            currentPlayer = 0;
        }else{
            currentPlayer++;
        }
    }

    private static void enablePickToken(){
        //Activer le bouton de pioche token
    }

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

    public void onExitButtonAction(ActionEvent actionEvent) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        FXMLLoader fxmlLoader = new FXMLLoader(GameController.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage = stage;
        Game.play();
        stage.setTitle("7 Wonders Architects");
        stage.setScene(scene);
        stage.show();
    }


    /*public class DisplayCardImageController {

        @FXML
        private static Pane imageContainer; // référence au JPanel qui contiendra l'image
        private static Image imagePath;

        public static void chooseCard(String card) {
            switch (card) {
                case "material:wood":
                   // imagePath = new Image("card-material-wood-lumberjack.png");
                   // giveResources(CardMaterialWood);
                   // ImageIcon imageIcon = new ImageIcon(new File(String.valueOf(imagePath)).getAbsolutePath());
                    Image image = new Image(new File("card-material-wood-lumberjack.png").toURI().toString());
                    ImageView imgView = new ImageView(image);
                    imageViewCentralDeck1.getChildren().clear();
                    imageViewCentralDeck1.getChildren().add(imgView);
                    break;
                case "material:paper":
                    //imagePath = new Image("card-material-paper-women.png");
                    //giveResources(CardMaterialPaper);
                    Image image1 = new Image(new File("card-material-paper-women.png").toURI().toString());
                    ImageView imgView1 = new ImageView(image1);
                    imageViewCentralDeck1.getChildren().clear();
                    imageViewCentralDeck1.getChildren().add(imgView1);
                    break;
                case "material:brick":
                    //imagePath = new Image("card-material-brick-women.png");
                    //giveResources(CardMaterialBrick);
                    Image image2 = new Image(new File("card-material-brick-women.png").toURI().toString());
                    ImageView imgView2 = new ImageView(image2);
                    imageViewCentralDeck1.getChildren().clear();
                    imageViewCentralDeck1.getChildren().add(imgView2);
                    break;
                case "material:stone":
                    //imagePath = new Image("card-material-stone-stonecutter.png");
                    //giveResources(CardMaterialStone);
                    Image image3 = new Image(new File("card-material-stone-stonecutter.png").toURI().toString());
                    ImageView imgView3 = new ImageView(image3);
                    imageViewCentralDeck1.getChildren().clear();
                    imageViewCentralDeck1.getChildren().add(imgView3);

                    break;
                case "material:glass":
                    imagePath = new Image("card-material-glass-women.png");
                    giveResources(CardMaterialGlass);
                    Image image4 = new Image(new File("card-material-glass-women.png").toURI().toString());
                    ImageView imgView4 = new ImageView(image4);
                    imageViewCentralDeck1.getChildren().clear();
                    imageViewCentralDeck1.getChildren().add(imgView4);

                    break;
                case "material:gold":
                    imagePath = new Image("card-material-brick-women.png");
                    giveResources(CardMaterialGold);
                    break;
                case "science:law":
                    imagePath = new Image("card-progress-law.png");
                    giveResources(CardScienceLaw);
                    break;
                case "science:mechanic":
                    imagePath = new Image("card-progress-mechanic.png");
                    giveResources(CardScienceMechanic);
                    break;
                case "science:architect":
                    imagePath = new Image("card-progress-architect.png");
                    giveResources(CardScienceArchitect);
                    break;
                case "war:barbarian":
                    imagePath = new Image("card-war-barbarian-1corn.png");
                    giveResources(CardWar_barbarian);
                    break;
                case "war:centurion":
                    imagePath = new Image("card-war-centurion.png");
                    giveResources(CardWar_centurion);
                    break;
                case "war:archer":
                    imagePath = new Image("card-war-archer-2corns.png");
                    giveResources(CardWar_archer);
                    break;
                case "politic:emperor":
                    imagePath = new Image("card-politic-emperor-3laurel.png");
                    giveResources(CardPolitic_emperor);
                    break;
                case "politic:cat":
                    imagePath = new Image("card-politic-women-2laurel-cat.png");
                    giveResources(CardPolitic_cat);
                    break;
                default:
                    System.out.println("erreur");;
                    break;
            }*/

            //ImageIcon imageIcon = new ImageIcon(new File(String.valueOf(imagePath)).getAbsolutePath());
            /*/ Définit l'image à afficher
            Pane.removeAll();
            Pane.add(new JLabel(imageIcon));
            Pane.revalidate();
            Pane.repaint();*/

}
