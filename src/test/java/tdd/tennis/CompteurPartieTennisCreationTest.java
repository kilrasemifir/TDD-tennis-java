package tdd.tennis;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Creation d'une partie")
class CompteurPartieTennisCreationTest {

    private static CompteurPartieTennis compteur = new CompteurPartieTennis();
    JoueurDeTennis joueur1 = new JoueurDeTennis("A", "A");
    JoueurDeTennis joueur2 = new JoueurDeTennis("B","B");
    PartieDeTennis partie = compteur.nouvellePartie(joueur1, joueur2);

    @Test
    @DisplayName("Creation d'une partie avec deux joueurs")
    public void testCreationDunePartie(){
        PartieDeTennis partie = compteur.nouvellePartie(joueur1, joueur2);
        assertNotNull(partie);
    }

    @Test
    @DisplayName("Une partie contient deux joueurs")
    public void testCreationDunePartieContient2joueurs(){
        PartieDeTennis partie = compteur.nouvellePartie(joueur1, joueur2);
        assertEquals(joueur1, partie.getJoueur1());
        assertEquals(joueur2, partie.getJoueur2());
    }

    @Test
    @DisplayName("Les deux joueurs doivent etre differents sinon retour d'une erreur")
    public void testJoueursDifferent(){
        assertThrows(RuntimeException.class, ()->compteur.nouvellePartie(joueur1, joueur1));
    }

    @Test
    @DisplayName("Les deux joueurs ne doivent pas etre nulls")
    public void testJoueursNonNull(){
        assertThrows(RuntimeException.class, ()->compteur.nouvellePartie(null, joueur1));
    }

    @Test
    @DisplayName("Les scores sont initialements a 0 points")
    public void testJoueursPointsA0(){
        PartieDeTennis partie = compteur.nouvellePartie(joueur1, joueur2);
        assertEquals(0, partie.getScoreJoueur1().getPoint());
        assertEquals(0, partie.getScoreJoueur2().getPoint());
    }

    @Test
    @DisplayName("Les scores sont initialements a 0 jeux")
    public void testJoueursJeuxA0(){
        assertEquals(0, partie.getScoreJoueur1().getJeux());
        assertEquals(0, partie.getScoreJoueur2().getJeux());
    }

    @Test
    @DisplayName("Les scores sont initialements a 0 sets")
    public void testJoueursSetsA0(){
        assertEquals(0, partie.getScoreJoueur1().getSet());
        assertEquals(0, partie.getScoreJoueur2().getSet());
    }

    @Test
    @DisplayName("Les scores sont initialements a 0 sets")
    public void testJoueursMatchsA0(){
        assertEquals(0, partie.getScoreJoueur1().getMatch());
        assertEquals(0, partie.getScoreJoueur2().getMatch());
    }
}