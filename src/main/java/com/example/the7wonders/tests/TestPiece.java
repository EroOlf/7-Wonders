package com.example.the7wonders.tests;

import com.example.the7wonders.domain.cards.Material;
import com.example.the7wonders.domain.game.Player;
import com.example.the7wonders.domain.wonder.Alexandrie;
import com.example.the7wonders.domain.wonder.WonderClass;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class TestPiece {

    @Test
    public void testConstructPieceAlexandrie(){
        Player p;
        WonderClass w1= new Alexandrie();
        try{
            p = new Player("Toto", w1);
        }catch(Exception e){
            System.out.println("Problème lors de l'initialisation du player");
            return;
        }
        HashMap<Material, Integer> hM = p.getMaterials();
        p.setMaterials(Material.Brick, 1);
        p.setMaterials(Material.Glass, 1);
        assertTrue(w1.getPieces().get(0).constructPiece(p.getMaterials(), p));

        //assertEquals((int) hM.get(Material.Brick), 0);


        p.setMaterials(Material.Brick, 1);
        p.setMaterials(Material.Glass, 1);
        p.setMaterials(Material.Paper, 1);
        assertTrue(w1.getPieces().get(1).constructPiece(p.getMaterials(), p));
        p.setMaterials(Material.Brick, 3);
        assertTrue(w1.getPieces().get(2).constructPiece(p.getMaterials(), p));
        p.setMaterials(Material.Brick, 2);
        assertTrue(w1.getPieces().get(3).constructPiece(p.getMaterials(), p));
    }
}
