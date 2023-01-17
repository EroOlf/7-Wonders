package com.example.the7wonders.domain.game;

import com.example.the7wonders.domain.cards.*;
import com.example.the7wonders.domain.wonder.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Player {
    private String name;
    private Player voisinDroite;
    private Player voisinGauche;
    private int laurelCount = 0;
    private int shieldCount = 0;
    HashMap<Material, Integer> materials;
    private List<CardType> cards;
    // A remplacer par WonderClass et donc suppr wonderDeck
    private WonderClass wonder;
    //private List<CardType> wonderDeck;

    public Player(String name, WonderClass wonder) throws Exception {
        this.name = name;
        this.voisinDroite = null;
        this.voisinGauche = null;
        this.cards = new ArrayList<>();
        cards.add(CardType.CardMaterialWood);
        cards.add(CardType.CardScienceLaw);
        this.wonder = wonder;
        this.materials = new HashMap<>();
        materials.put(Material.Brick, 0);
        materials.put(Material.Glass, 0);
        materials.put(Material.Gold, 0);
        materials.put(Material.Paper, 0);
        materials.put(Material.Stone, 0);
        materials.put(Material.Wood, 0);
        //initializeWonderDeck();
    }

    public WonderClass getWonder() {
        return wonder;
    }

    public void setVoisinDroite(Player p){
        voisinDroite = p;
    }

    public void setVoisinGauche(Player p){
        voisinGauche = p;
    }

    public Player getVoisinGauche(){
        return voisinGauche;
    }

    public List<CardType> getWonderDeck(){
        return wonder.getDeck();
    }

    public void setLaurelCount(int laurel){
        this.laurelCount = laurel;
    }

    public void setShieldCount(int shield){
        this.shieldCount = shield;
    }

    public void setMaterials(Material mat, int nbMaterial){
        int oldVal = this.materials.get(mat);
        this.materials.replace(mat, oldVal+nbMaterial);
    }

    public HashMap<Material, Integer> getMaterials() {
        return materials;
    }
}
