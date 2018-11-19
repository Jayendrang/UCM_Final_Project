package com.library.service;

import java.util.List;

import com.library.pojo.BooksInfo;
import com.library.pojo.StubClass;

public interface BookServices {
	public BooksInfo getBooksById(String bookId);
	public List<BooksInfo> uploadBooks(List<BooksInfo> books);
	public boolean removeBooks(List<String> books);
	public List<BooksInfo> updateBooksInfo(List<BooksInfo> books);
	public List<BooksInfo> getAllBooksByInstitution(String univId);
	public List<BooksInfo> getBooksByAuthor(String name);
	public List<BooksInfo> getBooksByTitile(String title);
	public List<StubClass> getBooksCountByGenreInstitution(String instituteId);
	public List<StubClass> getBooksCountByGenre();
}
