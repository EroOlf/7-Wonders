package com.example.the7wonders.domain.wonder;

import com.example.the7wonders.domain.cards.Material;
import com.example.the7wonders.domain.game.Player;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Piece {
    private int nbGloire;
    private PieceEffects effet;

    private int nbRessources;
    private boolean mustBeIdentical;

    private String pathImage;
    private int posX;
    private int posY;

    public Piece(int nbG, PieceEffects effet , int nbR, boolean mustBeIdentical, String pathImage, int posX, int posY){
        this.nbGloire = nbG;
        this.effet = effet;
        this.nbRessources= nbR;
        this.mustBeIdentical = mustBeIdentical;
        this.pathImage = pathImage;
        this.posX = posX;
        this.posY = posY;
    }

    public String getPathImage() {
        return pathImage;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY(){
        return posY;
    }

    public int getNbGloire() {
        return nbGloire;
    }

    public boolean constructPiece(HashMap<Material, Integer> materials, Player pl){
        if(mustBeIdentical){
            Iterator it = materials.entrySet().iterator();
            while(it.hasNext()){
                Map.Entry<Material, Integer> entry = (Map.Entry)it.next();
                int resourcePlayers = entry.getValue();
                if(nbRessources >= resourcePlayers){
                    // Construire la carte
                    // Supprimer les matériaux des players
                    pl.setMaterials(entry.getKey(), entry.setValue(resourcePlayers - nbRessources));
                    pl.setLaurelCount(nbGloire);
                    return true;
                }
            }
        }
        else{
            Iterator it = materials.entrySet().iterator();
            int resourcesDiff = 0;
            while(it.hasNext()){
                Map.Entry<Integer, Integer> entry = (Map.Entry)it.next();
                if(entry.getValue() > 0){
                    resourcesDiff++;
                }
            }
            if(nbRessources >= resourcesDiff){
                // Construire la carte
                // Supprimer les matériaux des players
                pl.setLaurelCount(nbGloire);
                Iterator it2 = materials.entrySet().iterator();
                resourcesDiff = 0;
                while(it.hasNext() && nbRessources > resourcesDiff){
                    Map.Entry<Material, Integer> entry2 = (Map.Entry)it.next();
                    pl.setMaterials(entry2.getKey(), entry2.getValue() -  1);
                    resourcesDiff++;
                }
                return true;
            }
        }
        return false;
    }
}
