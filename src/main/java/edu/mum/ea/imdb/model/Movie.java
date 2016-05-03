package edu.mum.ea.imdb.model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import edu.mum.ea.imdb.model.Character;

@Entity
public class Movie {
	public enum Genre {
		ACTION, ADVENTURE, HORROR, ROMANCE, COMEDY
	};

	private String name;
	private Genre genre;
	private double rating;
	private int year;

	@OneToMany(mappedBy = "movie")
	private Set<Character> characters;

	@OneToMany
	@JoinColumn(name = "director_id")
	private Set<Director> directors;

	@OneToMany
	@JoinColumn(name = "comment_id")
	private List<Comment> comments;
	private byte[] poster;
	private String summary;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public byte[] getPoster() {
		return poster;
	}

	public void setPoster(String posterFilePath) throws IOException {
		this.poster = Files.readAllBytes(Paths.get(posterFilePath));
	}

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	public void addCharacter(Character... characters) {
		for (Character character : characters) {
			this.characters.add(character);
		}
	}

	public void addDirector(Director... directors) {
		for (Director director : directors) {
			this.directors.add(director);
		}
	}
}
