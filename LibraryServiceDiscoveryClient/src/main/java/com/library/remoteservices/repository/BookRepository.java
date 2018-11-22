package com.library.remoteservices.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.library.pojo.BooksInfo;
import com.library.pojo.StubClass;
import com.library.service.BookServices;

public class BookRepository implements BookServices{
	@Autowired
	private RestTemplate restTemplate;
	
	protected final String userRemoteServiceURL = "http://LIBRARY-DAO-SERIVCE".concat("/library").concat("/repository");
	private final String getBooks = userRemoteServiceURL.concat("/getbook");
	private final String saveBooks = userRemoteServiceURL.concat("/savebooks");
	private final String removebooks = userRemoteServiceURL.concat("/removebooks");
	private final String updatebooks = userRemoteServiceURL.concat("/updatebooks");
	private final String getBooksByInst = userRemoteServiceURL.concat("/getBooksByInstitute");
	private final String getBooksByAuthor = userRemoteServiceURL.concat("/getBooksByAuthor");
	private final String getBooksByTitle = userRemoteServiceURL.concat("/getBooksByTitle");
	private final String getBooksGenreByInst = userRemoteServiceURL.concat("/getBooksCountByInstitution");
	private final String getBooksCountGenre = userRemoteServiceURL.concat("/getAllBooksCountByGenre");
	@Override
	public BooksInfo getBooksById(String bookId) {
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(getBooks).queryParam("bookid",bookId);
		return restTemplate.getForObject(builder.build().toUriString(), BooksInfo.class);
	}
	@Override
	public List<BooksInfo> uploadBooks(List<BooksInfo> books) {
			UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(saveBooks);
		return restTemplate.postForObject(builder.build().toUriString(), books, List.class);
	}
	@Override
	public boolean removeBooks(List<String> books) {
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(removebooks);
		return restTemplate.postForObject(builder.build().toUriString(),books, boolean.class);
	}
	@Override
	public List<BooksInfo> updateBooksInfo(List<BooksInfo> books) {
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(updatebooks);
		return restTemplate.postForObject(builder.build().toUriString(), books, List.class);
	}
	@Override
	public List<BooksInfo> getAllBooksByInstitution(String univId) {
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(getBooksByInst).queryParam("univ_id", univId);
		return restTemplate.getForObject(builder.build().toUriString(), List.class);
	}
	@Override
	public List<BooksInfo> getBooksByAuthor(String name) {
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(getBooksByAuthor).queryParam("name", name);
		return restTemplate.getForObject(builder.build().toUriString(), List.class);
	}
	
	@Override
	public List<BooksInfo> getBooksByTitile(String title) {
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(getBooksByTitle).queryParam("title", title);
		return restTemplate.getForObject(builder.build().toUriString(), List.class);
	}
	
	@Override
	public List<StubClass> getBooksCountByGenreInstitution(String instituteId) {
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(getBooksGenreByInst).queryParam("institution_id", instituteId);
		return restTemplate.getForObject(builder.build().toUriString(), List.class);
	}
	@Override
	public List<StubClass> getBooksCountByGenre() {
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(getBooksCountGenre);
		return restTemplate.getForObject(builder.build().toUriString(), List.class);
	}

}
