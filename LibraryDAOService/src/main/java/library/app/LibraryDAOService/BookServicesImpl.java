package library.app.LibraryDAOService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.client.http.HttpRequest;

import library.app.dao.model.StubClass;
import library.app.dao.model.books_info;
import library.app.dao.model.file_logger;
import library.app.dao.model.institution_info;
import library.app.exceptions.BookExceptions;
import library.app.services.BookServices;
import library.app.services.FileLoggerService;
import library.app.services.InstitutionServices;
import library.app.utilities.AppConstants;
import library.app.utilities.UniqueIdGenerator;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/library/book")
public class BookServicesImpl {

	@Autowired
	BookServices bookservices;

	@Autowired
	FileLoggerService fileLoggerService;

	@Autowired
	InstitutionServices institutionservices;

	@Autowired
	private RestTemplate restTemplate;

	// read books list from by institution id

	@SuppressWarnings("unchecked")
	@GetMapping("/getbook")
	public List<books_info> getBooks(@RequestParam(value = "bookid") String[] bookId) throws Exception {
		
		Iterable<String> searchStrings = Arrays.asList(bookId);
		List<books_info> data = bookservices.findAllById(searchStrings);
		
		if (!data.isEmpty()) {
	
			return data;
		}
		return null;
	}
	
	// save list of books from by institution id

	@PostMapping("/savebooks")
	public StubClass uploadBooks(@RequestBody books_info books) throws Exception {

		Optional<institution_info> institute_info = institutionservices.findById(books.getInstitution_id());
		StubClass response = new StubClass();
		if (institute_info.isPresent()) {
			

				// book repository
				books.setBook_id(UniqueIdGenerator.getRandomBookID());
				books.setInstitution_name(institute_info.get().getInstitution_name());
				String tBookRepoPath = institute_info.get().getServer_repo_path().concat("/")
						.concat(books.getBook_id());
				books.setRepo_path(tBookRepoPath.concat(".pdf"));
				books.setBook_cover_path(institute_info.get().getServer_repo_path().concat("/thumbnails/")
						.concat(books.getBook_id().concat(".jpeg")));
				books.setStatus(AppConstants.BOOKS_STATUS_NEW);
				books_info book = bookservices.save(books);
				
				// file logger
				file_logger action = new file_logger();
				action.setBook_id(books.getBook_id());
				action.setRepo_path(tBookRepoPath);
				action.setStatus(AppConstants.FILE_BATCH_NEW);
				file_logger logger = fileLoggerService.save(action);
			
				//response object
				if ((!book.equals(null)) && (!logger.equals(null))) {
					StubClass responseObject = new StubClass();
					responseObject.setKey(books.getBook_id());
					responseObject.setValue(institute_info.get().getServer_repo_path());
					response=responseObject;
				}
			
		}
		return response;
	}

	// delete list of books from by institution id

	@SuppressWarnings("unchecked")
	@PostMapping("/removebooks")
	public boolean removeBooks(@RequestParam(value="bookid") String books) throws Exception {
		int tcount=0;
		System.out.println("BookId--->"+books);
			if(bookservices.existsById(books)) {
				tcount+=bookservices.removeBook(books);
			}
		
		return tcount >= 1 ? true : false;
	}

	// update books info from by institution id

	@PostMapping("/updatebooks")
	public books_info updateBooksInfo(@RequestBody books_info inbooks) throws Exception {

		books_info tbooks = bookservices.findById(inbooks.getBook_id())
				.orElseThrow(() -> new BookExceptions(inbooks.getBook_name(), inbooks.getInstitution_name(),
						inbooks.getRepo_path()));
		if (!tbooks.getBook_id().isEmpty()) {
			tbooks.setAuthor(inbooks.getAuthor());
			tbooks.setBook_genre(inbooks.getBook_genre());
			tbooks.setEdition(inbooks.getEdition());
			tbooks.setBook_isbn(inbooks.getBook_isbn());
			tbooks.setBook_name(inbooks.getBook_name());
			return bookservices.save(tbooks);
		}

		return inbooks;
	}

	// Retrieve all books by institution Id

	@GetMapping("/getBooksByInstitute")
	public List<books_info> getAllBooksByInstitution(@RequestParam(value = "univ_id") String univ_id) throws Exception {
		
		List<books_info> books = bookservices.getAllBooksByInstitution(univ_id);
		if (books != null) {
			return books;
		}
		return books;
	}

	// Retrieve books by author name

	@GetMapping("/getBooksByAuthor")
	public List<books_info> getBooksByAuthor(@RequestParam(value = "name") String name,
			@RequestParam(value = "institution_id") String inst_id) throws Exception {
		List<books_info> books_author = new ArrayList<books_info>();
		if (inst_id.isEmpty() || inst_id.equals(null)) {
			books_author = bookservices.getBooksByAuthor(name);
		} else {
			books_author = bookservices.getBooksByAuthorUser(name, inst_id);
		}
		if (books_author != null) {
			return books_author;
		}
		return null;
	}

	// Retrieve books by book title

	@GetMapping("/getBooksByTitle")
	public List<books_info> getBooksByTitle(@RequestParam(value = "title") String title,
			@RequestParam(value = "institution_id") String inst_id) throws Exception {
		List<books_info> books_title = new ArrayList<books_info>();
		if (inst_id.isEmpty() || inst_id.equals(null)) {
			books_title = bookservices.getBooksByTitle(title);
		} else {
			books_title = bookservices.getBooksByTitleUser(title, inst_id);
		}
		if (books_title != null) {
			return books_title;
		}
		return null;
	}

	// Total books by genre

	@GetMapping("/getBooksCountByInstitution")
	public List<StubClass> getBooksCountByGenreInstitution(
			@RequestParam(value = "institution_id") String institution_id) throws NoSuchElementException, Exception {
		String[] data = bookservices.getBooksGenreByInstitution(institution_id);
		List<StubClass> response = new ArrayList<>();
		for (String dd : data) {
			String[] val = dd.split(",");
			StubClass items = new StubClass();
			items.setKey(val[0]);
			items.setValue(val[1]);
			response.add(items);
		}

		return response;
	}

	// Get books count by genre

	@GetMapping("/getAllBooksCountByGenre")
	public List<StubClass> getBooksCountGenre() {
		
		String[] data = bookservices.getBookCollectionByGenre();
		List<StubClass> response = new ArrayList<>();

		for (String idata : data) {
			String[] val = idata.split(",");
			StubClass items = new StubClass();
			items.setKey(val[0]);
			items.setValue(val[1]);
			response.add(items);
		}
		return response;
	}

	// Randombooks

	@GetMapping("/getRandomBooks")
	public List<books_info> getRandomBook() {
		return bookservices.getRandomBooks(new PageRequest(0, 5));
	}

	// BooksbyGenre
	@GetMapping("/getBooksByGenre")
	public List<books_info> getBooksByGenre(@RequestParam(value = "genre") String genre,
			@RequestParam(value = "institution_id") String inst_id) {
		List<books_info> response = new ArrayList<>();
		if (inst_id.isEmpty() || inst_id.equals(null)) {
			response = bookservices.getBooksByGenre(genre);
		} else {
			response = bookservices.getBooksByGenreUser(genre, inst_id);
		}
		return response;
	}
	
	

}
