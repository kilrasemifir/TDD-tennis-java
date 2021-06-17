package tdd.tennis;

/**
 * Repr�sente le score d'un joueur de tennis.
 * @author Killian
 *
 */
public class ScoreTennis {
	private int point;
	private int jeux;
	private int set;
	private int match;

	public int getJeux() {
		return jeux;
	}

	public int getSet() {
		return set;
	}

	public int getMatch() {
		return match;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public void setScore(int point, int jeux, int set, int match){
		this.point = point;
		this.jeux = jeux;
		this.set = set;
		this.match = match;
	}
}
