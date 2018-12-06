package com.library.remoteservices.repository;

import java.util.List;

import javax.naming.directory.SearchResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.library.pojo.BooksInfo;
import com.library.pojo.SearchResultPojo;
import com.library.pojo.StubClass;
import com.library.service.BookServices;

public class BookRepository implements BookServices {
	@Autowired
	private RestTemplate restTemplate;

	protected final String bookRemoteServiceURL = "http://LIBRARY-DAO-SERIVCE".concat("/library").concat("/book");
	protected final String bookTextSearchServiceURL = "http://LIBRARY-TEXT-SEARCH".concat("/library");
	
	private final String getBooks = bookRemoteServiceURL.concat("/getbook");
	private final String saveBooks = bookRemoteServiceURL.concat("/savebooks");
	private final String removebooks = bookRemoteServiceURL.concat("/removebooks");
	private final String updatebooks = bookRemoteServiceURL.concat("/updatebooks");
	private final String getBooksByInst = bookRemoteServiceURL.concat("/getBooksByInstitute");
	private final String getBooksByAuthor = bookRemoteServiceURL.concat("/getBooksByAuthor");
	private final String getBooksByTitle = bookRemoteServiceURL.concat("/getBooksByTitle");
	private final String getBooksGenreByInst = bookRemoteServiceURL.concat("/getBooksCountByInstitution");
	private final String getBooksCountGenre = bookRemoteServiceURL.concat("/getAllBooksCountByGenre");
	private final String getRandomBooks = bookRemoteServiceURL.concat("/getRandomBooks");
	private final String getBooksByGenre = bookRemoteServiceURL.concat("/getBooksByGenre");
	
	private final String textSearch = bookTextSearchServiceURL.concat("/textsearch");

	@Override
	public List<BooksInfo> getBooksById(String []bookId) throws NullPointerException {
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(getBooks).queryParam("bookid", bookId);

		return restTemplate.getForObject(builder.build().toUriString(), List.class);
	}

	@Override
	public StubClass uploadBooks(BooksInfo books) {
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(saveBooks);
		return restTemplate.postForObject(builder.build().toUriString(), books, StubClass.class);
	}

	@Override
	public boolean removeBooks(String books) {
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(removebooks).queryParam("bookid", books);
		return restTemplate.postForObject(builder.build().toUriString(), books, boolean.class);
	}

	@Override
	public BooksInfo updateBooksInfo(BooksInfo books) {
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(updatebooks);
		return restTemplate.postForObject(builder.build().toUriString(), books, BooksInfo.class);
	}

	@Override
	public List<BooksInfo> getAllBooksByInstitution(String univId) {
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(getBooksByInst).queryParam("univ_id", univId);
		return restTemplate.getForObject(builder.build().toUriString(), List.class);
	}

	@Override
	public List<BooksInfo> getBooksByAuthor(String name, String inst_id) {
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(getBooksByAuthor).queryParam("name", name)
				.queryParam("institution_id", inst_id);
		return restTemplate.getForObject(builder.build().toUriString(), List.class);
	}

	@Override
	public List<BooksInfo> getBooksByTitile(String title, String inst_id) {
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(getBooksByTitle).queryParam("title", title)
				.queryParam("institution_id", inst_id);
		return restTemplate.getForObject(builder.build().toUriString(), List.class);
	}

	@Override
	public List<StubClass> getBooksCountByGenreInstitution(String instituteId) {
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(getBooksGenreByInst)
				.queryParam("institution_id", instituteId);
		return restTemplate.getForObject(builder.build().toUriString(), List.class);
	}

	@Override
	public List<StubClass> getBooksCountByGenre() {
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(getBooksCountGenre);
		return restTemplate.getForObject(builder.build().toUriString(), List.class);
	}

	@Override
	public List<BooksInfo> getRandomBooks() {
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(getRandomBooks);
		return restTemplate.getForObject(builder.build().toUriString(), List.class);
	}

	@Override
	public List<BooksInfo> getBooksByGenre(String genre, String inst_id) {
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(getBooksByGenre).queryParam("genre", genre)
				.queryParam("institution_id", inst_id);
		return restTemplate.getForObject(builder.build().toUriString(), List.class);
	}

	@Override
	public List<SearchResultPojo> getTextSearch(String input) {
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(textSearch).queryParam("keywords", input);
		return restTemplate.getForObject(builder.build().toUriString(), List.class);
	}

}
