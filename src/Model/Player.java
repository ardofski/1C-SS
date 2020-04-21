package Model;

public class Player {
	private Character character;
	private int score;
	public Player(){
		this.score =0;	
	}
	public Character getCharacter() {
		return character;
	}
	public void setCharacter(Character character) {
		this.character = character;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	@Override
	public String toString() {
		return "Player [character=" + character + ", score=" + score + "]";
	}
	
}