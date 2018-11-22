package library.app.LibraryDAOService;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.validation.Valid;
import javax.ws.rs.core.MultivaluedMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

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

@CrossOrigin(origins="*",allowedHeaders="*")
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
	public books_info getBooks(@RequestParam(value = "bookid") String bookId) throws Exception {

		books_info data = bookservices.findById(bookId)
				.orElseThrow(() -> new BookExceptions("", "", "No Books found in repository"));
		System.err.println(data.getBook_name());
		if (data != null) {
			return data;
		}
		return null;
	}

	// save list of books from by institution id

	@PostMapping("/savebooks")
	public List<String> uploadBooks(@Valid @RequestBody List<books_info> books,
			@RequestParam(value = "institution_id") String institutionId) throws Exception {

		List<String> callresponse = new ArrayList<>();
		Optional<institution_info> institute_info = institutionservices.findById(institutionId);

		if (institute_info.isPresent()) {
			for (books_info books1 : books) {
				books1.setBook_id(UniqueIdGenerator.getRandomBookID());
				books1.setInstitution_name(institute_info.get().getInstitution_name());
				String tBookRepoPath = institute_info.get().getServer_repo_path().concat("/")
						.concat(books1.getBook_id());
				books1.setRepo_path(tBookRepoPath);
				books1.setBook_cover_path(institute_info.get().getServer_repo_path().concat("/thumbnails/")
						.concat(books1.getBook_id().concat(".png")));
				books_info book = bookservices.save(books1);
				file_logger action = new file_logger();
				action.setBook_id(books1.getBook_id());
				action.setRepo_path(books1.getRepo_path());
				action.setStatus(AppConstants.FILE_BATCH_NEW);
				file_logger logger = fileLoggerService.save(action);
				if ((!book.equals(null)) && (!logger.equals(null))) {
					callresponse.add("SUCCESS");
				}
			}
		}
		return callresponse;
	}

	// delete list of books from by institution id

	@SuppressWarnings("unchecked")
	@PostMapping("/removebooks")
	public boolean removeBooks(@Valid @RequestBody List<String> books) throws Exception {
		int tcount = 0;
		for (String inbooks : books) {
			System.err.println("BOOK DELETED :: SERVICE");
			if (bookservices.existsById(inbooks)) {
				System.err.println("BOOK DELETED :: END");
				tcount = +bookservices.deleteBooks(inbooks);
				
			}
		}

		return tcount == books.size() ? true : false;
	}

	// update books info from by institution id

	@PostMapping("/updatebooks")
	public List<books_info> updateBooksInfo(@Valid @RequestBody List<books_info> books) throws Exception {
		List<books_info> response = new ArrayList<>();
		for (books_info inbooks : books) {
			books_info tbooks = bookservices.findById(inbooks.getBook_id())
					.orElseThrow(() -> new BookExceptions(inbooks.getBook_name(), inbooks.getInstitution_name(),
							inbooks.getRepo_path()));
			if (!tbooks.getBook_id().isEmpty()) {
				tbooks.setAuthor(inbooks.getAuthor());
				tbooks.setBook_genre(inbooks.getBook_genre());
				tbooks.setEdition(inbooks.getEdition());
				tbooks.setBook_isbn(inbooks.getBook_isbn());
				tbooks.setBook_name(inbooks.getBook_name());
				response.add(bookservices.save(tbooks));
			}
		}

		return response;
	}

	// Retrieve all books by institution Id

	@GetMapping("/getBooksByInstitute")
	public List<books_info> getAllBooksByInstitution(@RequestParam(value = "univ_id") String univ_id) throws Exception {

		List<books_info> books = bookservices.getAllBooksByInstitution(univ_id);
		if (books != null) {
			return books;
		}
		return null;
	}

	// Retrieve books by author name
	@GetMapping("/getBooksByAuthor")
	public List<books_info> getBooksByAuthor(@RequestParam(value = "name") String name) throws Exception {

		List<books_info> books_author = bookservices.getBooksByAuthor(name);
		if (books_author != null) {
			return books_author;
		}
		return null;
	}

	// Retrieve books by book title
	@GetMapping("/getBooksByTitle")
	public List<books_info> getBooksByTitle(@RequestParam(value = "title") String title) throws Exception {

		List<books_info> books_author = bookservices.getBooksByName(title);
		if (books_author != null) {
			return books_author;
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

		return null;
	}

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

}
