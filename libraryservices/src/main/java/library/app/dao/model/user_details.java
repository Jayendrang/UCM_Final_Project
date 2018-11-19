package library.app.dao.model;

import java.io.Serializable;

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

import library.app.utilities.UniqueIdGenerator;

@Entity
@Table(name="user_profile")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value= {"creation_date","last_update_date"},allowGetters=true)	
public class user_details implements Serializable{

@Id	
private String user_id;
@NotBlank
private String email_id;
@NotBlank
private String role;
@NotBlank
private String user_fname;
@NotBlank
private String user_lname;
@NotBlank
private String recovery_question1;
@NotBlank
private String recovery_answer1;
@NotBlank
private String recovery_question2;
@NotBlank
private String recovery_answer2;
private String invite_id;
@NotBlank
private String institution_id;
@NotBlank
private String institution_name;

private String mobile;

@Column(nullable=false,updatable=false)
@Temporal(TemporalType.DATE)
@CreatedDate
private String creation_date;

@Column(nullable=false,updatable=false)
@Temporal(TemporalType.DATE)
@CreatedDate
private String last_update_date;

public String getUser_id() {
	return user_id;
}
public void setUser_id(String user_id) {

	this.user_id = user_id;
}
public String getEmail_id() {
	return email_id;
}
public void setEmail_id(String email_id) {
	this.email_id = email_id;
}
public String getRole() {
	return role;
}
public void setRole(String role) {
	this.role = role;
}
public String getUser_fname() {
	return user_fname;
}
public void setUser_fname(String user_fname) {
	this.user_fname = user_fname;
}
public String getUser_lname() {
	return user_lname;
}
public void setUser_lname(String user_lname) {
	this.user_lname = user_lname;
}
public String getRecovery_question1() {
	return recovery_question1;
}
public void setRecovery_question1(String recovery_question1) {
	this.recovery_question1 = recovery_question1;
}
public String getRecovery_answer1() {
	return recovery_answer1;
}
public void setRecovery_answer1(String recovery_answer1) {
	this.recovery_answer1 = recovery_answer1;
}
public String getRecovery_question2() {
	return recovery_question2;
}
public void setRecovery_question2(String recovery_question2) {
	this.recovery_question2 = recovery_question2;
}
public String getRecovery_answer2() {
	return recovery_answer2;
}
public void setRecovery_answer2(String recovery_answer2) {
	this.recovery_answer2 = recovery_answer2;
}
public String getInvite_id() {
	return invite_id;
}
public void setInvite_id(String invite_id) {
	this.invite_id = invite_id;
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
public String getMobile() {
	return mobile;
}
public void setMobile(String mobile) {
	this.mobile = mobile;
}
public String getCreation_date() {
	return creation_date;
}
public void setCreation_date(String creation_date) {
	this.creation_date = creation_date;
}
public String getLast_update_date() {
	return last_update_date;
}
public void setLast_update_date(String last_update_date) {
	this.last_update_date = last_update_date;
}

}
