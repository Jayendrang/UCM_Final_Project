package library.libraryservicesimpl;

import java.util.List;
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
import org.springframework.web.bind.annotation.RestController;

import library.app.dao.model.books_info;
import library.app.exceptions.BookExceptions;
import library.liberaryservices.BookServices;

@RestController
@RequestMapping("/library/repository")
public class BookServicesImpl {

	@Autowired
	BookServices bookservices;

	//read books list from by institution id
	
	@SuppressWarnings("unchecked")
	@GetMapping("/getbooks/{univId}")
	public List<books_info> getAllBooksByInstitution(@PathVariable(value = "univId") String instituionId) {
		System.err.println("GETBOOKS::BOOKS");
		return (List<books_info>) bookservices.findById(instituionId)
				.orElseThrow(() -> new BookExceptions("", "", "No Books found in repository"));
	}

	//save list of books from by institution id
	
	@PostMapping("/savebooks")
	public List<books_info> uploadBooks(@Valid @RequestBody List<books_info> books) {

		return bookservices.saveAll(books);
	}

	//delete list of books from by institution id
	
	@SuppressWarnings("unchecked")
	@DeleteMapping("/removebooks")
	public List<books_info> removeBooks(@Valid @RequestBody List<books_info> books) {
		for (books_info inbooks : books) {
			books_info tbooks = bookservices.findById(inbooks.getBook_id())
					.orElseThrow(() -> new BookExceptions(inbooks.getBook_name(), inbooks.getInstitution_name(),
							inbooks.getRepo_path()));
			if (!tbooks.getBook_id().isEmpty()) {
				bookservices.deleteById((tbooks.getBook_id()));
			}
		}
		return (List<books_info>) ResponseEntity.ok().build();
	}

	//update books info from by institution id
	
	@PutMapping("/updatebooks")
	public List<books_info> updateBooksInfo(@Valid @RequestBody List<books_info> books) {
		String temp_institution_id=null;
		for (books_info inbooks : books) {
			books_info tbooks = bookservices.findById(inbooks.getBook_id())
					.orElseThrow(() -> new BookExceptions(inbooks.getBook_name(), inbooks.getInstitution_name(),
							inbooks.getRepo_path()));
			temp_institution_id=tbooks.getInstitution_id();
			if (!tbooks.getBook_id().isEmpty()) {
				tbooks.setAuthor(inbooks.getAuthor());
				tbooks.setBook_genre(inbooks.getBook_genre());
				tbooks.setEdition(inbooks.getEdition());
				tbooks.setBook_isbn(inbooks.getBook_isbn());
				tbooks.setBook_name(inbooks.getBook_name());
				bookservices.save(tbooks);
			}
			
		}

		return getAllBooksByInstitution(temp_institution_id);
	}

}
