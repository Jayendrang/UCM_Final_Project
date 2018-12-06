package com.library.pojo;

import java.util.Date;

public class BooksInfo {
	private String book_id;

	private String book_name;

	private String book_isbn;

	private String book_genre;

	private String author;

	private String edition;

	private String user_id;

	private String institution_id;

	private String institution_name;

	private String repo_path;

	private Date created_date;

	public String getBook_id() {
		return book_id;
	}

	public void setBook_id(String book_id) {
		this.book_id = book_id;
	}

	public String getBook_name() {
		return book_name;
	}

	public void setBook_name(String book_name) {
		this.book_name = book_name.toUpperCase();
	}

	public String getBook_isbn() {
		return book_isbn;
	}

	public void setBook_isbn(String book_isbn) {
		this.book_isbn = book_isbn;
	}

	public String getBook_genre() {
		return book_genre;
	}

	public void setBook_genre(String book_genre) {
		this.book_genre = book_genre.toUpperCase();
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author.toUpperCase();
	}

	public String getEdition() {
		return edition;
	}

	public void setEdition(String edition) {
		this.edition = edition;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getInstitution_id() {
		return institution_id;
	}

	public void setInstitution_id(String institution_id) {
		this.institution_id = institution_id;
	}

	public String getInstitution_name() {
		return institution_name;
	}

	public void setInstitution_name(String institution_name) {
		this.institution_name = institution_name;
	}

	public String getRepo_path() {
		return repo_path;
	}

	public void setRepo_path(String repo_path) {
		this.repo_path = repo_path;
	}

	public Date getCreated_date() {
		return created_date;
	}

	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}

}
