package skprojekat.userservice.domain;

import javax.persistence.*;

@Entity
public class User {
	
	@Id
	private int id;
	
	@Column
	private String name;
}
