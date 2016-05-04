package edu.mum.ea.imdb;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import edu.mum.ea.imdb.model.Actor;
import edu.mum.ea.imdb.model.Character;
import edu.mum.ea.imdb.model.Comment;
import edu.mum.ea.imdb.model.Director;
import edu.mum.ea.imdb.model.Movie;
import edu.mum.ea.imdb.model.Movie.Genre;
import edu.mum.ea.imdb.model.Person;
import edu.mum.ea.imdb.model.User;

public class DAO {
	SessionFactory sessionFactory = null;

	public DAO() {
		Configuration configuration = new Configuration().configure();

		configuration.addAnnotatedClass(Actor.class);
		configuration.addAnnotatedClass(Character.class);
		configuration.addAnnotatedClass(Comment.class);
		configuration.addAnnotatedClass(Director.class);
		configuration.addAnnotatedClass(Movie.class);
		configuration.addAnnotatedClass(Person.class);
		configuration.addAnnotatedClass(User.class);

		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties());
		sessionFactory = configuration.buildSessionFactory(builder.build());
	}

	public void addMovies(Movie... movies) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();

		for (Movie movie : movies) {
			session.save(movie);
		}

		session.close();
	}

	public List<Movie> searchMovies(Predicate<Movie> predicate) {
		List<Movie> result = new ArrayList<Movie>();

		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();

		@SuppressWarnings("unchecked")
		List<Movie> movies = session.createQuery("FROM Movie").list();

		session.close();

		for (Movie movie : movies) {
			if (predicate.test(movie)) {
				result.add(movie);
			}
		}

		return result;
	}

	public List<Movie> searchMovieByName(String name) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();

		@SuppressWarnings("unchecked")
		List<Movie> result = session
				.createQuery("FROM Movie m WHERE m.name='" + name + "'").list();

		session.close();

		return result;
	}

	public List<Movie> searchMovieByGenre(Genre genre) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();

		@SuppressWarnings("unchecked")
		List<Movie> result = session
				.createQuery("FROM Movie m WHERE m.genre='" + genre + "'")
				.list();

		session.close();

		return result;
	}

	public List<Movie> searchMovieByRating(double rating) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();

		@SuppressWarnings("unchecked")
		List<Movie> result = session
				.createQuery("FROM Movie m WHERE m.rating=" + rating).list();

		session.close();

		return result;
	}

	public List<Movie> searchMovieByYear(int year) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();

		@SuppressWarnings("unchecked")
		List<Movie> result = session
				.createQuery("FROM Movie m WHERE m.rating=" + year).list();

		session.close();

		return result;
	}

	public List<Movie> searchMovieByDirector(String name) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();

		@SuppressWarnings("unchecked")
		List<Movie> result = session.createQuery(
				"SELECT DISTINCT m FROM Movie m JOIN m.directors d WHERE d.name = '"
						+ name + "'")
				.list();

		session.close();

		return result;
	}

	public List<Movie> searchMovieByActor(String name) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();

		@SuppressWarnings("unchecked")
		List<Movie> result = session.createQuery(
				"SELECT DISTINCT m FROM Movie m JOIN m.characters c WHERE c.actor.name = '"
						+ name + "'")
				.list();

		session.close();

		return result;
	}

	public List<Movie> searchMovieByCharacter(String name) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();

		@SuppressWarnings("unchecked")
		List<Movie> result = session.createQuery(
				"SELECT DISTINCT m FROM Movie m JOIN m.characters c WHERE c.name = '"
						+ name + "'")
				.list();

		session.close();

		return result;
	}

	public void close() {
		sessionFactory.close();
	}
}
