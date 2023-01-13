package com.example.the7wonders.domain.wonder;

import javafx.scene.layout.FlowPane;

import java.util.ArrayList;
import java.util.List;

public class Halicarnasse extends WonderClass{
    private static List<Piece> pieces = new ArrayList<Piece>(){{
        add(new Piece(3, PieceEffects.Null, 2, false, "images/wonders/halicarnasse/piece-front-halicarnasse-1.png", 0, 0));
        add(new Piece(3, PieceEffects.ChooseACard, 2, true, "images/wonders/halicarnasse/piece-front-halicarnasse-2.png", 0, 100));
        add(new Piece(5, PieceEffects.ChooseACard,3, true, "images/wonders/halicarnasse/piece-front-halicarnasse-3.png", 100, 100));
        add(new Piece(6, PieceEffects.Null, 2, false, "images/wonders/halicarnasse/piece-front-halicarnasse-4.png", 0, 200));
        add(new Piece(7, PieceEffects.Null, 4, false, "images/wonders/halicarnasse/piece-front-halicarnasse-5.png", 0, 300));
    }};
    private static Wonder wonder = Wonder.Halicarnasse;

    public Halicarnasse(){
        super(wonder, pieces);
    }

    @Override
    public void constructWonder(FlowPane root) {

    }
}
