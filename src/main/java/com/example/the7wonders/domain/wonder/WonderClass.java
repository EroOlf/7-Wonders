package com.example.the7wonders.domain.wonder;


import com.example.the7wonders.domain.cards.*;
import com.example.the7wonders.domain.game.Player;
import javafx.scene.layout.FlowPane;

import java.util.HashMap;
import java.util.List;

public abstract class WonderClass {
    private Wonder wonder;
    private List<Piece> pieces;

    public WonderClass(Wonder wonder, List<Piece> pieces){
        this.wonder = wonder;
        this.pieces = pieces;
    }

    public Wonder getWonder() {
        return wonder;
    }

    public List<Piece> getPieces(){
        return pieces;
    }

    public String getName(){
        return wonder.frenchName;
    }


    public abstract void constructWonder(FlowPane root);

    public List<CardType> getDeck(){
        return wonder.getDeck();
    }

    /**
     * Vérifie si on peut retourner une piece d'une merveille
     * @param cards : les cartes possédées par le joueur
     *
     */
    public String returnPiece(Player pl){
        for(Piece p : this.getPieces()){
            if(p.constructPiece(pl.getMaterials(), pl)){
                return p.getPathImage();
            }
        }
        return "";
    }

}
