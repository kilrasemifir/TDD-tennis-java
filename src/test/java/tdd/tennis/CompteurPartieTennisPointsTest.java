package tdd.tennis;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Gestion des Points")
class CompteurPartieTennisPointsTest {

    private static CompteurPartieTennis compteur = new CompteurPartieTennis();
    private static JoueurDeTennis joueur1 = new JoueurDeTennis("A", "A");
    private static JoueurDeTennis joueur2 = new JoueurDeTennis("B","B");
    private static PartieDeTennis partie = compteur.nouvellePartie(joueur1, joueur2);

    @Test
    @DisplayName("Quand un joueur avec 0 point gagne le point, il passe a 15 et l'autre reste a son score")
    public void testGagneUnPoint(){
        partie.getScoreJoueur1().setScore(0, 0, 0, 0);
        partie.getScoreJoueur2().setScore(0, 0, 0, 0);
        compteur.joueurGagne(partie, joueur1);
        assertEquals(15, partie.getScoreJoueur1().getPoint());
        assertEquals(0, partie.getScoreJoueur2().getPoint());
        compteur.joueurGagne(partie, joueur2);
        assertEquals(15, partie.getScoreJoueur1().getPoint());
        assertEquals(15, partie.getScoreJoueur2().getPoint());
    }

    @Test
    @DisplayName("Quand un gagnant a 15 points, il passe a 30")
    public void testGagneUnPointVers30(){
        partie.getScoreJoueur1().setScore(15, 0, 0, 0);
        partie.getScoreJoueur2().setScore(15, 0, 0, 0);
        compteur.joueurGagne(partie, joueur1);
        assertEquals(30, partie.getScoreJoueur1().getPoint());
        compteur.joueurGagne(partie, joueur2);
        assertEquals(30, partie.getScoreJoueur2().getPoint());
    }

    @Test
    @DisplayName("Quand un gagnant a 30 points, il passe a 40")
    public void testGagneUnPointVers40(){
        partie.getScoreJoueur1().setScore(30, 0, 0, 0);
        partie.getScoreJoueur2().setScore(30, 0, 0, 0);
        compteur.joueurGagne(partie, joueur1);
        assertEquals(40, partie.getScoreJoueur1().getPoint());
        compteur.joueurGagne(partie, joueur2);
        assertEquals(40, partie.getScoreJoueur2().getPoint());
    }




}