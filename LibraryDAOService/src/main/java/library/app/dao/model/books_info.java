package library.app.dao.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "books_info")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = { "creation_date" }, allowGetters = true)
public class books_info implements Serializable {

	private static final long serialVersionUID = 1L;

	public books_info() {

	}

	@Id
	@Column(name = "book_id")
	private String book_id;

	@NotBlank
	@Column(name = "book_name")
	private String book_name;

	@NotBlank
	@Column(name = "book_isbn")
	private String book_isbn;

	@NotBlank
	@Column(name = "book_genre")
	private String book_genre;

	@NotBlank
	@Column(name = "author")
	private String author;

	@NotBlank
	@Column(name = "edition")
	private String edition;

	@NotBlank
	@Column(name = "user_id")
	private String user_id;

	@NotBlank
	@Column(name = "institution_id")
	private String institution_id;

	@NotBlank
	@Column(name = "institution_name")
	private String institution_name;

	@NotBlank
	@Column(name = "repo_path")
	private String repo_path;

	@NotBlank
	@Column(name = "book_cover_path")
	private String book_cover_path;

	@Column(name = "created_date", nullable = false, updatable = false)
	@Temporal(TemporalType.DATE)
	@CreatedDate
	private Date created_date;

	public String getRepo_path() {
		return repo_path;
	}

	public void setRepo_path(String repo_path) {
		this.repo_path = repo_path;
	}

	public String getInstitution_name() {
		return institution_name;
	}

	public void setInstitution_name(String institution_name) {
		this.institution_name = institution_name;
	}

	public String getBook_cover_path() {
		return book_cover_path;
	}

	public void setBook_cover_path(String book_cover_path) {
		this.book_cover_path = book_cover_path;
	}

	public String getBook_id() {
		return book_id;
	}

	public void setBook_id(String book_id) {
		this.book_id = book_id;// UniqueIdGenerator.getRandomBookID() ;
	}

	public String getBook_name() {
		return book_name;
	}

	public void setBook_name(String book_name) {
		this.book_name = book_name;
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
		this.book_genre = book_genre;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
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

	public Date getCreated_date() {
		return created_date;
	}

	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}

}
