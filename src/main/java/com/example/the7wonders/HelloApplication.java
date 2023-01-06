package com.example.the7wonders;

import com.example.the7wonders.domain.game.Game;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class HelloApplication extends Application {
    public static Stage stage;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        HelloApplication.stage = stage;
        Game.play();
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
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
}