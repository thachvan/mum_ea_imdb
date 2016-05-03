package edu.mum.ea.imdb;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;

import edu.mum.ea.imdb.model.Actor;
import edu.mum.ea.imdb.model.Movie;
import edu.mum.ea.imdb.model.Movie.Genre;
import edu.mum.ea.imdb.model.Character;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		initDB();
		System.out.println("Hello World!");
	}

	public static void initDB() {
		// Actors
		Actor taylorKinney = new Actor("Taylor Kinney");
		taylorKinney.setDateOfBirth(LocalDate.of(1981, 7, 15));
		taylorKinney.setPlaceOfBirth("Lancaster, Pennsylvania, USA");
		taylorKinney.setPicture("resources/taylor_kinney.jpg");
		taylorKinney.setBiography("Kinney was born in Lancaster, Pennsylvania, to Pamela (Heisler), a dental hygienist, and Daniel Kinney, a banker. He is of German, Swiss-German, and English ancestry. Taylor and his three brothers were raised in Neffsville, Pennsylvania by their single mother, who worked as a dental hygienist. Kinney studied business management at West Virginia University, before developing an interest in acting.");
		
		// Chicago Fire
		Movie chicagoFire = new Movie();

		chicagoFire.setName("Chicago Fire");
		chicagoFire.setGenre(Genre.ACTION);
		chicagoFire.setRating(3.5);
		chicagoFire.setYear(2012);
		chicagoFire.addCharacter(new Character("Kelly Severide", taylorKinney));
		
		try {
			chicagoFire.setPoster(Files.readAllBytes(Paths.get("resources/chicago_fire.jpg")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
