package library.app.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import library.app.dao.model.institution_info;

@Repository
public interface InstitutionServices extends JpaRepository<institution_info, String> {

	@Query("SELECT ins FROM institution_info ins WHERE ins.status='ACTIVE' ")
	public List<institution_info> getInstitutionByStatus();
	
	
	@Transactional
	@Modifying
	@Query("UPDATE institution_info ins SET ins.status=?1,ins.created_by=?2 WHERE ins.institution_id=?3")
	public int updateInstitutionStatus(String status,String createdBy, String inst_id);
	
	@Query("SELECT info FROM institution_info info WHERE institution_email_domain=?1")
	public institution_info getInstitutionByDomain(String domainName);
	
	@Query("SELECT count(ins.institution_id) from institution_info ins WHERE ins.status=:status")
	public int totalInstitutionCount(@Param("status") String status);
}
