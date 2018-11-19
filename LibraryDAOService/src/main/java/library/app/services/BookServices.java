package library.app.services;

import java.util.HashMap;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import library.app.dao.model.books_info;

@Repository
public interface  BookServices	extends JpaRepository<books_info, String> {
	
	
	@Query(value="SELECT books FROM books_info books where institution_id=?1")
	public List<books_info> getAllBooksByInstitution(String institution_id);
	
	@Query(value="SELECT books FROM books_info books where books.author LIKE CONCAT('%',:author,'%')")
	public List<books_info> getBooksByAuthor(@Param("author")String author);

	@Query(value="SELECT books FROM books_info books where books.book_name LIKE CONCAT('%',:book_name,'%')")
	public List<books_info> getBooksByName(@Param("book_name")String book_name);
	
	@Query(value="select distinct(book_genre),count(institution_name) from books_info where institution_id=?1 group by book_genre ")
	public String[] getBooksGenreByInstitution(String institution_id);
	
	@Query(value="delete from books_info books where books.book_id=?1")
	public int deleteBooks(String book_id);
	
	@Query(value="select distinct(book_genre),count(book_genre) from books_info group by book_genre")
	public String[] getBookCollectionByGenre();
}
