package com.example.the7wonders.domain.wonder;

import javafx.scene.layout.FlowPane;

import java.util.ArrayList;
import java.util.List;

public class Babylon extends WonderClass{
    private static List<Piece> pieces = new ArrayList<Piece>(){{
        add(new Piece(3, PieceEffects.Null, 2, false, "images/wonders/babylon/piece-back-babylon-1.png", 0, 0));
        add(new Piece(0, PieceEffects.Jeton, 2, true, "images/wonders/babylon/piece-back-babylon-2.png", 0, 100));
        add(new Piece(3, PieceEffects.Null,2, false, "images/wonders/babylon/piece-back-babylon-3.png", 100, 100));
        add(new Piece(5, PieceEffects.Null, 3, false, "images/wonders/babylon/piece-back-babylon-4.png", 0, 200));
        add(new Piece(5, PieceEffects.Jeton, 3, true, "images/wonders/babylon/piece-back-babylon-5.png", 0, 200));
        add(new Piece(7, PieceEffects.Null, 4, false, "images/wonders/babylon/piece-back-babylon-6.png", 0, 200));
    }};
    private static Wonder wonder = Wonder.Babylone;

    public Babylon(){
        super(wonder, pieces);
    }

    @Override
    public void constructWonder(FlowPane root) {

    }
}
