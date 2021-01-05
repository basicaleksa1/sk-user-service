package skprojekat.userservice.domain;

import javax.persistence.*;

@Entity
public class Rank {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	private String type;
	private int miles;
	
	public Rank() {
		
	}
	
	public Rank(String type, int miles) {
		this.type = type;
		this.miles = miles;
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
		checkType();
	}

	private void checkType() {
		if(this.getMiles() < 1000) {
			this.setType("Bronza");
		}else if(this.getMiles() < 10000) {
			this.setType("Srebro");
		}else {
			this.setType("Zlato");
		}
	}
}
