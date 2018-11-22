package com.library.LibraryServiceDiscoveryClient;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.library.pojo.BooksInfo;
import com.library.pojo.StubClass;
import com.library.service.BookServices;

@RestController
@RequestMapping("/library/book")
public class BookServiceController {

	@Autowired
	BookServices bookservices;

	@GetMapping("/Book")
	protected BooksInfo getBook(@RequestParam(value = "bookId") String bookid) {
		return bookservices.getBooksById(bookid);
	}

	@GetMapping("/ByInstitution")
	protected List<BooksInfo> getAllBooksByInstitution(@RequestParam(value = "univid") String univid) {
		return bookservices.getAllBooksByInstitution(univid);
	}

	@GetMapping("/GenreByInstituion")
	protected List<StubClass> getAllBooksGenreByInstitution(@RequestParam(value = "instituionId") String institution) {
		return bookservices.getBooksCountByGenreInstitution(institution);
	}

	@GetMapping("/ByAuthor")
	protected List<BooksInfo> getAllBooksByAuthor(@RequestParam(value = "authorname") String author) {
		return bookservices.getBooksByAuthor(author);
	}

	@GetMapping("/ByTitle")
	protected List<BooksInfo> getAllBooksByTitle(@RequestParam(value = "title") String title) {
		return bookservices.getBooksByTitile(title);
	}

	@GetMapping("/GenreAllCount")
	protected List<StubClass> getGenreAllCount() {
		return bookservices.getBooksCountByGenre();
	}
	
	@PostMapping("/ModifyInfo")
	protected List<BooksInfo> modifyBookInfo(@RequestParam(value="modify") List<BooksInfo> books){
		return bookservices.updateBooksInfo(books);
	}
	
}
