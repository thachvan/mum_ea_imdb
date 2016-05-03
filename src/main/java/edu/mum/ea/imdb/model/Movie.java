package edu.mum.ea.imdb.model;

import java.util.List;
import java.util.Map;

public class Movie {
	public enum Genre {ACTION, ADVENTURE, HORROR, ROMANCE, COMEDY};
	private String name;
	private double rating;
	private int year;
	private Map<String, Actor> characters;
	private Director director;
	private List<Comment> comments;
}
