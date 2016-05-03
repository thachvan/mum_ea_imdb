package edu.mum.ea.imdb.model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;

public class Person {
	private String name;
	private LocalDate dateOfBirth;
	private String placeOfBirth;
	private String biography;
	private byte[] picture;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getPlaceOfBirth() {
		return placeOfBirth;
	}

	public void setPlaceOfBirth(String placeOfBirth) {
		this.placeOfBirth = placeOfBirth;
	}

	public String getBiography() {
		return biography;
	}

	public void setBiography(String biography) {
		this.biography = biography;
	}

	public byte[] getPicture() {
		return picture;
	}

	public void setPicture(String pictureFilePath) throws IOException {
		this.picture = Files.readAllBytes(Paths.get(pictureFilePath));
	}
}
