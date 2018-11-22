package com.library.search;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import com.library.search.SearchResultPojo;
public interface SearchCorpus {
public HashMap<String, List<SearchResultPojo>> searchInCorpus(List<String> data) throws SQLException;
}
