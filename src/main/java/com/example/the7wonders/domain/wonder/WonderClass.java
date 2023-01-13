package com.example.the7wonders.domain.wonder;


import com.example.the7wonders.domain.cards.CardType;
import javafx.scene.layout.FlowPane;

import java.util.List;

public abstract class WonderClass {
    private Wonder wonder;


    /*public WonderClass(Wonder wonder){
        this.wonder = wonder;
    }*/


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

}
