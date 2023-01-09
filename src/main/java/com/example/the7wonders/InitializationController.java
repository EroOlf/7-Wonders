package com.example.the7wonders;


import com.example.the7wonders.domain.controllers.GameController;
import com.example.the7wonders.domain.game.Game;
import com.example.the7wonders.domain.wonder.Wonder;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;

import javafx.geometry.HPos;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;



import java.util.ArrayList;
import java.util.List;

public class InitializationController {

    private static FlowPane root = new FlowPane();
    @FXML
    private static Label descriptionText = new Label();
    @FXML
    private static Label errorText = new Label();
    @FXML
    private static TextField pseudo = new TextField();

    //Lors de l'appui sur le bouton, currentNbPlayer s'incrémente
    @FXML
    private static Button validationButton = new Button("Submit");

    private static List<Wonder> availableWonders;
    @FXML
    private static List<ImageView> imageWonders = new ArrayList<>();
    @FXML
    private static List<Button> buttonWonders = new ArrayList<>();

    @FXML
    private VBox rootVBox;

    private static int currentNbPlayer = 0;
    private static final int nbPlayer = Game.getContext().getNbPlayers();
    private static Wonder wonder;

    public static void launch(){

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("sceneChoixWonders.fxml"));
      // Scene scene = new Scene(root, 600, 600);

        //background avec couleur et contour
        BackgroundFill bgFill = new BackgroundFill(Color.valueOf("#8a6227"), new CornerRadii(0), new Insets(0));
        BackgroundFill bgFill2 = new BackgroundFill(Color.valueOf("#DECBAA"), new CornerRadii(10), new Insets(10));
        Background bg = new Background(bgFill, bgFill2);
        root.setBackground(bg);


        Scene scene = new Scene(root, 780, 450);


       // Scene scene = new Scene(root, 800, 600);

        //scene.getStylesheets().add(HelloApplication.class.getResource("fight.css").toExternalForm());
        HelloApplication.stage.setTitle("Choose a Wonder and a name");
        HelloApplication.stage.setScene(scene);
        HelloApplication.stage.show();

        // espace vertical
        root.setVgap(25);


        // Réflechir à juste faire un tableau
        availableWonders = new ArrayList<>();
        availableWonders.add(Wonder.Alexandrie);
        availableWonders.add(Wonder.Babylone);
        availableWonders.add(Wonder.Ephese);
        availableWonders.add(Wonder.Halicarnasse);
        availableWonders.add(Wonder.Olympie);
        availableWonders.add(Wonder.Gizeh);
        availableWonders.add(Wonder.Rhodes);
        displayElements();
        choice();

        //espace horizontal de sorte à créer 2 colonnes
        root.setHgap(50);
    }

    public static void choice(){

        root.getChildren().addAll(descriptionText, pseudo, validationButton, errorText);
        descriptionText.setText("  Joueur numéro " + currentNbPlayer + " doit choisir un nom et une merveille !");
        wonder = null;
        for(Wonder w : availableWonders){
            Image image = new Image(String.valueOf(HelloApplication.class.getResource(w.getPathImage() + "wonder-" + w.frenchName +".png")), 80,100 ,false,true);
            ImageView imageView = new ImageView(image);
            imageWonders.add(imageView);

            //un effet de drop Shadow
            DropShadow ds = new DropShadow();
            ds.setOffsetY(8.0);
            ds.setOffsetX(8.0);
            ds.setRadius(6.0);
            ds.setColor(Color.valueOf("#8a6227"));

            Button button1 = new Button("Choose");
            button1.setEffect(ds);

            button1.setPrefSize(80, 30);
            button1.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
            button1.getStyleClass().add("button1");
            button1.setFocusTraversable(true);
            button1.setOnMouseClicked(mouseEvent -> button1.requestFocus());
            button1.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    wonder = w;
                }
            });
            buttonWonders.add(button1);
            root.getChildren().addAll(imageView, button1);
        }
    }

    private static void displayElements(){

        validationButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String name = pseudo.getText();
                pseudo.setText("");
                if(name == null){
                    errorText.setText("Veuillez entrer votre nom");
                    return;
                }
                if(wonder == null){
                    errorText.setText("Veuillez choisir une merveille");
                    return;
                }
                Game.getContext().createPlayers(name, wonder);
                currentNbPlayer++;
                availableWonders.remove(wonder);
                errorText.setText("");
                if(currentNbPlayer < nbPlayer){
                    root.getChildren().clear();
                    choice();
                    System.out.println("Joueur suivant");
                } else{
                    // Lancer la page de jeu
                    Game.getContext().associateNeighbors();
                    GameController.initializeGame();
                    System.out.println("Lancement du plateau de jeu");
                }
            }
        });
    }
}
