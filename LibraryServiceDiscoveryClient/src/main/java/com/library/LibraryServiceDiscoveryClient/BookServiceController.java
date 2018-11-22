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
import com.library.pojo.StubClass;
import com.library.service.BookServices;

@CrossOrigin(origins="*",allowedHeaders="*")
@RestController
@RequestMapping("/library/book")
public class BookServiceController {

	@Autowired
	BookServices bookservices;

	@GetMapping("/getBook")
	public BooksInfo getBook(@RequestParam(value = "bookId") String bookid) {
		return bookservices.getBooksById(bookid);
	}
	
	@PostMapping("/save")
	public List<BooksInfo> saveNewBook(@RequestBody List<BooksInfo> booksInfo){
		return bookservices.uploadBooks(booksInfo);
	}

	@GetMapping("/ByInstitution")
	public List<BooksInfo> getAllBooksByInstitution(@RequestParam(value = "univid") String univid) {
		return bookservices.getAllBooksByInstitution(univid);
	}

	@GetMapping("/GenreByInstituion")
	public List<StubClass> getAllBooksGenreByInstitution(@RequestParam(value = "instituionId") String institution) {
		return bookservices.getBooksCountByGenreInstitution(institution);
	}

	@GetMapping("/ByAuthor")
	public List<BooksInfo> getAllBooksByAuthor(@RequestParam(value = "authorname") String author) {
		return bookservices.getBooksByAuthor(author);
	}

	@GetMapping("/ByTitle")
	public List<BooksInfo> getAllBooksByTitle(@RequestParam(value = "title") String title) {
		return bookservices.getBooksByTitile(title);
	}

	@GetMapping("/GenreAllCount")
	public List<StubClass> getGenreAllCount() {
		return bookservices.getBooksCountByGenre();
	}
	
	@PostMapping("/ModifyInfo")
	public List<BooksInfo> modifyBookInfo(@RequestParam(value="modify") List<BooksInfo> books){
		return bookservices.updateBooksInfo(books);
	}
	
	@PostMapping("/Remove")
	protected boolean removeBook(@RequestParam(value="bookId") List<String> bookids){
		return bookservices.removeBooks(bookids);
		}
	@GetMapping("/check")
	public String checkServices(@RequestParam(value="check")String check) {
		return check;
	}
	
	}
