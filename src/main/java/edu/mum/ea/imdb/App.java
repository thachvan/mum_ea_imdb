package edu.mum.ea.imdb;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import edu.mum.ea.imdb.model.Actor;
import edu.mum.ea.imdb.model.Movie;
import edu.mum.ea.imdb.model.Movie.Genre;
import edu.mum.ea.imdb.model.User;
import edu.mum.ea.imdb.model.Character;
import edu.mum.ea.imdb.model.Director;

public class App {
	public static void main(String[] args) throws IOException {
		initDB();
	}

	public static void initDB() throws IOException {
		// Actors
		Actor taylorKinney = new Actor("Taylor Kinney");
		taylorKinney.setDateOfBirth(LocalDate.of(1981, 7, 15));
		taylorKinney.setPlaceOfBirth("Lancaster, Pennsylvania, USA");
		taylorKinney.setPicture("resources/taylor_kinney.jpg");
		taylorKinney.setBiography(
				"Kinney was born in Lancaster, Pennsylvania, to Pamela (Heisler), a dental hygienist, and Daniel Kinney, a banker. He is of German, Swiss-German, and English ancestry. Taylor and his three brothers were raised in Neffsville, Pennsylvania by their single mother, who worked as a dental hygienist. Kinney studied business management at West Virginia University, before developing an interest in acting.");

		Actor jesseSpencer = new Actor("Jesse Spencer");
		jesseSpencer.setDateOfBirth(LocalDate.of(1979, 12, 12));
		jesseSpencer.setPlaceOfBirth("Melbourne, Victoria, Australia");
		jesseSpencer.setPicture("resources/jesse_spencer.jpg");
		jesseSpencer.setBiography(
				"Jesse Spencer was born on February 12, 1979 in Melbourne, Victoria, Australia as Jesse Gordon Spencer. He is an actor, known for House M.D. (2004), Chicago Fire (2012) and Neighbours (1985).");

		Actor stefanDennis = new Actor("Stefan Dennis");
		stefanDennis.setDateOfBirth(LocalDate.of(1958, 10, 30));
		stefanDennis.setPlaceOfBirth("Melbourne, Victoria, Australia");
		stefanDennis.setPicture("resources/stefan_dennis.jpg");
		stefanDennis.setBiography(
				"Stefan Dennis was born on October 30, 1958 in Melbourne, Victoria, Australia. He is an actor and producer, known for Neighbours (1985), 50 Kisses (2014) and Dave's Dead (2012). He has been married to Gail Easdale since 2000. They have two children. He was previously married to Roz Roy.");

		// Chicago Fire movie
		Movie chicagoFire = new Movie("Chicago Fire");

		chicagoFire.setGenre(Genre.ACTION);
		chicagoFire.setRating(7.9);
		chicagoFire.setYear(2012);
		chicagoFire.setPoster("resources/chicago_fire.jpg");
		chicagoFire.setSummary(
				"The story of firefighters in Chicago, both on a personal and professional level.");
		chicagoFire.addDirector(new Director("Joe Chappelle"),
				new Director("Sanford Bookstaver"));
		chicagoFire.addCharacter(new Character("Kelly Severide", taylorKinney),
				new Character("Matthew Casey", jesseSpencer));
		chicagoFire.addComment(new User("lauren_779"),
				"If you are planning on watching this show and pointing out the inaccuracies, don't watch it");
		chicagoFire.addComment(new User("sfcat"),
				"Wasn't perfect but no pilot is");

		// Neighbours movie
		Movie neighbours = new Movie("Neighbours");
		neighbours.setGenre(Genre.DRAMA);
		neighbours.setRating(5.2);
		neighbours.setYear(2016);
		neighbours.setPoster("resources/neighbours.jpg");
		neighbours.setSummary(
				"Serial chronicling the lives of the residents of Ramsay Street in the fictional Australian suburb of Erinsborough. When the series began, it revolved around three families - the Ramsays, the Robinsons and the Clarkes, living at no. 24, no. 26 and no. 28 respectively. Nowadays, the scope of the programme is much wider and only one member of the original families remains in the street. The show reached a watershed in 1997 when the last remaining original character, Helen Daniels, died. The departure of her relatives and arrival of the Scully family marked the start of a new era for Neighbours. There's never a dull moment in Ramsay Street, and despite their many feuds, the residents will always - in the words of the show's theme tune - \"be there for one another\".");
		neighbours.addDirector(new Director("Tony Osicka"));
		neighbours.addCharacter(new Character("Paul Robinson", stefanDennis),
				new Character("Billy Kennedy", jesseSpencer));
		neighbours.addComment(new User("tracy-piedmont"),
				"OK. I read some of the lesser reviews but I totally disagree. I love this show and after 1 episode I was hooked");

		DAO dao = new DAO();
		
		// perform saving objects
		dao.addMovies(chicagoFire, neighbours);
		// search movie by name using Lambda
		List<Movie> moviesByName = dao.searchMovies(movie -> movie.getName().equals("Chicago Fire"));
		// search movie by name using function
		moviesByName = dao.searchMovieByName("Chicago Fire");
		// search movie by genre
		List<Movie> moviesByGenre = dao.searchMovieByGenre(Genre.DRAMA);
		// search movie by rating
		List<Movie> moviesByRating = dao.searchMovieByRating(5.2);
		// search movie by year
		List<Movie> moviesByYear = dao.searchMovieByYear(2016);
		// search movie by actor
		List<Movie> moviesByActor = dao.searchMovieByActor("Jesse Spencer");
		// search movie by character
		List<Movie> moviesByCharacter = dao.searchMovieByCharacter("Billy Kennedy");
		// search movie by director
		List<Movie> moviesByDirector = dao.searchMovieByDirector("Sanford Bookstaver");
		
		dao.close();
	}
}
