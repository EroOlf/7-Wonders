package com.example.the7wonders.domain.game;

import com.example.the7wonders.domain.wonder.Wonder;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Game {
    private static Game context;
    private static Table table = Table.getTable();
    private static int nbPlayers;
    private static List<Player> players = new ArrayList<>();

    private Game(){}

    public static void play() throws UnsupportedAudioFileException, IOException, LineUnavailableException{
        context = new Game();
            File mp3File = new File("7Wondersmusic.wav");
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(mp3File);

            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public static Game getContext(){
        return  context;
    }

    public void setNbPlayers(int number){
        nbPlayers = number;
    }
    public int getNbPlayers(){ return nbPlayers;}

    public List<Player> getPlayers(){
        return players;
    }

    public Table getTable(){
        return table;
    }

    public void createPlayers(String name, Wonder wonder){
        try{
            players.add(new Player(name, wonder));
        } catch(Exception e){
            System.out.println("Pb avec le player");
        }
    }

    public void associateNeighbors(){
        for(int i = 0; i < players.size(); ++i){
            players.get(i).setVoisinDroite(i + 1 < players.size() ? players.get(i+1) : players.get(0));
            players.get(i).setVoisinGauche(i == 0 ? players.get(players.size() - 1) : players.get(i - 1));
        }
    }

    public void shuffleCentralDeck(){
        Collections.shuffle(table.getCentralDeck());
    }


    public void shuffleDeck(Player p){
        // Mélanger : le deck du player, le deck de son voisin de gauche, faire celle de la pioche central à part, au tout début
        // Si on mélange le deck de tout le monde, pas besoin de le faire pour le voisin de gauche
         Collections.shuffle(p.getWonderDeck());
    }
}
