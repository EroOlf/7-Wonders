package com.example.the7wonders.domain.wonder;

import com.example.the7wonders.HelloApplication;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;

import java.util.ArrayList;
import java.util.List;

public class Alexandrie extends WonderClass{
    private List<Piece> pieces;
    private Wonder wonder = Wonder.Alexandrie;

    public Alexandrie(){
        //super(wonder);
        pieces = new ArrayList<>();
        pieces.add(new Piece(4, PieceEffects.Null, 2, false, "images/wonders/alexandrie/piece-front-alexandrie-1.png", 0, 0));
        pieces.add(new Piece(6, PieceEffects.Null, 3, false, "images/wonders/alexandrie/piece-front-alexandrie-2.png", 0, 100));
        pieces.add(new Piece(5, PieceEffects.Pioche,3, true, "images/wonders/alexandrie/piece-front-alexandrie-3.png", 100, 100));
        pieces.add(new Piece(3, PieceEffects.Pioche, 2, true, "images/wonders/alexandrie/piece-front-alexandrie-4.png", 0, 200));
        pieces.add(new Piece(7, PieceEffects.Null, 4, false, "images/wonders/alexandrie/piece-front-alexandrie-6.png", 0, 300));
    }


    @Override
    public void constructWonder(FlowPane root){
        for(Piece p : pieces){
            Image image = new Image(String.valueOf(HelloApplication.class.getResource(p.getPathImage())), p.getPosX(), p.getPosY() ,false,true);
            ImageView imageView = new ImageView(image);
            root.getChildren().add(imageView);
        }

    }




}
