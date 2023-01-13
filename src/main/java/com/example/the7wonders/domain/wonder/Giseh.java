package com.example.the7wonders.domain.wonder;

import javafx.scene.layout.FlowPane;

import java.util.ArrayList;
import java.util.List;

public class Giseh extends WonderClass{
    private static List<Piece> pieces = new ArrayList<Piece>(){{
        add(new Piece(5, PieceEffects.Null, 2, true, "images/wonders/giseh/piece-front-giseh-1.png", 0, 0));
        add(new Piece(6, PieceEffects.Null, 3, false, "images/wonders/giseh/piece-front-giseh-2.png", 0, 100));
        add(new Piece(7, PieceEffects.Null,3, true, "images/wonders/giseh/piece-front-giseh-3.png", 100, 100));
        add(new Piece(8, PieceEffects.Null, 4, false, "images/wonders/giseh/piece-front-giseh-4.png", 0, 200));
    }};
    private static Wonder wonder = Wonder.Gizeh;

    public Giseh(){
        super(wonder, pieces);
    }

    @Override
    public void constructWonder(FlowPane root) {

    }
}
