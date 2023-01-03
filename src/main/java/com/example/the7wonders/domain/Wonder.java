package com.example.the7wonders.domain;

public enum Wonder {

	Alexandrie("Alexandrie", "Alexandrie", //
			"Prenez la premi�re carte d'une pioche au choix, n'importe o� sur la table, et posez-la devant vous", "images/wonders/alexandrie/"),
	
	Halicarnasse("Halicarnasse", "Halicarnasse", //
			"Prenez les 5 premi�res cartes de la pioche � votre gauche ou � votre droite, choisissez-en 1 et posez-la devant vous" //
			+ "M�langez-les cartes restantes dans leur pioche", "images/wonders/halicarnasse/"),
	
	Ephese("Ephese", "Ephese", //
			"Prenez la premi�re carte de la pioche centrale et posez-la devant vous", "images/wonders/ephese/"),
	
	Olympie("Olympie", "Olympie", //
			"Prenez la premi�re carte de la pioche � votre cauche et de celle � votre droite, et posez-les devant vous", "images/wonders/olympie/"),
	
	Babylone("Babylone", "Babylone", //
			"Choisissez 1 jeton Progr�s parmi les 4 disponibles, et posez-le devant vous", "images/wonders/babylon/"),
	
	Rhodes("Rhodes", "Rhodes", //
			"Ajoutez 1 Bouclier � votre total de Boucliers", "images/wonders/rhodes/"),
	
	Gizeh("Gizeh", "Gizeh", //
			"Cette merveille n'a pas d'effet particulier, mais rapporte plus de points de victoire que les autres Merveilles", "images/wonders/giseh/");
	
	// ------------------------------------------------------------------------
	
	public final String displayName;
	
	public final String frenchName;
	
	public final String effectDescription;

	private final String pathImage;
	
	// ------------------------------------------------------------------------
	
	Wonder(String displayName, String frenchName, String effectDescription, String pathImage) {
		this.displayName = displayName;
		this.frenchName = frenchName;
		this.effectDescription = effectDescription;
		this.pathImage = pathImage;
	}

	public String getPathImage() {
		return pathImage;
	}
}
