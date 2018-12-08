package library.app.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import library.app.dao.model.books_info;

@Repository
public interface BookServices extends JpaRepository<books_info, String> {

	@Query(value = "SELECT books FROM books_info books where institution_id=?1 and books.status='AVAILABLE' ")
	public List<books_info> getAllBooksByInstitution(String institution_id);

	@Query(value = "SELECT books FROM books_info books where books.author LIKE CONCAT('%',:author,'%') and books.status='AVAILABLE' ")
	public List<books_info> getBooksByAuthor(@Param("author") String author);

	@Query(value = "SELECT books FROM books_info books where books.book_name LIKE CONCAT('%',:book_name,'%') and books.status='AVAILABLE' ")
	public List<books_info> getBooksByTitle(@Param("book_name") String book_name);

	@Query(value = "select distinct(book_genre),count(institution_name) from books_info where institution_id=?1 and status='AVAILABLE' group by book_genre ")
	public String[] getBooksGenreByInstitution(String institution_id);
	
	@Query(value = "delete from books_info books where books.book_id=?1")
	public int deleteBooks(String book_id);

	@Query(value = "select distinct(book_genre),count(book_genre) from books_info group by book_genre")
	public String[] getBookCollectionByGenre();

	@Query(value = "SELECT books FROM books_info books WHERE books.status='AVAILABLE' ORDER BY RAND()")
	public List<books_info> getRandomBooks(Pageable pageable);

	@Query(value = "SELECT books FROM books_info books where books.author LIKE CONCAT('%',:author,'%') and books.status='AVAILABLE' and books.institution_id=:inst_id")
	public List<books_info> getBooksByAuthorUser(@Param("author") String author, @Param("inst_id")String id);

	@Query(value = "SELECT books FROM books_info books where books.book_name LIKE CONCAT('%',:book_name,'%') and books.institution_id=:inst_id and books.status='AVAILABLE'")
	public List<books_info> getBooksByTitleUser(@Param("book_name") String book_name, @Param("inst_id")String id);

	@Query(value = "SELECT books FROM books_info books where books.book_genre LIKE CONCAT('%',:book_genre,'%') and books.status='AVAILABLE' ORDER BY books.book_id")
	public List<books_info> getBooksByGenre(@Param("book_genre") String genre);

	@Query(value = "SELECT books FROM books_info books where books.book_name LIKE CONCAT('%',:book_genre,'%') and books.status='AVAILABLE' and books.institution_id=:inst_id ORDER BY books.book_id")
	public List<books_info> getBooksByGenreUser(@Param("book_genre") String genre,@Param("inst_id") String id);
	
	@Query(value="SELECT COUNT(books.book_id) from books_info books WHERE books.status='AVAILABLE' ")
	public int totalBookCount();
	
	@Transactional
	@Modifying
	@Query(value="UPDATE books_info books SET books.status='REMOVED' WHERE books.institution_id=:inst_id")
	public int removeBooksFromRepo(@Param("inst_id")String inst);
	

	@Transactional
	@Modifying
	@Query(value="UPDATE books_info books SET books.status='REMOVED' WHERE books.book_id=:book_id")
	public int removeBook(@Param("book_id")String id);
	
	
}
