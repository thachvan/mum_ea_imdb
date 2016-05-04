package edu.mum.ea.imdb.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "movie_character")
public class Character {
	@Id
	@GeneratedValue
	private int id;

	private String name;

	@ManyToOne
	@JoinColumn(name = "movie_id")
	private Movie movie;

	@OneToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "actor_id")
	private Actor actor;

	public Character() {
	}

	public Character(String name, Actor actor) {
		this.setName(name);
		this.setActor(actor);
		actor.addMovie(movie);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public Actor getActor() {
		return actor;
	}

	public void setActor(Actor actor) {
		this.actor = actor;
	}
}
