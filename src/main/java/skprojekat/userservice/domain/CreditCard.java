package skprojekat.userservice.domain;

import javax.persistence.*;

@Entity
public class CreditCard {

	@Id
	private Integer id;
	@Column(length = 40, nullable = false)
	private String full_name;
	@Column(length = 15, nullable = false)
	private int card_num;
	@Column(length = 3, nullable = false)
	private String card_code;
	
	public String getFull_name() {
		return full_name;
	}
	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}
	public int getCard_num() {
		return card_num;
	}
	public void setCard_num(int card_num) {
		this.card_num = card_num;
	}
	public String getCard_code() {
		return card_code;
	}
	public void setCard_code(String card_code) {
		this.card_code = card_code;
	}
	
}
