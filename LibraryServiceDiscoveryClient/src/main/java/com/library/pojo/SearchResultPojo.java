package com.library.pojo;

import org.springframework.stereotype.Component;

@Component
public class SearchResultPojo {
	private String bookid,tf_idf;

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
