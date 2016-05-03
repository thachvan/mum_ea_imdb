package edu.mum.ea.imdb.model;

import java.util.List;

public class Director extends Person {
	private List<Movie> movies;
	
	public Director() {
	}
	
	public Director(String name) {
		this.setName(name);
	}
}
