package com.library.search;

import java.io.Serializable;

public class TableSchema implements Serializable{
	private String bookid, words;
	private float df,idf,tf_idf,count;
	public String getBookid() {
		return bookid;
	}
	public void setBookid(String bookid) {
		this.bookid = bookid;
	}
	public String getWords() {
		return words;
	}
	public void setWords(String words) {
		this.words = words;
	}
	public float getDf() {
		return df;
	}
	public void setDf(float df) {
		this.df = df;
	}
	public float getIdf() {
		return idf;
	}
	public void setIdf(float idf) {
		this.idf = idf;
	}
	public float getTf_idf() {
		return tf_idf;
	}
	public void setTf_idf(float tf_idf) {
		this.tf_idf = tf_idf;
	}
	public float getCount() {
		return count;
	}
	public void setCount(float count) {
		this.count = count;
	}
}
