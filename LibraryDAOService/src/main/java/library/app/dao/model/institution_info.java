package library.app.dao.model;

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
@Table(name = "institution_info")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = { "created_date" }, allowGetters = true)
public class institution_info {

	@Id
	private String institution_id;

	@NotBlank
	private String institution_name;
	@NotBlank
	private String institution_address;
	@NotBlank
	private String institution_invite_id;
	@NotBlank
	private String institution_email_domain;
	@NotBlank
	private String institution_email_id;
	@NotBlank
	private String institution_contact;

	@Column(nullable = false, updatable = false)
	@Temporal(TemporalType.DATE)
	@CreatedDate
	private Date created_date;

	@NotBlank
	private String created_by;

	@NotBlank
	private String server_repo_path;

	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getServer_repo_path() {
		return server_repo_path;
	}

	public void setServer_repo_path(String server_repo_path) {
		this.server_repo_path = server_repo_path;
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

	public String getInstitution_address() {
		return institution_address;
	}

	public void setInstitution_address(String institution_address) {
		this.institution_address = institution_address;
	}

	public String getInstitution_invite_id() {
		return institution_invite_id;
	}

	public void setInstitution_invite_id(String institution_invite_id) {
		this.institution_invite_id = institution_invite_id;
	}

	public String getInstitution_email_domain() {
		return institution_email_domain;
	}

	public void setInstitution_email_domain(String institution_email_domain) {
		this.institution_email_domain = institution_email_domain;
	}

	public String getInstitution_email_id() {
		return institution_email_id;
	}

	public void setInstitution_email_id(String institution_email_id) {
		this.institution_email_id = institution_email_id;
	}

	public String getInstitution_contact() {
		return institution_contact;
	}

	public void setInstitution_contact(String institution_contact) {
		this.institution_contact = institution_contact;
	}

	public Date getCreated_date() {
		return created_date;
	}

	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}

	public String getCreated_by() {
		return created_by;
	}

	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}

	public institution_info() {
		
	}
	
	public institution_info(String inst_name,String inst_addr,String mail_domain,
			String mail_id,String contact) {
		this.institution_address=inst_addr;
		this.institution_name=inst_name;
		this.institution_email_domain=mail_domain;
		this.institution_email_id=mail_id;
		this.institution_contact=contact;
	}
}
