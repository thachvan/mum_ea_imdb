package edu.mum.ea.imdb.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Comment {
	@Id
	@GeneratedValue
	private int id;

	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;

	private String comment;

	public Comment() {
	}

	public Comment(User user, String comment) {
		this.user = user;
		this.comment = comment;
	}

	public User getUser() {
		return user;
	}

	public String getComment() {
		return comment;
	}
}
