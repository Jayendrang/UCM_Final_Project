package library.app.LibraryService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import library.app.dao.model.books_info;
import library.app.exceptions.BookExceptions;
import library.app.services.BookServices;
import library.app.utilities.UniqueIdGenerator;

@RestController
@RequestMapping("/library/repository")
public class BookServicesImpl {

	@Autowired
	BookServices bookservices;

	// read books list from by institution id

	@SuppressWarnings("unchecked")
	@GetMapping("/getbook/{bookId}")
	public books_info getBooks(@PathVariable(value = "bookId") String bookId) throws Exception {

		books_info data = bookservices.findById(bookId)
				.orElseThrow(() -> new BookExceptions("", "", "No Books found in repository"));
		System.err.println(data.getBook_name());
		if (data != null) {
			return data;
		}
		return null;
	}

	// save list of books from by institution id

	@PostMapping("/savebooks/{univ_id}")
	public List<books_info> uploadBooks(@Valid @RequestBody List<books_info> books,
			@PathVariable(value = "univ_id") String univ_id) throws Exception {
		List<books_info> bookswithId = new ArrayList<>();
		for (books_info books1 : books) {
			books1.setBook_id(UniqueIdGenerator.getRandomBookID());
			bookswithId.add(books1);
		}
		return bookservices.saveAll(bookswithId);
	}

	// delete list of books from by institution id

	@SuppressWarnings("unchecked")
	@DeleteMapping("/removebooks")
	public ResponseEntity<?> removeBooks(@Valid @RequestBody List<books_info> books) throws Exception {
		for (books_info inbooks : books) {
			System.err.println("BOOK DELETED :: SERVICE");
			if (!inbooks.getBook_id().isEmpty()) {
				System.err.println("BOOK DELETED :: END");

				bookservices.deleteById((inbooks.getBook_id()));

			}
		}
		return ResponseEntity.ok().build();
	}

	// update books info from by institution id

	@PutMapping("/updatebooks")
	public ResponseEntity<?> updateBooksInfo(@Valid @RequestBody List<books_info> books) throws Exception {
		String temp_institution_id = null;
		for (books_info inbooks : books) {
			books_info tbooks = bookservices.findById(inbooks.getBook_id())
					.orElseThrow(() -> new BookExceptions(inbooks.getBook_name(), inbooks.getInstitution_name(),
							inbooks.getRepo_path()));
			temp_institution_id = tbooks.getInstitution_id();
			if (!tbooks.getBook_id().isEmpty()) {
				tbooks.setAuthor(inbooks.getAuthor());
				tbooks.setBook_genre(inbooks.getBook_genre());
				tbooks.setEdition(inbooks.getEdition());
				tbooks.setBook_isbn(inbooks.getBook_isbn());
				tbooks.setBook_name(inbooks.getBook_name());
				bookservices.save(tbooks);
			}
		}

		return ResponseEntity.ok().build();// getAllBooksByInstitution(temp_institution_id);
	}

	// Retrieve all books by institution Id

	@GetMapping("/getMultipleBooks")
	public List<books_info> getAllBooksByInstitution(@RequestParam(value = "univ_id") String univ_id) throws Exception {

		List<books_info> books = bookservices.getAllBooksByInstitution(univ_id);
		if (books != null) {
			return books;
		}
		return null;
	}

	// Retrieve books by author name
	@GetMapping("/getBooksByAuthor")
	public List<books_info> getBooksByAuthor(@PathVariable(value = "name") String name) throws Exception {

		List<books_info> books_author = bookservices.getBooksByAuthor(name);
		if (books_author != null) {
			return books_author;
		}
		return null;
	}

	// Retrieve books by book title
	@GetMapping("/getBooksByTitle")
	public List<books_info> getBooksByTitle(@PathVariable(value = "title") String title) throws Exception {

		List<books_info> books_author = bookservices.getBooksByName(title);
		if (books_author != null) {
			return books_author;
		}
		return null;
	}
	
	// Total books by genre
	@GetMapping("/getBooksCountByGenre")
	public HashMap<String, String> getBooksCountByGenre(@PathVariable(value = "institution_id") String institution_id)
			throws  NoSuchElementException, Exception{
		String[] data = bookservices.getBooksGenreByInstitution(institution_id);
		for(String dd : data) {
			
			System.err.println(data.getClass().getSimpleName());
		}
		
		return null;
	}
	
	
}
