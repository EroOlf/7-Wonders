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
    public void returnPiece(List<CardType> cards, WonderClass wc, Player pl){
        /*int resourceWood = 0;
        int resourcePaper = 0;
        int resourceBrick = 0;
        int resourceStone = 0;
        int resourceGlass = 0;
        int resourceGold = 0;*/
        for(Piece p : wc.getPieces()){
            if(p.constructPiece(pl.getMaterials(), pl)){
                pl.setLaurelCount(p.getNbGloire());
                //Enlever les matériaux

                // Enlever la piece

            }
        }
    }

}
