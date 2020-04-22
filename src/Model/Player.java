package Model;

public class Player {
	private int score;
	private String name;

	public Player(String name){
		this.name = name;
		this.score =0;
	}

	public Player(String name, int score){
		this.name = name;
		this.score = score;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	@Override
	public String toString() {
		return "Player [name=" + name + ", score=" + score + "]";
	}
	
}