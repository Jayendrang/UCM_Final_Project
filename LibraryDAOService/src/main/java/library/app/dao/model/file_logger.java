package library.app.dao.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "file_action_logger")
@EntityListeners(AuditingEntityListener.class)
public class file_logger {

	@Id
	@GeneratedValue
	@Column(name="file_log_id",updatable=false,nullable=false)
	private int file_log_id;
	
	private String book_id, repo_path,status;
	
	@Column(name="created_date",nullable=false,updatable=false)
	@Temporal(TemporalType.DATE)
	@CreatedDate
	private Date created_date; 
	

	public int getFile_log_id() {
		return file_log_id;
	}

	public void setFile_log_id(int file_log_id) {
		this.file_log_id = file_log_id;
	}

	public String getBook_id() {
		return book_id;
	}

	public void setBook_id(String book_id) {
		this.book_id = book_id;
	}

	public String getRepo_path() {
		return repo_path;
	}

	public void setRepo_path(String repo_path) {
		this.repo_path = repo_path;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreated_date() {
		return created_date;
	}

	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}

}
