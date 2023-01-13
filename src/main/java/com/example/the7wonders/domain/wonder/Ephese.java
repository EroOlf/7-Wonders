package com.example.the7wonders.domain.wonder;

import com.example.the7wonders.HelloApplication;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;

import java.util.ArrayList;
import java.util.List;

public class Ephese extends WonderClass{
    private static List<Piece> pieces = new ArrayList<Piece>(){{
        add(new Piece(3, PieceEffects.CardCentralDeck, 2, true, "images/wonders/ephese/piece-front-ephese-2.png", 0, 0));
        add(new Piece(5, PieceEffects.CardCentralDeck, 3, true, "images/wonders/ephese/piece-front-ephese-3.png", 0, 100));
        add(new Piece(4, PieceEffects.CardCentralDeck,3, false, "images/wonders/ephese/piece-front-ephese-4.png", 100, 100));
        add(new Piece(4, PieceEffects.Null, 2, false, "images/wonders/ephese/piece-front-ephese-5.png", 0, 200));
        add(new Piece(7, PieceEffects.Null, 4, false, "images/wonders/ephese/piece-front-ephese-6.png", 0, 300));
    }};
    private static Wonder wonder = Wonder.Ephese;

    public Ephese(){
        super(wonder, pieces);
    }

    @Override
    public void constructWonder(FlowPane root) {
        for(Piece p : pieces){
            Image image = new Image(String.valueOf(HelloApplication.class.getResource(p.getPathImage())), p.getPosX(), p.getPosY() ,false,true);
            ImageView imageView = new ImageView(image);
            root.getChildren().add(imageView);}
    }
}
