package tdd.tennis;

/**
 * Cette classe represente une partie de tennis entre deux joueur avec leurs scores.
 * @author Killian
 *
 */
public class PartieDeTennis {

	private JoueurDeTennis gagnantDeLaPartie;
	private JoueurDeTennis joueur1;
	private JoueurDeTennis joueur2;
	
	private ScoreTennis scoreJoueur1 = new ScoreTennis();
	private ScoreTennis scoreJoueur2 = new ScoreTennis();
	
	private boolean jeuxDecisif;

	public PartieDeTennis(JoueurDeTennis joueur1, JoueurDeTennis joueur2) {
		this.joueur1 = joueur1;
		this.joueur2 = joueur2;
	}
	
	

	
	public JoueurDeTennis getGagnantDeLaPartie() {
		return gagnantDeLaPartie;
	}




	public void setGagnantDeLaPartie(JoueurDeTennis gagnantDeLaPartie) {
		this.gagnantDeLaPartie = gagnantDeLaPartie;
	}




	public JoueurDeTennis getJoueur1() {
		return joueur1;
	}

	public JoueurDeTennis getJoueur2() {
		return joueur2;
	}

	public ScoreTennis getScoreJoueur1() {
		return scoreJoueur1;
	}

	public ScoreTennis getScoreJoueur2() {
		return scoreJoueur2;
	}

	public boolean isJeuxDecisif() {
		return jeuxDecisif;
	}

	public void setJeuxDecisif(boolean jeuxDecisif) {
		this.jeuxDecisif = jeuxDecisif;
	}
	
	

}
