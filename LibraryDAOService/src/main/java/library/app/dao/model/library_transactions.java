package library.app.dao.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "library_transactions")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = { "transaction_date" }, allowGetters = true)
public class library_transactions implements Serializable {

	private static final long serialVersionUID = 1L;

	public library_transactions() {

	}

	@Id
	private String transaction_id;

	@NotNull
	private String book_id;

	@NotNull
	private String user_id;

	@Column(nullable = false, updatable = false)
	@Temporal(TemporalType.DATE)
	@CreatedDate
	private Date transaction_date;

	private String remarks;

//	@ManyToOne(fetch = FetchType.LAZY, optional = false)
//	@JoinColumn(name = "book_id", nullable = false, insertable = false, updatable = false)
//	@JsonIgnore
//	private books_info books;

	
//
//	@ManyToOne(fetch = FetchType.LAZY, optional = false)
//	@JoinColumn(name = "user_id", nullable = false, insertable = false, updatable = false)
//	@JsonIgnore
//	private user_profile profile;

	private String institution_id;

	public String getInstitution_id() {
		return institution_id;
	}

	public void setInstitution_id(String institution_id) {
		this.institution_id = institution_id;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

//	public books_info getBooks() {
//		return books;
//	}
//
//	public void setBooks(books_info books) {
//		this.books = books;
//	}

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
//	public user_profile getProfile() {
//		return profile;
//	}
//
//	public void setProfile(user_profile profile) {
//		this.profile = profile;
//	}
}
