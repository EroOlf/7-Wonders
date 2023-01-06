package com.example.the7wonders;

import com.example.the7wonders.domain.Game;
import com.example.the7wonders.domain.GameController;
import com.example.the7wonders.domain.Wonder;
import javafx.beans.property.SimpleMapProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;

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

    private static int currentNbPlayer = 0;
    private static final int nbPlayer = Game.getContext().getNbPlayers();
    private static Wonder wonder;

    public static void launch(){
        Scene scene = new Scene(root, 800, 800);
        //scene.getStylesheets().add(HelloApplication.class.getResource("fight.css").toExternalForm());
        HelloApplication.stage.setTitle("Choose a Wonder and a name");
        HelloApplication.stage.setScene(scene);
        HelloApplication.stage.show();

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
    }

    public static void choice(){
        root.getChildren().addAll(descriptionText, pseudo, validationButton, errorText);
        descriptionText.setText("Joueur numéro " + currentNbPlayer + " doit choisir un nom et une merveille !");
        wonder = null;
        for(Wonder w : availableWonders){
            Image image = new Image(String.valueOf(HelloApplication.class.getResource(w.getPathImage() + "wonder-" + w.frenchName +".png")), 200,300 ,false,true);
            ImageView imageView = new ImageView(image);
            imageWonders.add(imageView);
            Button button = new Button("Choose");
            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    wonder = w;
                }
            });
            buttonWonders.add(button);
            root.getChildren().addAll(imageView, button);
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
