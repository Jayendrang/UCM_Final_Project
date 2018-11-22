package com.library.search;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DocumentSearchService {

	@Autowired
	SearchResultPojo resultdata;

	@Autowired
	SearchCorpus searchCorpus;

	@RequestMapping("/library/search")
	public HashMap<String, List<SearchResultPojo>> getSearchResult(@RequestParam(value = "keywords") List<String> data)
			throws SQLException {

		return searchCorpus.searchInCorpus(data);
	}

}
