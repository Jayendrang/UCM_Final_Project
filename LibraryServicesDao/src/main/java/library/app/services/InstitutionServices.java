package library.app.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import library.app.dao.model.institution_info;

@Repository
public interface InstitutionServices extends JpaRepository<institution_info, String> {

	@Query("SELECT ins FROM institution_info ins WHERE ins.status=?1")
	public List<institution_info> getInstitutionByStatus(String status);
	
	
	@Transactional
	@Modifying
	@Query("UPDATE institution_info ins SET ins.status=?1,ins.created_by=?2 WHERE ins.institution_id=?3")
	public int updateInstitutionStatus(String status,String createdBy, String inst_id);
	
	
}
