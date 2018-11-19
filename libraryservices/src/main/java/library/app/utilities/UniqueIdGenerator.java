package library.app.utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.RandomStringUtils;

public class UniqueIdGenerator {

	// Random UserID
	public static String getRandomUserID(String fName, String lName) {
		StringBuilder userID = new StringBuilder();
		String randomAlphaNumeric = RandomStringUtils.randomAlphabetic(6);
		userID.append(lName.substring(0, 2));
		userID.append(randomAlphaNumeric);
		userID.append(fName.substring(0, 2));
		return userID.toString();
	}

	// Random BookID
	public static String getRandomBookID() {
		StringBuilder expensesID = new StringBuilder();
		String randamAlphaNumeric = RandomStringUtils.randomAlphabetic(6);
		expensesID.append("BK");
		expensesID.append(randamAlphaNumeric);
		return expensesID.toString();
	}

	// Random TransactionID
	public static String getRandomTranxID() {
		StringBuilder expensesID = new StringBuilder();
		String randamAlphaNumeric = RandomStringUtils.randomAlphabetic(6);
		expensesID.append("TRX");
		expensesID.append(randamAlphaNumeric);
		return expensesID.toString();
	}

	// Random UniversityID
	public static String getRandomUniversityID(String schoolName) {
		StringBuilder expensesID = new StringBuilder();
		String randamAlphaNumeric = RandomStringUtils.randomAlphabetic(6);
		expensesID.append("IN").append(schoolName).subSequence(0, 3);
		expensesID.append(randamAlphaNumeric);
		return expensesID.toString();
	}

	// Current Date
	public static String getCurrentDate() {
		SimpleDateFormat currentDate = new SimpleDateFormat("YYYY-MM-dd");
		Date date = new Date();
		return currentDate.format(date).toString();
	}

	// Current date and time
	public static String getCurrentDateAndTime() {
		SimpleDateFormat currentDateTime = new SimpleDateFormat("YYYY-MM-dd hh:mm:ss");
		Date date = new Date();
		return currentDateTime.format(date).toString();

	}

}
