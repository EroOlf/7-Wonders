package com.example.the7wonders.tests;
import com.example.the7wonders.domain.game.Player;
import com.example.the7wonders.domain.wonder.*;
import org.junit.Test;


import static org.junit.Assert.*;
public class TestInit {
    @Test
    /**
     * Teste l'initialisation du joueur
     */
    public void TestInitJoueur(){
        WonderClass w1 = new Alexandrie();
        Player p1;
        WonderClass w2 = new Giseh();
        Player p2;
        try{
            p1 = new Player("Toto", w1);
            p2 = new Player("Titi", w2);

        }catch(Exception e){
            System.out.println("Problème lors de l'initialisation du player");
            return;
        }
        assertEquals(p1.getName(), "Toto");
        assertEquals(p1.getWonder(), w1);
        assertEquals(p2.getName(), "Titi");
        assertEquals(p2.getWonder(), w2);

    }

    @Test
    /**
     * Teste l'initialisation des merveilles
     */
    public void TestWonders(){
        //Vérifie l'implémentation
        WonderClass w1 = new Babylon();
        WonderClass w2 = new Alexandrie();
        WonderClass w3 = new Ephese();
        WonderClass w4 = new Halicarnasse();
        WonderClass w5 = new Olympie();
        WonderClass w6 = new Rhodes();
        WonderClass w7 = new Giseh();
        assertEquals(w1.getWonder(), Wonder.Babylone);
        assertEquals(w2.getWonder(), Wonder.Alexandrie);
        assertEquals(w3.getWonder(), Wonder.Ephese);
        assertEquals(w4.getWonder(), Wonder.Halicarnasse);
        assertEquals(w5.getWonder(), Wonder.Olympie);
        assertEquals(w6.getWonder(), Wonder.Rhodes);
        assertEquals(w7.getWonder(), Wonder.Gizeh);
        // Vérifie les cartes
        assertEquals(w1.getDeck(), Wonder.Babylone.getDeck());
        assertEquals(w2.getDeck(), Wonder.Alexandrie.getDeck());
        assertEquals(w3.getDeck(), Wonder.Ephese.getDeck());
        assertEquals(w4.getDeck(), Wonder.Halicarnasse.getDeck());
        assertEquals(w5.getDeck(), Wonder.Olympie.getDeck());
        assertEquals(w6.getDeck(), Wonder.Rhodes.getDeck());
        assertEquals(w7.getDeck(), Wonder.Gizeh.getDeck());
    }
}
