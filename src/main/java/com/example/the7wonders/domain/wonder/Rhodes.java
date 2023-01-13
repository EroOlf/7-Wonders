package com.example.the7wonders.domain.wonder;

import javafx.scene.layout.FlowPane;

import java.util.ArrayList;
import java.util.List;

public class Rhodes extends WonderClass{
    private static List<Piece> pieces = new ArrayList<Piece>(){{
        add(new Piece(4, PieceEffects.Bouclier, 2, true, "images/wonders/rhodes/piece-back-rhodes-1.png", 0, 0));
        add(new Piece(4, PieceEffects.Null, 2, false, "images/wonders/rhodes/piece-back-rhodes-2.png", 0, 100));
        add(new Piece(5, PieceEffects.Null,3, true, "images/wonders/rhodes/piece-back-rhodes-3.png", 100, 100));
        add(new Piece(6, PieceEffects.Bouclier, 3, true, "images/wonders/rhodes/piece-back-rhodes-4.png", 0, 200));
        add(new Piece(7, PieceEffects.Null, 4, false, "images/wonders/rhodes/piece-back-rhodes-5.png", 0, 200));
    }};
    private static Wonder wonder = Wonder.Rhodes;

    public Rhodes(){
        super(wonder, pieces);
    }

    @Override
    public void constructWonder(FlowPane root) {

    }
}
