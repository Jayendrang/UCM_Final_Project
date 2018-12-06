package library.LibraryTextSearch;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import library.daoservice.SearchCorpus;
import library.pojo.HiveConnectionProperties;

@CrossOrigin(origins="*",allowedHeaders="*")
@RestController
@RequestMapping("/library")
public class DocumentSearchService {

	@Autowired
	library.pojo.SearchResultPojo resultdata;
	
	@Autowired
	SearchCorpus searchCorpus;

	@GetMapping("/textsearch")
	public List<library.pojo.SearchResultPojo> getSearchResult(@RequestParam(value = "keywords") String data)
			throws SQLException {
		String [] input = data.split(" ");
		return searchCorpus.searchInCorpus(Arrays.asList(input));
	}

}
