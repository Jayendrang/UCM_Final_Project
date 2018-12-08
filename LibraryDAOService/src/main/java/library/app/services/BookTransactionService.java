package library.app.services;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import library.app.dao.model.library_transactions;

@Repository
public interface BookTransactionService extends JpaRepository<library_transactions,String> {

	@Query("SELECT trans from library_transactions trans WHERE trans.user_id= :user_id order by trans.transaction_date")
	public List<library_transactions> getBooksInfo(@Param("user_id")String userId);
	

	@Query("SELECT ins.institution_name,count(trans.transaction_id) FROM library_transactions trans JOIN institution_info ins on ins.institution_id=trans.institution_id" + 
			" WHERE ins.institution_id=?1")
	public String[] getTotalTransactionPerInstitute(String inst_id);
	
	
	@Query("select distinct(book_genre),count(institution_name) from books_info where institution_id=?1 group by book_genre ")
	public String[] getBooksGenresPerInstitution(String inst_id);
	
	@Query("select count(trans.transaction_id) from library_transactions trans where trans.transaction_date between ?1 and ?2 ")
	public int getTransactionVolumeByWeek(Date start,Date end);
	
	//@Query("select count(trans.transaction_id) from library_transactions trans where trans.transaction_date between ?1 and ?2 and trans.institution_id = ?3 ")
	//	public int getTransactionVolumeByWeekForInstitution(Date start, Date end,  String insti_id);

		//total reads in school
		
		@Query("select count(trans.transaction_id)\n" + 
				"from library_transactions trans inner join books_info books on trans.book_id = books.book_id\n" + 
				"where trans.institution_id=books.institution_id and trans.institution_id= :inst_id \n" + 
				"and trans.transaction_date between :st_date and :ed_date")
	
		public int getTotalTransactionPerInstitute1(@Param("inst_id")String inst_id,
				@Param("st_date") Date stdata, @Param("ed_date")Date ed_date);


		//total reads out school
		@Query("select count(trans.transaction_id)\n" + 
				"from library_transactions trans inner join books_info books on trans.book_id = books.book_id\n" + 
				"where trans.institution_id<>books.institution_id and trans.institution_id= :inst_id \n" + 
				"and trans.transaction_date between :st_date and :ed_date ")
		public int getTotlTransactionNonInstitute(@Param("inst_id") String inst_id,
				@Param("st_date") Date stdata, @Param("ed_date")Date ed_date);
}
