package com.example.the7wonders.domain.wonder;

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

    public boolean constructPiece(){
        return false;
    }
}
