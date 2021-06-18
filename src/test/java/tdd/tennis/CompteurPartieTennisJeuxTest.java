package tdd.tennis;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("Quand un joueur gagne un jeu")
class CompteurPartieTennisJeuxTest {
	
	private static JoueurDeTennis joueur1 = new JoueurDeTennis();
	private static JoueurDeTennis joueur2 = new JoueurDeTennis();
	private static CompteurPartieTennis compteur = new CompteurPartieTennis();
	private static PartieDeTennis partie = compteur.nouvellePartie(joueur1, joueur2);
	
	@Test
	@DisplayName("Si il a i il passe a i+1 point (jusqu'a 6)")
	public void testGagneJeu(){
		partie.getScoreJoueur1().setJeux(0);
		for (int i = 1; i <= 6; i++) {
			partie.getScoreJoueur1().setPoint(40);
			compteur.joueurGagne(partie, joueur1);
			assertEquals(i, partie.getScoreJoueur1().getJeux());
		}
	}
	
	@Test
	@DisplayName("Si le gagnant a 6 jeux et le perdant moins de 4 (inclu)")
	public void testGagneJeuxAvec2JeuxAvance6A4(){
		partie.getScoreJoueur1().setJeux(6);
		partie.getScoreJoueur1().setPoint(40);
		partie.getScoreJoueur2().setJeux(4);
		compteur.joueurGagne(partie, joueur1);
		assertEquals(1, partie.getScoreJoueur1().getSet());
		assertEquals(0, partie.getScoreJoueur1().getJeux());
		assertEquals(0, partie.getScoreJoueur2().getJeux());
	}
	
	@Test
	@DisplayName("Si le gagnant a 2 jeux d'avance, alors il gagne le set")
	public void testGagneJeuxAvec2JeuxAvance(){
		partie.getScoreJoueur1().setSet(0);
		partie.getScoreJoueur1().setJeux(6);
		partie.getScoreJoueur1().setPoint(40);
		partie.getScoreJoueur2().setJeux(5);
		compteur.joueurGagne(partie, joueur1);
		assertEquals(1, partie.getScoreJoueur1().getSet());
		assertEquals(0, partie.getScoreJoueur1().getJeux());
		assertEquals(0, partie.getScoreJoueur2().getJeux());
	}
	
	@Nested
	@DisplayName("Les deux joueurs sont a 6 jeux")
	class JeuxDecisifTest {
		@Test
		@DisplayName("Alors ont passe en jeux decisif")
		public void testGagneJeuxAvec2JeuxAvance(){
			partie.getScoreJoueur1().setJeux(5);
			partie.getScoreJoueur1().setPoint(40);
			partie.getScoreJoueur2().setJeux(6);
			compteur.joueurGagne(partie, joueur1);
			assertTrue(partie.isJeuxDecisif());
		}
		
		@Test
		@DisplayName("Les points sont de 1 en 1")
		public void testGagnePointDansJeuxDecisif(){
			partie.setJeuxDecisif(true);
			partie.getScoreJoueur1().setPoint(0);
			for (int i = 1; i <= 6; i++) {
				compteur.joueurGagne(partie, joueur1);
				assertEquals(i, partie.getScoreJoueur1().getPoint());
			}
		}
		
		@Test
		@DisplayName("Si le gagnant a 2 points d'avance alors il gagne le jeux et donc le set")
		public void testGagneAvec2pointDavance(){
			partie.getScoreJoueur1().setSet(0);
			partie.setJeuxDecisif(true);
			partie.getScoreJoueur1().setPoint(6);
			partie.getScoreJoueur2().setPoint(5);
			compteur.joueurGagne(partie, joueur1);
			assertEquals(1, partie.getScoreJoueur1().getSet());
		}
	}

}
