package com.example.the7wonders.domain.controllers;

import com.example.the7wonders.domain.game.Game;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    private Slider nbPlayersSlider;

    @FXML
    public void onHelloButtonClick() {
        Game.getContext().setNbPlayers(Integer.parseInt(welcomeText.getText()));
        InitializationController.launch();
    }

    @FXML
    public void initialize(){
        nbPlayersSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            welcomeText.setText(Integer.toString(newValue.intValue()));
        });
    }
}