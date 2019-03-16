package src;

public class Item {
	private String aDescription;
	private int aPoids;
	private int aPrix;

	public Item(String description,int poids,int prix){
		this.aDescription = description;
		this.aPoids = poids;
		this.aPrix = prix;
	}

	public void setDescription(String description){
		this.aDescription = description;
	}

	public void setPoids(int poids){
		this.aPoids = poids;
	}

	public void setPrix(int prix){
		this.aPrix = prix;
	}

	public String getDescription(){
		return this.aDescription;
	}

	public double getPoids(){
		return this.aPoids;
	}

	public double getPrix(){
		return this.aPrix;
	}


}