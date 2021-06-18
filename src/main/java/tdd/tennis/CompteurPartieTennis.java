package tdd.tennis;

/**
 * Cette classe contient tout le code permettant de calculer les points au tennis.
 * @author Killian
 *
 */
public class CompteurPartieTennis {

    /**
     * Cette Methode permet de crï¿½er une nouvelle partie de tennis.
     * Une partie contient 2 joueurs et 2 Scores
     * Au debut de la partie, les score sont a 0.
     * @param joueur1 premier joueur de la partie
     * @param joueur2 deuxieme joueur de la partie
     * @return une nouvelle partie.
     */
    public PartieDeTennis nouvellePartie(JoueurDeTennis joueur1, JoueurDeTennis joueur2) {
    	if (joueur2==null || joueur1==null) {
    		throw new RuntimeException("Les joueurs ne peuvent pas etre nulls");
    	}
    	if(joueur1==joueur2) {
    		throw new RuntimeException("Les deux joueurs ne peuvent pas etre les même");
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
    	if (partie.getGagnantDeLaPartie()!=null) {
    		throw new RuntimeException("La partie est fini!");
    	}
    	ScoreTennis[] scores = trouverGagnantPerdant(partie, gagnant);
    	if (partie.isJeuxDecisif())
    		gagnePointDansJeuxDecisif(scores[0], scores[1], partie);
    	else 
    		gagneLePoint(scores[0], scores[1]);
		gestionDesJeux(scores[0], scores[1], partie);
		if (scores[0].getSet()==2) {
			partie.setGagnantDeLaPartie(gagnant);
		}
       return partie;
    }
    
    private ScoreTennis[] trouverGagnantPerdant(PartieDeTennis partie, JoueurDeTennis gagnant) {
    	ScoreTennis scoreGagnant, scorePerdant;
    	if (gagnant == partie.getJoueur1()) {
    		scoreGagnant = partie.getScoreJoueur1();
    		scorePerdant = partie.getScoreJoueur2();
    	} else if(gagnant == partie.getJoueur2()) {
    		scoreGagnant = partie.getScoreJoueur2();
    		scorePerdant = partie.getScoreJoueur1();
    	} else {
    		throw new RuntimeException("Le gagnant n'a rien a faire ici!!");
    	}
    	return new ScoreTennis[] {scoreGagnant, scorePerdant};
    }
    
    private void gagnePointDansJeuxDecisif(ScoreTennis scoreGagnant, ScoreTennis scorePerdant, PartieDeTennis partie) {
    	scoreGagnant.setPoint(scoreGagnant.getPoint()+1);
    	if (scoreGagnant.getPoint()>6 && scoreGagnant.getPoint() >= scorePerdant.getPoint()+2) {
    		scoreGagnant.setSet(scoreGagnant.getSet()+1);
    		scoreGagnant.setJeux(0);
    		scoreGagnant.setPoint(0);
    		scorePerdant.setJeux(0);
    		scorePerdant.setPoint(0);
    		partie.setJeuxDecisif(false);
    	}
    }

	private void gagneLePoint(ScoreTennis scoreGagnant, ScoreTennis scorePerdant) {
		if (scoreGagnant.getPoint()==0) {
			scoreGagnant.setPoint(15);
    	} else if( scoreGagnant.getPoint() == 15) {
    		scoreGagnant.setPoint(30);
    	} else if(scoreGagnant.getPoint() == 30){
    		scoreGagnant.setPoint(40);
    	} else if (scorePerdant.getPoint() != 40) {
    		gagneLeJeu(scoreGagnant, scorePerdant);
    	} else {
    		casEgualite40Point(scoreGagnant, scorePerdant);
    	}	
	}

	private void casEgualite40Point(ScoreTennis scoreGagnant, ScoreTennis scorePerdant) {
		if(scorePerdant.isAvantage()){
			scorePerdant.setAvantage(false);
		} else if(!scorePerdant.isAvantage() && !scoreGagnant.isAvantage()){
			scoreGagnant.setAvantage(true);
		} else if(scoreGagnant.isAvantage()){
			gagneLeJeu(scoreGagnant, scorePerdant);
		}
	}

	private void gagneLeJeu(ScoreTennis scoreGagnant, ScoreTennis scorePerdant) {
		scoreGagnant.setPoint(0);
		scorePerdant.setPoint(0);
		scoreGagnant.setAvantage(false);
		scoreGagnant.setJeux(scoreGagnant.getJeux()+1);
	}

	private void gestionDesJeux(ScoreTennis scoreGagnant, ScoreTennis scorePerdant, PartieDeTennis partie) {
		if (scoreGagnant.getJeux() >= 7 && scoreGagnant.getJeux() >= scorePerdant.getJeux() +2) {
			scoreGagnant.setSet(scoreGagnant.getSet()+1);
			scoreGagnant.setJeux(0);
			scorePerdant.setJeux(0);
		}else if (scoreGagnant.getJeux()==6 && scorePerdant.getJeux()==6) {
			partie.setJeuxDecisif(true);
		}
	}

}
