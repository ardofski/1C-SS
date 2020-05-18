package Model;

/**
 * The type Player.
 */
public class Player {
    /**
     * The Score.
     */
    private int score;
    /**
     * The Name.
     */
    private String name;

    /**
     * Instantiates a new Player.
     *
     * @param name the name
     */
    public Player(String name){
		this.name = name;
		this.score =0;
	}

    /**
     * Instantiates a new Player.
     *
     * @param name  the name
     * @param score the score
     */
    public Player(String name, int score){
		this.name = name;
		this.score = score;
	}

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
		return name;
	}

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
		this.name = name;
	}

    /**
     * Gets score.
     *
     * @return the score
     */
    public int getScore() {
		return score;
	}

    /**
     * Sets score.
     *
     * @param score the score
     */
    public void setScore(int score) {
		this.score = score;
	}

    /**
     * To string string.
     *
     * @return the string
     */
    @Override
	public String toString() {
		return "Player [name=" + name + ", score=" + score + "]";
	}
	
}