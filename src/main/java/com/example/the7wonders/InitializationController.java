package com.example.the7wonders;


import com.example.the7wonders.domain.controllers.GameController;
import com.example.the7wonders.domain.game.Game;
import com.example.the7wonders.domain.wonder.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;

import javafx.geometry.HPos;

import javafx.geometry.Insets;
import javafx.scene.Node;
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
import javafx.stage.Stage;


import javax.crypto.spec.PSource;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.example.the7wonders.HelloApplication.stage;

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
    @FXML
    private static Button onExitButton = new Button("Exit");
    private static List<Wonder> availableWonders;
    @FXML
    private static List<ImageView> imageWonders = new ArrayList<>();
    @FXML
    private static List<Button> buttonWonders = new ArrayList<>();

    @FXML
    private static VBox rootVBox;

    private static int currentNbPlayer = 0;
    private static final int nbPlayer = Game.getContext().getNbPlayers();
    private static Wonder wonder;

    public static void launch() throws UnsupportedAudioFileException, LineUnavailableException, IOException {


       // FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("sceneChoixWonders.fxml"));
      // Scene scene = new Scene(root, 600, 600);

       // FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("sceneChoixWonders.fxml"));


        //background avec couleur et contour
        BackgroundFill bgFill = new BackgroundFill(Color.valueOf("#8a6227"), new CornerRadii(0), new Insets(0));
        BackgroundFill bgFill2 = new BackgroundFill(Color.valueOf("#DECBAA"), new CornerRadii(10), new Insets(10));
        Background bg = new Background(bgFill, bgFill2);
        root.setBackground(bg);


        Scene scene = new Scene(root, 800, 790);

        //anciennement :  Scene scene = new Scene(root, 800, 600);

        //scene.getStylesheets().add(HelloApplication.class.getResource("fight.css").toExternalForm());
        stage.setTitle("Choose a Wonder and a name");
        stage.setScene(scene);
        stage.show();

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

    public static void choice() throws IOException, UnsupportedAudioFileException, LineUnavailableException {

        root.getChildren().addAll(descriptionText, pseudo, validationButton, errorText);
        root.setPadding(new Insets(25,5,0,5));
        descriptionText.setText("    Joueur numéro " + currentNbPlayer + " doit choisir un nom et une merveille !");
        descriptionText.setTextFill(Color.valueOf("#8a6227"));
        descriptionText.setFont(Font.font("Cochin", 18));

        wonder = null;
        for(Wonder w : availableWonders){
            Image image = new Image(String.valueOf(HelloApplication.class.getResource(w.getPathImage() + "wonder-" + w.frenchName +".png")), 100,100 ,true,true);
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
            button1.setFont(Font.font("Cochin",13));
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
            validationButton.setFont(Font.font("Cochin",15));
            validationButton.setEffect(ds);
            validationButton.setTextFill(Color.valueOf("#8a6227"));
            validationButton.setPrefSize(70, 25);
            validationButton.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
            validationButton.getStyleClass().add("validationButton");
            validationButton.setFocusTraversable(true);

            //button EXIT
            onExitButton.setFont(Font.font("Cochin",15));
            onExitButton.setEffect(ds);
            onExitButton.setTextFill(Color.valueOf("#8a6227"));
            onExitButton.setPrefSize(70, 25);
            onExitButton.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
            onExitButton.getStyleClass().add("onExitButton");
            onExitButton.setFocusTraversable(true);
            onExitButton.setOnMouseClicked(mouseEvent -> onExitButton.requestFocus());
            onExitButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    Stage stage = (Stage) rootVBox.getScene().getWindow();
                    stage.close();
                }
            })
        ;}
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
                System.out.println(transformationWonderToClass(wonder).getName());
                Game.getContext().createPlayers(name, transformationWonderToClass(wonder));

                currentNbPlayer++;
                availableWonders.remove(wonder);
                errorText.setText("");
                if(currentNbPlayer < nbPlayer){
                    root.getChildren().clear();
                    try {
                        choice();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (UnsupportedAudioFileException e) {
                        e.printStackTrace();
                    } catch (LineUnavailableException e) {
                        e.printStackTrace();
                    }
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

    private static WonderClass transformationWonderToClass(Wonder wonder){
        switch(wonder.frenchName){
            case "Alexandrie":
                WonderClass A = new Alexandrie();
                System.out.println(A.getName());
                return A;
            case "Babylone":
                WonderClass B = new Babylon();
                System.out.println(B.getName());
                return B;
            case "Ephese":
                WonderClass C = new Ephese();
                System.out.println(C.getName());
                return C;
            case "Halicarnasse":
                WonderClass D = new Halicarnasse();
                System.out.println(D.getName());
                return D;
            case "Olympe":
                WonderClass E = new Olympie();
                System.out.println(E.getName());
                return E;
            case "Rhodes":
                WonderClass F = new Rhodes();
                System.out.println(F.getName());
                return F;
            case "Gizeh":
                WonderClass H = new Rhodes();
                System.out.println(H.getName());
                return H;


            default:
                return null;
        }
    }
}
