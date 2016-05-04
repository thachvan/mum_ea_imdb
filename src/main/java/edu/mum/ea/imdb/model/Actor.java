package edu.mum.ea.imdb.model;

import java.io.IOException;
import javax.persistence.Entity;

@Entity
public class Actor extends Person {
	public Actor() {
	}

	public Actor(String name) throws IOException {
		this.setName(name);
	}
}
