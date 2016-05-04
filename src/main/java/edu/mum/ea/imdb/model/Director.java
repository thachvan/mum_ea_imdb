package edu.mum.ea.imdb.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

@Entity
public class Director extends Person {
	@ManyToMany(mappedBy = "directors")
	private List<Movie> movies;

	public Director() {
	}

	public Director(String name) {
		this.setName(name);
	}
}
