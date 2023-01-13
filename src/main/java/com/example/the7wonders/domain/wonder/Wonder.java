package com.example.the7wonders.domain.wonder;

import com.example.the7wonders.domain.cards.*;

import java.util.ArrayList;
import java.util.List;

public enum Wonder {

	Alexandrie("Alexandrie", "Alexandrie", //
			"Prenez la premi�re carte d'une pioche au choix, n'importe o� sur la table, et posez-la devant vous", "images/wonders/alexandrie/", CardDecks.deckCardQuantities_Alexandrie),
	
	Halicarnasse("Halicarnasse", "Halicarnasse", //
			"Prenez les 5 premi�res cartes de la pioche � votre gauche ou � votre droite, choisissez-en 1 et posez-la devant vous" //
			+ "M�langez-les cartes restantes dans leur pioche", "images/wonders/halicarnasse/", CardDecks.deckCardQuantities_Halicarnasse),
	
	Ephese("Ephese", "Ephese", //
			"Prenez la premi�re carte de la pioche centrale et posez-la devant vous", "images/wonders/ephese/", CardDecks.deckCardQuantities_Ephese),
	
	Olympie("Olympie", "Olympie", //
			"Prenez la premi�re carte de la pioche � votre cauche et de celle � votre droite, et posez-les devant vous", "images/wonders/olympie/", CardDecks.deckCardQuantities_Olympie),
	
	Babylone("Babylone", "Babylone", //
			"Choisissez 1 jeton Progr�s parmi les 4 disponibles, et posez-le devant vous", "images/wonders/babylon/", CardDecks.deckCardQuantities_Babylon),
	
	Rhodes("Rhodes", "Rhodes", //
			"Ajoutez 1 Bouclier � votre total de Boucliers", "images/wonders/rhodes/", CardDecks.deckCardQuantities_Rhodes),
	
	Gizeh("Gizeh", "Gizeh", //
			"Cette merveille n'a pas d'effet particulier, mais rapporte plus de points de victoire que les autres Merveilles", "images/wonders/giseh/", CardDecks.deckCardQuantities_Gizeh);
	
	// ------------------------------------------------------------------------
	
	public final String displayName;
	
	public final String frenchName;
	
	public final String effectDescription;

	private final String pathImage;

	private List<CardType> deck;
	
	// ------------------------------------------------------------------------
	
	Wonder(String displayName, String frenchName, String effectDescription, String pathImage, List<CardDecks.CardTypeQuantity> deckTemp) {
		this.displayName = displayName;
		this.frenchName = frenchName;
		this.effectDescription = effectDescription;
		this.pathImage = pathImage;
		this.deck = new ArrayList<>();
		cardsListToCards(deckTemp);
	}

	public String getPathImage() {
		return pathImage;
	}

	public List<CardType> getDeck() {
		return deck;
	}

	private void cardsListToCards(List<CardDecks.CardTypeQuantity> deckTemp){
		for(CardDecks.CardTypeQuantity c : deckTemp){
			for(int i = 0; i < c.quantity; ++i){
				deck.add(c.cardType);
			}
		}
	}
}
