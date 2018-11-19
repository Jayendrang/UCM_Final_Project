package com.library.load;

import java.io.Serializable;

public class TableSchema implements Serializable{
	private String bookId, words;
	private float tf_count, df_count, idf_count, tf_idf_count;

	public float getTf_count() {
		return tf_count;
	}

	public void setTf_count(float tf_count) {
		this.tf_count = tf_count;
	}

	public float getDf_count() {
		return df_count;
	}

	public void setDf_count(float df_count) {
		this.df_count = df_count;
	}

	public float getIdf_count() {
		return idf_count;
	}

	public void setIdf_count(float idf_count) {
		this.idf_count = idf_count;
	}

	public float getTf_idf_count() {
		return tf_idf_count;
	}

	public void setTf_idf_count(float tf_idf_count) {
		this.tf_idf_count = tf_idf_count;
	}

	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	public String getWords() {
		return words;
	}

	public void setWords(String words) {
		this.words = words;
	}

}
