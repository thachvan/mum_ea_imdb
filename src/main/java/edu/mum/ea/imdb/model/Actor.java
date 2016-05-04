package edu.mum.ea.imdb.model;

import java.io.IOException;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Actor extends Person {
	@ManyToMany
	@JoinTable(name = "ACTOR_MOVIE", joinColumns = @JoinColumn(name = "actor_id"), inverseJoinColumns = @JoinColumn(name = "movie_id"))
	private Set<Movie> movies;

	public Actor() {
	}

	public Actor(String name) throws IOException {
		this.setName(name);
	}

	public void addMovie(Movie movie) {
		movies.add(movie);
	}
}
