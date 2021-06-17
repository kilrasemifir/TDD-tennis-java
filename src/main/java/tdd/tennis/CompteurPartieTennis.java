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
		return null;
	}
	
	/**
	 * Cette methode doit etre appeler lors ce qu'un joueur gagne un point.
	 * @param partie qui est jouer.
	 * @param gagnant Joueur qui a gagner.
	 * @return La partie avec les nouveaux scores.
	 */
	public PartieDeTennis joueurGagne(PartieDeTennis partie, JoueurDeTennis gagnant) {
		return partie;
	}
}
