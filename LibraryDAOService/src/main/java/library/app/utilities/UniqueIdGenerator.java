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
		StringBuilder randomBookId = new StringBuilder();
		String randamAlphaNumeric = RandomStringUtils.randomAlphabetic(6);
		randomBookId.append("BK");
		randomBookId.append(randamAlphaNumeric);
		return randomBookId.toString();
	}

	// Random TransactionID
	public static String getRandomTranxID() {
		StringBuilder randomTranxID = new StringBuilder();
		String randamAlphaNumeric = RandomStringUtils.randomAlphabetic(6);
		randomTranxID.append("TRX");
		randomTranxID.append(randamAlphaNumeric);
		return randomTranxID.toString();
	}

	// Random UniversityID
	public static String getRandomUniversityID(String schoolName) {
		StringBuilder randInstitutionID = new StringBuilder();
		String randamAlphaNumeric = RandomStringUtils.randomAlphabetic(6);
		randInstitutionID.append("IN");
		randInstitutionID.append(schoolName.substring(0, 3));
		randInstitutionID.append(randamAlphaNumeric);
		return randInstitutionID.toString();
	}
	
	// Random UniversityID
	public static String getReferenceID() {
		StringBuilder referenceID = new StringBuilder();
		String randamAlphaNumeric = RandomStringUtils.randomAlphabetic(6);
		referenceID.append(randamAlphaNumeric);
		return referenceID.toString();
	}
	
	//RandomID Generator
	public static String getRandomID() {
		StringBuilder referenceID = new StringBuilder();
		String randamAlphaNumeric = RandomStringUtils.randomAlphabetic(3);
		referenceID.append("RQ").append(randamAlphaNumeric);
		return referenceID.toString();
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
