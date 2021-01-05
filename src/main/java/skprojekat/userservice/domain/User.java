package skprojekat.userservice.domain;

import java.util.List;

import javax.persistence.*;

@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(length = 20, nullable = false)
	private String first_name;	
	@Column(length = 20, nullable = false)
	private String last_name;
	@Column(length = 50, unique = true, nullable = false)
	private String email;
	@Column(nullable = false)
	private String password;
	@Column(length = 20, unique = true, nullable = false)
	private int passport;
    @ManyToOne(optional = false)
	private Role role;
    @ManyToOne(optional = true)
    private CreditCard credit_cards;
	@ManyToOne(optional = true)
	private Rank rank;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getPassport() {
		return passport;
	}
	public void setPassport(int passport) {
		this.passport = passport;
	}
	public CreditCard getCredit_cards() {
		return credit_cards;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public void setCredit_cards(CreditCard credit_cards) {
		this.credit_cards = credit_cards;
	}
	public Rank getRank() {
		return rank;
	}
	public void setRank(Rank rank) {
		this.rank = rank;
	}

}
