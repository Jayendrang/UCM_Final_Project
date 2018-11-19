package library.app.dao.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="library_transactions")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value= {"transaction_date"},allowGetters=true)	
public class library_transactions {

	@Id
	private String transaction_id;
	
	@NotNull
	private String book_id;
	
	@NotNull
	private String user_id;
	
	@Column(nullable=false,updatable=false)
	@Temporal(TemporalType.DATE)
	@CreatedDate
	private String transaction_date;
	private String remark;
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
	public String getTransaction_date() {
		return transaction_date;
	}
	
	public void setTransaction_date(String transaction_date) {
		this.transaction_date = transaction_date;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
