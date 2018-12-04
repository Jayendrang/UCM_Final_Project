package library.app.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import library.app.dao.model.user_profile;

@Repository
public interface UserServices extends JpaRepository<user_profile, String> {

	@Query(value="SELECT pro.recovery_question1,pro.recovery_answer1,pro.recovery_question2,pro.recovery_answer2 from "
			+ "user_profile pro WHERE pro.user_id=?1")
	public user_profile getProfileRecoveryInfo(user_profile data);
	
	@Query(value="SELECT NEW user_profile(pro.user_id, pro.email_id, pro.role, pro.user_fname, pro.user_lname, pro.institution_id,  "
			+ "pro.institution_name, pro.creation_date, pro.status) from user_profile pro where pro.role=?1")
	public List<user_profile> getAllUserInfo(String role);
	
	@Query(value="SELECT NEW user_profile(pro.user_id, pro.email_id, pro.password, pro.role, pro.user_fname, pro.user_lname, pro.institution_id, "
			+ "pro.institution_name, pro.creation_date, pro.status) from user_profile pro where pro.user_lname LIKE CONCAT('%',:user_lname,'%')")
	public List<user_profile> getUserInfo(@Param("user_lname")String user_lname);
	
	@Transactional
	@Modifying
	@Query(value="UPDATE user_profile pro SET pro.is_locked=?2 WHERE pro.user_id=?1")
	public int updateProfile(String user_id,String is_locked);
	
	@Query(value="SELECT count(users.user_id) from user_profile users")
	public int getAllUserCount();
	
	@Query(value="SELECT COUNT(users.user_id) from user_profile users where users.institution_id=?1")
	public int getUserCountByInstitution(String inst_id);
	
	@Query(value="SELECT user.user_id,user.role,user.institution_id,user.institution_name,user.password,user.user_fname,user.user_lname, user.is_locked from "
			+ "user_profile user WHERE user.user_id= ?1 and user.status='ACTIVE' ")
	public user_profile validateUser(String userId);
	
}
