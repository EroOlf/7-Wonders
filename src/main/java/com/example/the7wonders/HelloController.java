package com.example.the7wonders;


import com.example.the7wonders.domain.game.Game;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class HelloController {
    @FXML
    private Label welcomeText;
    @FXML
    private Slider nbPlayersSlider;
    @FXML
    private VBox rootVBox;
    @FXML
    public void onHelloButtonClick() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        Game.getContext().setNbPlayers(Integer.parseInt(welcomeText.getText()));
        InitializationController.launch();

    }

    @FXML
    public void initialize(){
        nbPlayersSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            welcomeText.setText(Integer.toString(newValue.intValue()));
        });
    }
    @FXML
    protected void onExitButton(){
        Stage stage = (Stage) rootVBox.getScene().getWindow();
        stage.close();
    }
}