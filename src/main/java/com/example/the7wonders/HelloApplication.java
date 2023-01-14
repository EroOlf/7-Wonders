package com.example.the7wonders;


import com.example.the7wonders.domain.game.Game;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.List;

    public class HelloApplication extends Application {
        public static Stage stage;

        @Override
        public void start(Stage stage) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);

            HelloApplication.stage = stage;
            Game.play();
            stage.setTitle("7 Wonders Architects");
            stage.setScene(scene);
            stage.show();


            /*FXMLLoader fxmlLoader1 = new FXMLLoader(HelloApplication.class.getResource("plateau.fxml"));
            Scene scene1 = new Scene(fxmlLoader1.load(), 600, 400);
            HelloApplication.stage = stage;
            Game.play();
            stage.setTitle("Hello!");
            stage.setScene(scene1);
            stage.show();*/


        }

        public static void main(String[] args) throws Exception {
       /* Player p1 = new Player("Lucas", Wonder.Alexandrie);
        System.out.println(p1.getWonder().displayName);
        System.out.println(CardBack.Alexandrie);
        List<CardDecks.CardTypeQuantity> wonderDeck = CardDecks.deckCardQuantities_Alexandrie;
        for(CardDecks.CardTypeQuantity cd : wonderDeck){
            System.out.println(cd.cardType);
        } */
            launch();

        }

        public void onExitButton() {
            stage.close();
        }
    }

