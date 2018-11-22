package library.LibraryTextSearch;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import library.daoservice.SearchCorpus;
import library.pojo.HiveConnectionProperties;

@RestController
public class DocumentSearchService {

	@Autowired
	library.pojo.SearchResultPojo resultdata;
	
	@Autowired
	SearchCorpus searchCorpus;

	@RequestMapping("/library/search")
	public HashMap<String, List<library.pojo.SearchResultPojo>> getSearchResult(@RequestParam(value = "keywords") List<String> data)
			throws SQLException {

		return searchCorpus.searchInCorpus(data);
	}

}
