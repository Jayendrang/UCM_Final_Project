package com.library.LibraryServiceDiscoveryClient;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.library.pojo.BooksInfo;
import com.library.pojo.SearchResultPojo;
import com.library.pojo.StubClass;
import com.library.service.BookServices;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/library/book")
public class BookServiceController {

	@Autowired
	BookServices bookservices;

	@GetMapping("/getBook")
	public List<BooksInfo> getBook(@RequestParam(value = "bookId") String bookid) {
		return bookservices.getBooksById(bookid);
	}

	@PostMapping("/save")
	public StubClass saveNewBook(@RequestBody BooksInfo booksInfo) {
		return bookservices.uploadBooks(booksInfo);
	}

	@GetMapping("/ByInstitution")
	public List<BooksInfo> getAllBooksByInstitution(@RequestParam(value = "univid") String univid) {
		return bookservices.getAllBooksByInstitution(univid);
	}

	@GetMapping("/GenreByInstitution")
	public List<StubClass> getAllBooksGenreByInstitution(@RequestParam(value = "institutionId") String institution) {
		return bookservices.getBooksCountByGenreInstitution(institution);
	}

	@GetMapping("/ByAuthor")
	public List<BooksInfo> getAllBooksByAuthor(@RequestParam(value = "authorname") String author,
			@RequestParam(value = "institution_id") String inst_id) {
		return bookservices.getBooksByAuthor(author, inst_id);
	}

	@GetMapping("/ByTitle")
	public List<BooksInfo> getAllBooksByTitle(@RequestParam(value = "title") String title,
			@RequestParam(value = "institution_id") String inst_id) {
		return bookservices.getBooksByTitile(title, inst_id);
	}

	@GetMapping("/GenreAllCount")
	public List<StubClass> getGenreAllCount() {
		return bookservices.getBooksCountByGenre();
	}

	@PostMapping("/ModifyInfo")
	public List<BooksInfo> modifyBookInfo(@RequestBody BooksInfo books) {
		return bookservices.updateBooksInfo(books);
	}

	@PostMapping("/Remove")
	protected boolean removeBook(@RequestParam(value = "bookId") List<String> bookids) {
		return bookservices.removeBooks(bookids);
	}

	@GetMapping("/Randombooks")
	public List<BooksInfo> checkServices() {
		return bookservices.getRandomBooks();
	}

	@GetMapping("/ByGenre")
	public List<BooksInfo> searchByGenre(@RequestParam(value = "genre") String bookGenre,
			@RequestParam(value = "institution_id") String inst_id) {
		return bookservices.getBooksByGenre(bookGenre, inst_id);
	}
	
	@GetMapping("/TextSearch")
	public List<SearchResultPojo> searchByContent(@RequestParam(value="keywords") List<String> keywords){
		return bookservices.getTextSearch(keywords);
	}
}
