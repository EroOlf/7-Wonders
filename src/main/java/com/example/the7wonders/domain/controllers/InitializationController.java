package com.example.the7wonders.domain.controllers;

import com.example.the7wonders.HelloApplication;
import com.example.the7wonders.GameController;
import com.example.the7wonders.domain.game.Game;
import com.example.the7wonders.domain.wonder.Alexandrie;
import com.example.the7wonders.domain.wonder.Wonder;
import com.example.the7wonders.domain.wonder.WonderClass;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;

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

import javafx.scene.text.Font;


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

            public static void launch() {


                // FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("sceneChoixWonders.fxml"));
                // Scene scene = new Scene(root, 600, 600);

                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("sceneChoixWonders.fxml"));


                //background avec couleur et contour
                BackgroundFill bgFill = new BackgroundFill(Color.valueOf("#8a6227"), new CornerRadii(0), new Insets(0));
                BackgroundFill bgFill2 = new BackgroundFill(Color.valueOf("#DECBAA"), new CornerRadii(10), new Insets(10));
                Background bg = new Background(bgFill, bgFill2);
                root.setBackground(bg);


                Scene scene = new Scene(root, 1000, 800);

                //anciennement :  Scene scene = new Scene(root, 800, 600);

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

            public static void choice() {

                root.getChildren().addAll(descriptionText, pseudo, validationButton, errorText);

                root.setPadding(new Insets(25, 5, 0, 5));
                descriptionText.setText("    Joueur numéro " + currentNbPlayer + " doit choisir un nom et une merveille !");
                descriptionText.setTextFill(Color.valueOf("#8a6227"));
                descriptionText.setFont(Font.font("Cochin", 18));

                wonder = null;
                for (Wonder w : availableWonders) {
                    Image image = new Image(String.valueOf(HelloApplication.class.getResource(w.getPathImage() + "wonder-" + w.frenchName + ".png")), 100, 100, true, true);
                    ImageView imageView = new ImageView(image);
                    imageWonders.add(imageView);
                    imageView.setPreserveRatio(true);

                    imageView.setFitWidth(300);
                    imageView.setFitHeight(150);


                    //un effet de drop Shadow
                    DropShadow ds = new DropShadow();
                    ds.setOffsetY(8.0);
                    ds.setOffsetX(8.0);
                    ds.setRadius(6.0);
                    ds.setColor(Color.valueOf("#8a6227"));

                    //button CHOOSE
                    Button button1 = new Button("Choose");
                    button1.setFont(Font.font("Cochin", 13));
                    button1.setEffect(ds);
                    button1.setTextFill(Color.valueOf("#8a6227"));
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

                    //button SUBMIT
                    validationButton.setFont(Font.font("Cochin", 15));
                    validationButton.setEffect(ds);
                    validationButton.setTextFill(Color.valueOf("#8a6227"));
                    validationButton.setPrefSize(70, 25);
                    validationButton.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
                    validationButton.getStyleClass().add("validationButton");
                    validationButton.setFocusTraversable(true);
                    validationButton.setOnMouseClicked(mouseEvent -> validationButton.requestFocus());
                }
            }

            private static void displayElements() {

                validationButton.setOnAction(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent actionEvent) {
                        String name = pseudo.getText();
                        pseudo.setText("");
                        if (name == null) {
                            errorText.setText("Veuillez entrer votre nom");
                            return;
                        }
                        if (wonder == null) {
                            errorText.setText("Veuillez choisir une merveille");
                            return;
                        }
                        Game.getContext().createPlayers(name, transformationWonderToClass(wonder));
                        currentNbPlayer++;
                        availableWonders.remove(wonder);
                        errorText.setText("");
                        if (currentNbPlayer < nbPlayer) {
                            root.getChildren().clear();
                            choice();
                            System.out.println("Joueur suivant");
                        } else {
                            // Lancer la page de jeu
                            Game.getContext().associateNeighbors();
                            GameController.initializeGame();
                            System.out.println("Lancement du plateau de jeu");
                        }
                    }
                });
            }

            private static WonderClass transformationWonderToClass(Wonder wonder){
                switch(wonder.frenchName){
                    case "Alexandrie":
                        return new Alexandrie();
                    default:
                        return null;
                }
            }
        }

