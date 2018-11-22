package com.library.pojo;

import java.util.Date;


public class Transactions {


	private String transaction_id;

	private String book_id;

	private String user_id;

	private Date transaction_date;

	private String remarks;

	private BooksInfo books;

	private User_Info profile;

	private String institution_id;

	public String getTransaction_id() {
		return transaction_id;
	}

	public void setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
	}

	public String getBook_id() {
		return book_id;
	}

	public void setBook_id(String book_id) {
		this.book_id = book_id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public Date getTransaction_date() {
		return transaction_date;
	}

	public void setTransaction_date(Date transaction_date) {
		this.transaction_date = transaction_date;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public BooksInfo getBooks() {
		return books;
	}

	public void setBooks(BooksInfo books) {
		this.books = books;
	}

	public User_Info getProfile() {
		return profile;
	}

	public void setProfile(User_Info profile) {
		this.profile = profile;
	}

	public String getInstitution_id() {
		return institution_id;
	}

	public void setInstitution_id(String institution_id) {
		this.institution_id = institution_id;
	}
	
	
}
