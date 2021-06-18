package tdd.tennis;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("Quand un joueur gagne le point")
class CompteurPartieTennisPointsTest {
	
	private static JoueurDeTennis joueur1 = new JoueurDeTennis();
	private static JoueurDeTennis joueur2 = new JoueurDeTennis();
	private static CompteurPartieTennis compteur = new CompteurPartieTennis();
	private static PartieDeTennis partie = compteur.nouvellePartie(joueur1, joueur2);
	
	@Test
	@DisplayName("En debut de partie les joueurs ont 0 point")
	public void testDebutDePartieA0Point() {
		PartieDeTennis partie = compteur.nouvellePartie(joueur1, joueur2);
		assertEquals(0, partie.getScoreJoueur1().getPoint());
		assertEquals(0, partie.getScoreJoueur2().getPoint());
	}
	
	@Test
	@DisplayName("Si le gagnant a 0 point, il passe a 15 points")
	public void testPoint0A15() {
		partie.getScoreJoueur1().setPoint(0);
		compteur.joueurGagne(partie, joueur1);
		assertEquals(15, partie.getScoreJoueur1().getPoint());
		partie.getScoreJoueur2().setPoint(0);
		compteur.joueurGagne(partie, joueur2);
		assertEquals(15, partie.getScoreJoueur2().getPoint());
	}
	
	@Test
	@DisplayName("Si le gagnant a 15 point, il passe a 30 points")
	public void testPoint15A30() {
		partie.getScoreJoueur1().setPoint(15);
		compteur.joueurGagne(partie, joueur1);
		assertEquals(30, partie.getScoreJoueur1().getPoint());
		partie.getScoreJoueur2().setPoint(15);
		compteur.joueurGagne(partie, joueur2);
		assertEquals(30, partie.getScoreJoueur2().getPoint());
	}
	
	@Test
	@DisplayName("Le perdant le gagne aucun points")
	public void testPerdantAucunPoint() {
		partie.getScoreJoueur1().setPoint(0);
		partie.getScoreJoueur2().setPoint(0);
		compteur.joueurGagne(partie, joueur1);
		assertEquals(0, partie.getScoreJoueur2().getPoint());
		compteur.joueurGagne(partie, joueur2);
		assertEquals(15, partie.getScoreJoueur1().getPoint());
		compteur.joueurGagne(partie, joueur1);
		assertEquals(15, partie.getScoreJoueur2().getPoint());
		compteur.joueurGagne(partie, joueur2);
		assertEquals(30, partie.getScoreJoueur1().getPoint());
	}
	
	@Test
	@DisplayName("Si le gagnant a 30 point, il passe a 40 points")
	public void testPoint30A40() {
		partie.getScoreJoueur1().setPoint(30);
		compteur.joueurGagne(partie, joueur1);
		assertEquals(40, partie.getScoreJoueur1().getPoint());
		partie.getScoreJoueur2().setPoint(30);
		compteur.joueurGagne(partie, joueur2);
		assertEquals(40, partie.getScoreJoueur2().getPoint());
	}
	
	@Test
	@DisplayName("Erreur si le gagnant ne participe pas a la partie")
	public void testGagnantInconue() {
		assertThrows(RuntimeException.class, ()->compteur.joueurGagne(partie, new JoueurDeTennis("Patoche", "Tutu")));
	}
	
	@Nested
	@DisplayName("Les deux joueurs ont 40")
	class Egalite40PointTest {
		
		@BeforeEach
		public void before() {
			partie.getScoreJoueur1().setPoint(40);
			partie.getScoreJoueur2().setPoint(40);
		}
		
		@Test
		@DisplayName("Si aucun n'a d'avantage, alors le gagnant en gagne un")
		public void testGagneAvantage() {
			partie.getScoreJoueur1().setAvantage(false);
			partie.getScoreJoueur2().setAvantage(false);
			compteur.joueurGagne(partie, joueur1);
			assertTrue(partie.getScoreJoueur1().isAvantage());
		}
		
		@Test
		@DisplayName("Si Le perdant a un avantage, alors il le perd et le gagnant n'en gagne pas")
		public void testPerdantPerdAvantage() {
			partie.getScoreJoueur1().setAvantage(false);
			partie.getScoreJoueur2().setAvantage(true);
			compteur.joueurGagne(partie, joueur1);
			assertFalse(partie.getScoreJoueur1().isAvantage());
			assertFalse(partie.getScoreJoueur2().isAvantage());
		}
		
	}

	
	@Nested
	@DisplayName("Le joueur gagne le jeu")
	class GagneLeJeuTest {
		@Test
		@DisplayName("Si Le gagnant a un avantage, alors il gagne le jeu")
		public void testGagnantGagneAvecAvantage() {
			partie.getScoreJoueur1().setPoint(40);
			partie.getScoreJoueur2().setPoint(40);
			partie.getScoreJoueur1().setJeux(0);
			partie.getScoreJoueur1().setAvantage(true);
			partie.getScoreJoueur2().setAvantage(false);
			compteur.joueurGagne(partie, joueur1);
			assertEquals(1, partie.getScoreJoueur1().getJeux());
		}
		
		@Test
		@DisplayName("Si Le gagnant a un avantage, alors il gagne le jeu")
		public void testGagnantGagneAvecAvantageReinitialisation() {
			partie.getScoreJoueur1().setPoint(40);
			partie.getScoreJoueur2().setPoint(40);
			partie.getScoreJoueur1().setAvantage(true);
			partie.getScoreJoueur2().setAvantage(false);
			compteur.joueurGagne(partie, joueur1);
			assertEquals(0, partie.getScoreJoueur1().getPoint());
			assertEquals(0, partie.getScoreJoueur2().getPoint());
			assertFalse(partie.getScoreJoueur2().isAvantage());
			assertFalse(partie.getScoreJoueur2().isAvantage());
		}
		
		@Test
		@DisplayName("Si le gagnant a 40 point et que le perdant en a moins de 30")
		public void testGagneJeuxParAvance() {
			partie.getScoreJoueur1().setPoint(40);
			partie.getScoreJoueur2().setPoint(30);
			partie.getScoreJoueur1().setAvantage(false);
			partie.getScoreJoueur2().setAvantage(false);
			compteur.joueurGagne(partie, joueur1);
			assertEquals(1, partie.getScoreJoueur1().getJeux());
			assertEquals(0, partie.getScoreJoueur1().getPoint());
			assertEquals(0, partie.getScoreJoueur2().getPoint());
			assertFalse(partie.getScoreJoueur2().isAvantage());
			assertFalse(partie.getScoreJoueur2().isAvantage());
		}
	}
}
