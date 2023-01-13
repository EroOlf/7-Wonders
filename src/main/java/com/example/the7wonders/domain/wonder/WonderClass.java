package com.example.the7wonders.domain.wonder;


import com.example.the7wonders.Player;
import com.example.the7wonders.domain.cards.*;
import javafx.scene.layout.FlowPane;

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
    public void returnPiece(List<CardType> cards, Player p){
        int resourceWood = 0;
        int resourcePaper = 0;
        int resourceBrick = 0;
        int resourceStone = 0;
        int resourceGlass = 0;
        int resourceGold = 0;
        for(CardType c : cards){
            if(c.cardCategory == CardCategory.MaterialCard){
                /*switch(c.material){
                    case Material.Brick :

                }*/
            }
        }
    }

}
