package tdd.tennis;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Lors ce qu'un joueur gagne un set")
class CompteurPartieTennisSetsTest {

	private static JoueurDeTennis joueur1 = new JoueurDeTennis();
	private static JoueurDeTennis joueur2 = new JoueurDeTennis();
	private static CompteurPartieTennis compteur = new CompteurPartieTennis();
	private static PartieDeTennis partie = compteur.nouvellePartie(joueur1, joueur2);
	
	@Test
	@DisplayName("Si il gagne 2 sets, il gagne la partie")
	void testGagnant() {
		partie.getScoreJoueur1().setSet(1);
		partie.getScoreJoueur1().setJeux(6);
		partie.getScoreJoueur1().setPoint(40);
		compteur.joueurGagne(partie, joueur1);
		assertEquals(joueur1, partie.getGagnantDeLaPartie());
	}
	
	@Test
	@DisplayName("Il n'est plus possible de jouer apres qu'un joueur est gagné")
	void testErreurSiFini() {
		partie.setGagnantDeLaPartie(null);
		partie.getScoreJoueur1().setSet(1);
		partie.getScoreJoueur1().setJeux(6);
		partie.getScoreJoueur1().setPoint(40);
		compteur.joueurGagne(partie, joueur1);
		assertThrows(RuntimeException.class, ()->{
			compteur.joueurGagne(partie, joueur1);
		});
	}

}
