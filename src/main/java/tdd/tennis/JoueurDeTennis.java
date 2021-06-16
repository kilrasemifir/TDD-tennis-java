package tdd.tennis;

/**
 * Represente un joueur de tennis.
 * Un même joueur peut participer a plusieur partie.
 * @author Killian
 *
 */
public class JoueurDeTennis {

	private String nom;
	private String prenom;
	
	
	
	public JoueurDeTennis() {
		this("","");
	}

	public JoueurDeTennis(String nom, String prenom) {
		super();
		this.nom = nom;
		this.prenom = prenom;
	}
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
	
}
