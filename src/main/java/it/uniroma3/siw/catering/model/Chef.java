package it.uniroma3.siw.catering.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;


@Entity
@Table(uniqueConstraints = { @UniqueConstraint(name = "UniqueNameAndNationality", columnNames = { "firstName",
		"lastName", "nationality" }) })
public class Chef {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotBlank
	private String firstName;
	@NotBlank
	private String lastName;
	@NotBlank
	private String nationality;

	@OneToMany(mappedBy = "chef")
	private List<Buffet> offeredBuffets;

	/*
	 * @ElementCollection
	 * 
	 * @NotEmpty private List<Long> offeredBuffets;
	 */
	
	@Override
	public String toString() {
		return id + " " + firstName + " " + lastName + ", " + nationality;
	}

	public Chef(Long id, String firstName, String lastName, String nationality,
			List<Buffet> offeredBuffets) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.nationality = nationality;
		this.offeredBuffets = offeredBuffets;
	}

	public Chef() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public List<Buffet> getOfferedBuffets() {
		return offeredBuffets;
	}

	public void setOfferedBuffets(List<Buffet> offeredBuffets) {
		this.offeredBuffets = offeredBuffets;
	}
}