package edu.mum.ea.imdb.model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import edu.mum.ea.imdb.model.Character;

@Entity
public class Movie {
	public enum Genre {
		ACTION, ADVENTURE, HORROR, ROMANCE, COMEDY, DRAMA
	};

	@Id
	@GeneratedValue
	int id;

	private String name;
	private Genre genre;
	private double rating;
	private int year;
	
	@Lob
	private byte[] poster;
	@Column(columnDefinition="text")
	private String summary;

	@OneToMany(mappedBy = "movie", cascade = { CascadeType.ALL })
	private Set<Character> characters = new HashSet<Character>();

	@ManyToMany(cascade = { CascadeType.ALL })
	@JoinTable(name = "MOVIES_DIRECTORS", joinColumns = @JoinColumn(name = "director_id"), inverseJoinColumns = @JoinColumn(name = "movie_id"))
	private Set<Director> directors = new HashSet<Director>();

	@OneToMany(cascade = { CascadeType.ALL })
	@JoinColumn(name = "comment_id")
	private List<Comment> comments = new ArrayList<Comment>();

	public Movie() {
	}

	public Movie(String name) {
		setName(name);
	}

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

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public void addComment(User user, String comment) {
		comments.add(new Comment(user, comment));
	}
}
