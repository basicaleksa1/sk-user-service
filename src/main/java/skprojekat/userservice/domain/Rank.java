package skprojekat.userservice.domain;

import javax.persistence.*;

@Entity
public class Rank {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	private String type;
	private int miles;
	private int popust;
	
	public Rank() {
		
	}
	
	public Rank(String type, int miles, int popust) {
		this.type = type;
		this.miles = miles;
		this.popust = popust;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}


	public int getMiles() {
		return miles;
	}

	public void setMiles(int miles) {
		this.miles = miles;
	}

	public int getPopust() {
		return popust;
	}

	public void setPopust(int popust) {
		this.popust = popust;
	}
}
