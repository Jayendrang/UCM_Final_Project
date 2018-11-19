package com.library.search;

import java.util.List;

import com.library.search.SearchResultPojo;
public interface SearchCorpusInterface {
public List<SearchResultPojo> searchInCorpus(List<String> data);
}
