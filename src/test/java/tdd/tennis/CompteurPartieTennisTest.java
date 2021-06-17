package tdd.tennis;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CompteurPartieTennisTest {

	private CompteurPartieTennis compteur = new CompteurPartieTennis();
	private JoueurDeTennis joueur1 = new JoueurDeTennis();
	private JoueurDeTennis joueur2 = new JoueurDeTennis();
	
	@Test
	@DisplayName("Creation d'une partie avec 2 joueurs")
	void testCreationPartieAvec2Joueurs() {
		PartieDeTennis partie = compteur.nouvellePartie(joueur1, joueur2);
		assertNotNull(partie);
	}

}
