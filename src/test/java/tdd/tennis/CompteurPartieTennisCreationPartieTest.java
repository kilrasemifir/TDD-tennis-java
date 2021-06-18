package tdd.tennis;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Creation d'une nouvelle partie")
class CompteurPartieTennisCreationPartieTest {

	private static JoueurDeTennis joueur1 = new JoueurDeTennis();
	private static JoueurDeTennis joueur2 = new JoueurDeTennis();
	private static CompteurPartieTennis compteur = new CompteurPartieTennis();
	
	@Test
	@DisplayName("Avec deux joueurs")
	public void testCreationAvecDeuxJoueurs() {
		PartieDeTennis partie = compteur.nouvellePartie(joueur1, joueur2);
		assertNotNull(partie);
	}
	
	@Test
	@DisplayName("Retourne une erreur si un des joueur est null")
	public void testErreurSiJoueurNull() {
		assertThrows(RuntimeException.class, ()->compteur.nouvellePartie(joueur1, null));
		assertThrows(RuntimeException.class, ()->compteur.nouvellePartie(null, joueur2));
	}
	
	@Test
	@DisplayName("Retourne une erreur si un seul joueur participe a la partie")
	public void testErreurSiUnSeulJoueur() {
		assertThrows(RuntimeException.class, ()->compteur.nouvellePartie(joueur2, joueur2));
	}
	
	@Test
	@DisplayName("La partie contient les deux joueurs")
	public void testContientLesJoueurs() {
		PartieDeTennis partie = compteur.nouvellePartie(joueur1, joueur2);
		assertEquals(joueur1, partie.getJoueur1());
		assertEquals(joueur2, partie.getJoueur2());
	}

}
