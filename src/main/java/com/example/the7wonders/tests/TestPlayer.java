package com.example.the7wonders.tests;

import com.example.the7wonders.domain.cards.Material;
import com.example.the7wonders.domain.cards.ScienceCategory;
import com.example.the7wonders.domain.game.Player;
import com.example.the7wonders.domain.wonder.Alexandrie;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class TestPlayer {

    public Player intialization(){
        Player p;
        try{
            p = new Player("Toto", new Alexandrie());
        }catch(Exception e){
            System.out.println("Problème lors de l'initialisation du player");
            return null;
        }
        return p;
    }

    @Test
    public void testSetMaterial(){
        Player p = intialization();
        p.setMaterials(Material.Gold, 1);
        p.setMaterials(Material.Brick, 2);
        p.setMaterials(Material.Paper, 5);
        p.setMaterials(Material.Paper, -1);
        HashMap<Material, Integer> hM = p.getMaterials();
        assertEquals((int) hM.get(Material.Gold), 1);
        assertEquals((int) hM.get(Material.Brick), 2);
        assertEquals((int) hM.get(Material.Paper), 4);
    }

    @Test
    public void testSetScienceTokens(){
        Player p = intialization();
        p.setScienceTokens(ScienceCategory.Mechanic, 1);
        p.setScienceTokens(ScienceCategory.Architect, 2);
        p.setScienceTokens(ScienceCategory.Law, 3);
        p.setScienceTokens(ScienceCategory.Law, -1);
        HashMap<ScienceCategory, Integer> hM = p.getScienceTokens();
        assertEquals((int) hM.get(ScienceCategory.Mechanic), 1);
        assertEquals((int) hM.get(ScienceCategory.Architect), 2);
        assertEquals((int) hM.get(ScienceCategory.Law), 2);
    }

    @Test
    public void testChooseToken(){
        Player p = intialization();
        p.setScienceTokens(ScienceCategory.Architect, 2);
        // Choix token scientifique si 2 tokens identiques
        assertTrue(p.couldChooseToken());
        p.setScienceTokens(ScienceCategory.Architect, 1);
        p.setScienceTokens(ScienceCategory.Law, 1);
        p.setScienceTokens(ScienceCategory.Mechanic, 1);
        //
        HashMap<ScienceCategory, Integer> hM = p.getScienceTokens();
        assertEquals((int) hM.get(ScienceCategory.Mechanic), 1);
        assertEquals((int) hM.get(ScienceCategory.Architect), 1);
        assertEquals((int) hM.get(ScienceCategory.Law), 1);
        // Choix token scientifique si 3 tokens différents
        assertTrue(p.couldChooseToken());
    }
}
