package library.app.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND)
public class BookExceptions extends RuntimeException {
	private String bookname;
	private String institutionname;
	private String repoPath;

	public BookExceptions(String bookName, String instName, String repo) {
		this.bookname = bookName;
		this.institutionname = instName;
		this.repoPath = repo;
	}
	public String getBookname() {
		return bookname;
	}

	public String getInstitutionname() {
		return institutionname;
	}

	public String getRepoPath() {
		return repoPath;
	}

}
