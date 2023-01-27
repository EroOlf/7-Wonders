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

    /**
     * Vérifie si un player peut construire une pièce de sa merveille
     * @param materials : les matériaux du player
     * @param pl : le player
     * @return true si une pièce peut être construite, false sinon
     */
    public boolean constructPiece(HashMap<Material, Integer> materials, Player pl){
        if(mustBeIdentical){
            Iterator it = materials.entrySet().iterator();
            while(it.hasNext()){
                Map.Entry<Material, Integer> entry = (Map.Entry)it.next();
                int resourcePlayers = entry.getValue();
                if(nbRessources >= resourcePlayers && entry.getValue() >= nbRessources){
                    // Construire la carte

                    // Supprimer les matériaux des players
                    pl.setMaterials(entry.getKey(), -nbRessources);
                    //System.out.println(entry.setValue(resourcePlayers - nbRessources));

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
                while(it2.hasNext() && nbRessources > resourcesDiff){
                    Map.Entry<Material, Integer> entry2 = (Map.Entry)it2.next();
                    if(entry2.getValue() > 0){
                        pl.setMaterials(entry2.getKey(),  -1);
                        resourcesDiff++;
                    }
                }
                return true;
            }
        }
        return false;
    }
}
