package library.daoservice;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;


public interface SearchCorpus {
public List<library.pojo.SearchResultPojo> searchInCorpus(List<String> data) throws SQLException;
}
