package Model;

public class Pet {
	private String name;
	private String description;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Pet(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Pet [name=" + name + ", description=" + description + "]";
	}
	
	
}
