package com.example.the7wonders.domain.game;

import com.example.the7wonders.domain.cards.*;
import com.example.the7wonders.domain.wonder.*;

import java.util.*;

public class Player {

    private String name;
    private Player voisinDroite;
    private Player voisinGauche;
    private int laurelCount = 0;
    private int shieldCount = 0;
    HashMap<Material, Integer> materials;
    HashMap<ScienceCategory, Integer> scienceTokens;
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
        initMaterials();
        initScience();
    }

    private void initScience(){
        this.scienceTokens = new HashMap<>();
        scienceTokens.put(ScienceCategory.Architect, 0);
        scienceTokens.put(ScienceCategory.Law, 0);
        scienceTokens.put(ScienceCategory.Mechanic, 0);
    }

    private void initMaterials(){
        this.materials = new HashMap<>();
        materials.put(Material.Brick, 0);
        materials.put(Material.Glass, 0);
        materials.put(Material.Gold, 0);
        materials.put(Material.Paper, 0);
        materials.put(Material.Stone, 0);
        materials.put(Material.Wood, 0);
    }

    public String getName(){
        return name;
    }

    public WonderClass getWonder() {
        return wonder;
    }

    public String getName(){
        return name;
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

    public void setScienceTokens(ScienceCategory sc, int s){
        int oldVal = this.scienceTokens.get(sc);
        this.scienceTokens.replace(sc, oldVal+s);
    }

    public HashMap<Material, Integer> getMaterials() {
        return materials;
    }

    /**
     * Vérifie si le player peut choisir un jeton de science selon les règles du jeu :
     * - S'il possède 2 symboles scientifiques identiques
     * - S'il possède 3 symboles scientifiques différents
     * @return true si le player peut choisir un token, false sinon
     */
    public boolean couldChooseToken(){
        int rscIdentical = 2;
        int rscDiff = 3;
        int rscPlayerDiff = 0;
        Iterator it = scienceTokens.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry<ScienceCategory, Integer> entry = (Map.Entry)it.next();
            if(entry.getValue() >= rscIdentical){
                // Retirer les cartes
                scienceTokens.replace(entry.getKey(), 0);
                return true;
            }else{
                if(entry.getValue() >= 1){
                    rscPlayerDiff++;
                }
            }
        }
        if(rscPlayerDiff >= rscDiff){
           setScienceTokens(ScienceCategory.Law, -1);
           setScienceTokens(ScienceCategory.Architect, -1);
           setScienceTokens(ScienceCategory.Mechanic, -1);
           return true;
        }
        return false;
    }


}
