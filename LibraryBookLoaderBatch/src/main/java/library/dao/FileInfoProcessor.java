package library.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mysql.jdbc.PreparedStatement;

@Service
public class FileInfoProcessor {

	@Autowired
	DaoProperties dbProperties;

	public FileInfoProcessor() {
		System.out.println("Database file collector started");
	}

	private final String new_file_query = "SELECT file_log_id,book_id,repo_path,status FROM file_action_logger WHERE status=?";
	private final String processed_file_update_query = "UPDATE file_action_logger SET status=? WHERE file_log_id=?";
	private final String book_info_update_query = "UPDATE books_info SET status=? where book_id=?";

	public List<FileLoggerPojo> getNewFileList() throws SQLException, Exception {
		List<FileLoggerPojo> data = new ArrayList<>();
		System.err.println("Getting new file Info");
		PreparedStatement preparedStatement = (PreparedStatement) dbProperties.getConnection()
				.prepareStatement(new_file_query);
		preparedStatement.setString(1, "NEW");
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			FileLoggerPojo bookdata = new FileLoggerPojo();
			bookdata.setBook_id(resultSet.getString("book_id"));
			bookdata.setFile_log_id(resultSet.getString("file_log_id"));
			bookdata.setRepo_path(resultSet.getString("repo_path"));
			bookdata.setStatus(resultSet.getString("status"));
			data.add(bookdata);
		}

		return data;
	}

	public void setBookStatus(List<FileLoggerPojo> bookprocessresult) throws SQLException, Exception {
		System.err.println("Getting new file Info");
		PreparedStatement preparedStatement1 = (PreparedStatement) dbProperties.getConnection()
				.prepareStatement(processed_file_update_query);
		PreparedStatement preparedStatement2 = (PreparedStatement) dbProperties.getConnection()
				.prepareStatement(book_info_update_query);
		for (FileLoggerPojo details : bookprocessresult) {
			preparedStatement1.setString(1, "COMPLETED");
			preparedStatement1.setString(2, details.getFile_log_id());
			int rows = preparedStatement1.executeUpdate();
			if (rows >= 1) {
				preparedStatement2.setString(1, "AVAILABLE");
				preparedStatement2.setString(2, details.getBook_id());
				preparedStatement2.executeUpdate();

			}
		}

	}
}
