package library.app.utilities;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AppUtils {
	public static JsonNode parseJson(String jsonData) throws Exception {
		JsonFactory jsonFactory = new JsonFactory();
		ObjectMapper objectMapper = new ObjectMapper(jsonFactory);
		JsonNode jsonNode = objectMapper.readTree(jsonData);

		return jsonNode;
	}

	public static HashMap<Integer, String> getWeeks() throws Exception {

		HashMap<Integer, String> queryDates = new HashMap<>();

		if (Calendar.getInstance().get(Calendar.WEEK_OF_MONTH) == 1) {
			String week1 = Calendar.getInstance().get(Calendar.YEAR) + "-"
					+ (Calendar.getInstance().get(Calendar.MONTH) + 1) + "-" + "1" + "/"
					+ Calendar.getInstance().get(Calendar.YEAR) + "-" + (Calendar.getInstance().get(Calendar.MONTH) + 1)
					+ "-" + "7";
			queryDates.put(1, week1);
			String week2 = Calendar.getInstance().get(Calendar.YEAR) + "-"
					+ (Calendar.getInstance().get(Calendar.MONTH)) + "-" + "23" + "/"
					+ Calendar.getInstance().get(Calendar.YEAR) + "-" + (Calendar.getInstance().get(Calendar.MONTH))
					+ "-" + "31";
			queryDates.put(2, week2);
			String week3 = (Calendar.getInstance().get(Calendar.YEAR) + "-"
					+ (Calendar.getInstance().get(Calendar.MONTH)) + "-" + "15") + "/"
					+ (Calendar.getInstance().get(Calendar.YEAR) + "-" + (Calendar.getInstance().get(Calendar.MONTH))
							+ "-" + "22");
			queryDates.put(3, week3);
			String week4 = (Calendar.getInstance().get(Calendar.YEAR) + "-"
					+ (Calendar.getInstance().get(Calendar.MONTH)) + "-" + "8") + "/"
					+ (Calendar.getInstance().get(Calendar.YEAR) + "-" + (Calendar.getInstance().get(Calendar.MONTH))
							+ "-" + "14");
			queryDates.put(4, week3);

		}
		if (Calendar.getInstance().get(Calendar.WEEK_OF_MONTH) == 2) {
			String week1 = Calendar.getInstance().get(Calendar.YEAR) + "-"
					+ (Calendar.getInstance().get(Calendar.MONTH) + 1) + "-" + "1" + "/"
					+ Calendar.getInstance().get(Calendar.YEAR) + "-" + (Calendar.getInstance().get(Calendar.MONTH) + 1)
					+ "-" + "7";
			queryDates.put(2, week1);
			String week2 = Calendar.getInstance().get(Calendar.YEAR) + "-"
					+ (Calendar.getInstance().get(Calendar.MONTH)) + "-" + "23" + "/"
					+ Calendar.getInstance().get(Calendar.YEAR) + "-" + (Calendar.getInstance().get(Calendar.MONTH))
					+ "-" + "31";
			queryDates.put(3, week2);
			String week3 = (Calendar.getInstance().get(Calendar.YEAR) + "-"
					+ (Calendar.getInstance().get(Calendar.MONTH)) + "-" + "15") + "/"
					+ (Calendar.getInstance().get(Calendar.YEAR) + "-" + (Calendar.getInstance().get(Calendar.MONTH))
							+ "-" + "22");
			queryDates.put(4, week3);
			String week4 = (Calendar.getInstance().get(Calendar.YEAR) + "-"
					+ (Calendar.getInstance().get(Calendar.MONTH) + 1) + "-" + "8") + "/"
					+ (Calendar.getInstance().get(Calendar.YEAR) + "-"
							+ (Calendar.getInstance().get(Calendar.MONTH) + 1) + "-" + "14");
			queryDates.put(1, week4);
		}
		if (Calendar.getInstance().get(Calendar.WEEK_OF_MONTH) == 3) {

			String week1 = Calendar.getInstance().get(Calendar.YEAR) + "-"
					+ (Calendar.getInstance().get(Calendar.MONTH) + 1) + "-" + "1" + "/"
					+ Calendar.getInstance().get(Calendar.YEAR) + "-" + (Calendar.getInstance().get(Calendar.MONTH) + 1)
					+ "-" + "7";
			queryDates.put(3, week1);
			String week2 = Calendar.getInstance().get(Calendar.YEAR) + "-"
					+ (Calendar.getInstance().get(Calendar.MONTH)) + "-" + "23" + "/"
					+ Calendar.getInstance().get(Calendar.YEAR) + "-" + (Calendar.getInstance().get(Calendar.MONTH))
					+ "-" + "31";
			queryDates.put(4, week2);
			String week3 = (Calendar.getInstance().get(Calendar.YEAR) + "-"
					+ (Calendar.getInstance().get(Calendar.MONTH) + 1) + "-" + "15") + "/"
					+ (Calendar.getInstance().get(Calendar.YEAR) + "-"
							+ (Calendar.getInstance().get(Calendar.MONTH) + 1) + "-" + "22");
			queryDates.put(1, week3);
			String week4 = (Calendar.getInstance().get(Calendar.YEAR) + "-"
					+ (Calendar.getInstance().get(Calendar.MONTH) + 1) + "-" + "8") + "/"
					+ (Calendar.getInstance().get(Calendar.YEAR) + "-"
							+ (Calendar.getInstance().get(Calendar.MONTH) + 1) + "-" + "14");
			queryDates.put(2, week4);
		}
		if (Calendar.getInstance().get(Calendar.WEEK_OF_MONTH) == 4) {
			String week1 = Calendar.getInstance().get(Calendar.YEAR) + "-"
					+ (Calendar.getInstance().get(Calendar.MONTH) + 1) + "-" + "1" + "/"
					+ Calendar.getInstance().get(Calendar.YEAR) + "-" + (Calendar.getInstance().get(Calendar.MONTH) + 1)
					+ "-" + "7";
			queryDates.put(4, week1);
			String week2 = Calendar.getInstance().get(Calendar.YEAR) + "-" + Calendar.getInstance().get(Calendar.MONTH)
					+ "-" + "23" + "/" + Calendar.getInstance().get(Calendar.YEAR) + "-"
					+ (Calendar.getInstance().get(Calendar.MONTH)) + "-" + "30";
			queryDates.put(3, week2);
			String week3 = (Calendar.getInstance().get(Calendar.YEAR) + "-"
					+ (Calendar.getInstance().get(Calendar.MONTH) + 1) + "-" + "15") + "/"
					+ (Calendar.getInstance().get(Calendar.YEAR) + "-"
							+ (Calendar.getInstance().get(Calendar.MONTH) + 1) + "-" + "22");
			queryDates.put(2, week3);
			String week4 = (Calendar.getInstance().get(Calendar.YEAR) + "-"
					+ (Calendar.getInstance().get(Calendar.MONTH) + 1) + "-" + "8") + "/"
					+ (Calendar.getInstance().get(Calendar.YEAR) + "-"
							+ (Calendar.getInstance().get(Calendar.MONTH) + 1) + "-" + "14");
			queryDates.put(1, week4);
		}
		if (Calendar.getInstance().get(Calendar.WEEK_OF_MONTH) == 5) {
			String week1 = Calendar.getInstance().get(Calendar.YEAR) + "-"
					+ (Calendar.getInstance().get(Calendar.MONTH) + 1) + "-" + "1" + "/"
					+ Calendar.getInstance().get(Calendar.YEAR) + "-" + (Calendar.getInstance().get(Calendar.MONTH) + 1)
					+ "-" + "7";
			queryDates.put(4, week1);
			String week2 = Calendar.getInstance().get(Calendar.YEAR) + "-" + Calendar.getInstance().get(Calendar.MONTH)
					+ "-" + "23" + "/" + Calendar.getInstance().get(Calendar.YEAR) + "-"
					+ (Calendar.getInstance().get(Calendar.MONTH)) + "-" + "31";
			queryDates.put(3, week2);
			String week3 = (Calendar.getInstance().get(Calendar.YEAR) + "-"
					+ (Calendar.getInstance().get(Calendar.MONTH) + 1) + "-" + "15") + "/"
					+ (Calendar.getInstance().get(Calendar.YEAR) + "-"
							+ (Calendar.getInstance().get(Calendar.MONTH) + 1) + "-" + "22");
			queryDates.put(2, week3);
			String week4 = (Calendar.getInstance().get(Calendar.YEAR) + "-"
					+ (Calendar.getInstance().get(Calendar.MONTH) + 1) + "-" + "8") + "/"
					+ (Calendar.getInstance().get(Calendar.YEAR) + "-"
							+ (Calendar.getInstance().get(Calendar.MONTH) + 1) + "-" + "14");
			queryDates.put(1, week4);
		}

		return queryDates;
	}

}
