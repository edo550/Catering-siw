package it.uniroma3.siw.catering.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;


@Entity
public class Buffet {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotBlank
	private String name;
	@NotBlank
	private String description;

	@ManyToOne
	private Chef chef;

	@ManyToMany
	private List<Dish> offeredDishes;

	
	
	
	public Buffet(Long id,  String name,  String description, Chef chef, List<Dish> offeredDishes) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.chef = chef;
		this.offeredDishes = offeredDishes;
	}

	public Buffet() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public Chef getChef() {
		return chef;
	}

	public void setChef(Chef chef) {
		this.chef = chef;
	}

	public List<Dish> getOfferedDishes() {
		return offeredDishes;
	}

	public void setOfferedDishes(List<Dish> offeredDishes) {
		this.offeredDishes = offeredDishes;
	}
	
	

}