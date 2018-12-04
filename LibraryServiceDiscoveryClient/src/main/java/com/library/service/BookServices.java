package com.library.service;

import java.util.List;

import javax.naming.directory.SearchResult;

import com.library.pojo.BooksInfo;
import com.library.pojo.SearchResultPojo;
import com.library.pojo.StubClass;

public interface BookServices {
	public List<BooksInfo> getBooksById(String bookId);
	public StubClass uploadBooks(BooksInfo books);
	public boolean removeBooks(List<String> books);
	public List<BooksInfo> updateBooksInfo(BooksInfo books);
	public List<BooksInfo> getAllBooksByInstitution(String univId);
	public List<BooksInfo> getBooksByAuthor(String name,String instituteId);
	public List<BooksInfo> getBooksByTitile(String title,String instituteId);
	public List<StubClass> getBooksCountByGenreInstitution(String instituteId);
	public List<StubClass> getBooksCountByGenre();
	public List<BooksInfo> getRandomBooks();
	public List<BooksInfo> getBooksByGenre(String genre,String instituteId);
	public List<SearchResultPojo> getTextSearch(List<String> input);
}
