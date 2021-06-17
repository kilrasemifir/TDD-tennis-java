package tdd.tennis;
/**
 * Cette classe contient tout le code permettant de calculer les points au tennis.
 * @author Killian
 *
 */
public class CompteurPartieTennis {

    /**
     * Cette Methode permet de cr�er une nouvelle partie de tennis.
     * Une partie contient 2 joueurs et 2 Scores
     * Au debut de la partie, les score sont a 0.
     * @param joueur1 premier joueur de la partie
     * @param joueur2 deuxieme joueur de la partie
     * @return une nouvelle partie.
     */
    public PartieDeTennis nouvellePartie(JoueurDeTennis joueur1, JoueurDeTennis joueur2) {

        if(joueur1 == joueur2){
            throw new RuntimeException("Les joueurs doivent etre differents!");
        }
        if (joueur1==null || joueur2==null){
            throw new RuntimeException("Les joueurs ne doivent pas etre nulls");
        }
        return new PartieDeTennis(joueur1, joueur2);
    }

    /**
     * Cette methode doit etre appeler lors ce qu'un joueur gagne un point.
     * @param partie qui est jouer.
     * @param gagnant Joueur qui a gagner.
     * @return La partie avec les nouveaux scores.
     */
    public PartieDeTennis joueurGagne(PartieDeTennis partie, JoueurDeTennis gagnant) {
        ScoreTennis scoreDuGagnant = trouverScoreGagnant(partie, gagnant);
        managePoint(scoreDuGagnant);
        return partie;
    }

    private ScoreTennis trouverScoreGagnant(PartieDeTennis partie, JoueurDeTennis gagnant) {
        ScoreTennis scoreDuGagnant;
        if (gagnant == partie.getJoueur1())
            scoreDuGagnant = partie.getScoreJoueur1();
        else
            scoreDuGagnant = partie.getScoreJoueur2();
        return scoreDuGagnant;
    }

    private void managePoint(ScoreTennis scoreDuGagnant) {
        if(scoreDuGagnant.getPoint()==0){
            scoreDuGagnant.setPoint(15);
        }else if(scoreDuGagnant.getPoint()==15){
            scoreDuGagnant.setPoint(30);
        } else {
            scoreDuGagnant.setPoint(40);
        }
    }
}
