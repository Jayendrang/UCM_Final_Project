package com.library.search;

import org.springframework.stereotype.Component;

@Component
public class SearchResultPojo {
	private String words,bookid,tf_idf;

	public String getWords() {
		return words;
	}

	public void setWords(String words) {
		this.words = words;
	}

	public String getBookid() {
		return bookid;
	}

	public void setBookid(String bookid) {
		this.bookid = bookid;
	}

	public String getTf_idf() {
		return tf_idf;
	}

	public void setTf_idf(String tf_idf) {
		this.tf_idf = tf_idf;
	}
	
}
