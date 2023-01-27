
package com.example.the7wonders.domain.controllers;//package com.example.the7wonders;

//import com.example.the7wonders.domain.Game;
import com.example.the7wonders.InitializationController;

//package com.example.the7wonders.domain.controllers;


import com.example.the7wonders.domain.game.Game;
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

    /**
     * Lors du click sur le bouton de validation, la page permettant au joueur de choisir sa merveille et son nom
     * @throws UnsupportedAudioFileException
     * @throws LineUnavailableException
     * @throws IOException
     */
    @FXML
    public void onHelloButtonClick() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        Game.getContext().setNbPlayers(Integer.parseInt(welcomeText.getText()));
        InitializationController.launch();

    }

    /**
     * Méthode d'initialisation pour le slider permettant de choisir le nombre de joueurs
     */
    @FXML
    public void initialize(){
        nbPlayersSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            welcomeText.setText(Integer.toString(newValue.intValue()));
        });
    }

    /**
     * Lors du click sur le bouton exit, le jeu se ferme
     */
    @FXML
    protected void onExitButton(){
        Stage stage = (Stage) rootVBox.getScene().getWindow();
        stage.close();
    }
}
