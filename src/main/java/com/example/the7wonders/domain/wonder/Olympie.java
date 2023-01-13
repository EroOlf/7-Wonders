package com.example.the7wonders.domain.wonder;

import javafx.scene.layout.FlowPane;

import java.util.ArrayList;
import java.util.List;

public class Olympie extends WonderClass{
    // Il n'y a pas les pièces backs
    private static List<Piece> pieces = new ArrayList<Piece>(){{
        add(new Piece(3, PieceEffects.CardCentralDeck, 2, true, "images/wonders/olympie/piece-front-olympie-2.png", 0, 0));
        add(new Piece(5, PieceEffects.CardCentralDeck, 3, true, "images/wonders/olympie/piece-front-olympie-3.png", 0, 100));
        add(new Piece(4, PieceEffects.CardCentralDeck,3, false, "images/wonders/olympie/piece-front-olympie-4.png", 100, 100));
        add(new Piece(4, PieceEffects.Null, 2, false, "images/wonders/olympie/piece-front-olympie-5.png", 0, 200));
        add(new Piece(7, PieceEffects.Null, 4, false, "images/wonders/olympie/piece-front-olympie-6.png", 0, 300));
    }};
    private static Wonder wonder = Wonder.Olympie;

    public Olympie(){
        super(wonder, pieces);
    }

    @Override
    public void constructWonder(FlowPane root) {

    }
}
