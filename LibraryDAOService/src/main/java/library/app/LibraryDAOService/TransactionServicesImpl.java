package library.app.LibraryDAOService;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import library.app.dao.model.StubClass;
import library.app.dao.model.books_info;
import library.app.dao.model.library_transactions;
import library.app.services.BookServices;
import library.app.services.BookTransactionService;
import library.app.utilities.AppUtils;
import library.app.utilities.UniqueIdGenerator;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/library/transactions")
public class TransactionServicesImpl {
	public TransactionServicesImpl() {
	}
@Override
public String toString() {
	// TODO Auto-generated method stub
	return super.toString();
}

	@Autowired
	BookTransactionService transactionService;
	
	@Autowired
	BookServices bookservices;

	@PostMapping("/save")
	public ResponseEntity<library_transactions> saveTransaction(@Valid @RequestBody library_transactions trans) {
		trans.setTransaction_id(UniqueIdGenerator.getRandomTranxID());
		library_transactions trans1 = transactionService.save(trans);

		if (!trans1.equals(null)) {
			return new ResponseEntity<library_transactions>(trans1, HttpStatus.OK);
		}
		return null;
	}

	@GetMapping("/get")
	public List<books_info> getTransactionById(@RequestParam(name = "user_id") String user_id)
			throws NoSuchElementException {
		List<library_transactions> transdata = new ArrayList<>(transactionService.getBooksInfo(user_id)); 
		System.err.println("result size" + transdata.size());
		HashMap<String, String> data = new HashMap<>();
		for(library_transactions info:transdata) {
			data.put(info.getTransaction_id(), info.getBook_id());
		}
		List<String> transBookDetails = new ArrayList<>(data.values());
		List<books_info> response = bookservices.findAllById(transBookDetails);
		if (!transdata.isEmpty()) {
			return response;
		}

		return null;
	}

	@GetMapping("/getCountByInstitution")
	public StubClass getTransactionByInstitution(@RequestParam("institution_id") String institution_id)
			throws NoSuchElementException {

		String[] data = transactionService.getTotalTransactionPerInstitute(institution_id);
		StubClass response = new StubClass();
		response.setKey(data[0]);
		response.setValue(data[1]);
		return response;
	}

	@GetMapping("/getAllByGenre")
	public List<StubClass> getTransactionByGenre(@RequestParam("institution_id") String institution_id)
			throws NoSuchElementException {
		String[] data = transactionService.getBooksGenresPerInstitution(institution_id);
		List<StubClass> responseData = new ArrayList<>();
		for (String array : data) {
			String[] values = array.split(",");
			StubClass keyval = new StubClass();
			keyval.setKey(values[0]);
			keyval.setValue(values[1]);
			responseData.add(keyval);
		}
		return responseData;
	}

	@GetMapping("/getVolumeByDate")
	public List<StubClass> getTransactinsByDate(@RequestParam("institution_id") String institution_id)
			throws Exception {
		List<StubClass> response = new ArrayList<>();
		SimpleDateFormat sqlDate = new SimpleDateFormat("yyyy-MM-dd");
		java.sql.Date startDate = null;
		java.sql.Date endDate = null;
		if (institution_id.equalsIgnoreCase("kmax")) {
			HashMap<Integer, String> weeks = AppUtils.getWeeks();
			for (int k = 1; k < 5; k++) {
				String[] days = weeks.get(k).split("/");
				StubClass responsepart = new StubClass();
				responsepart.setKey("week" + String.valueOf(k));
				startDate = new Date(sqlDate.parse(days[0]).getTime());
				endDate = new Date(sqlDate.parse(days[1]).getTime());
				responsepart
						.setValue(String.valueOf(transactionService.getTransactionVolumeByWeek(startDate, endDate)));
				response.add(responsepart);
			}
		} else {
			HashMap<Integer, String> weeks = AppUtils.getWeeks();
			for (int k = 1; k < 5; k++) {
				String[] days = weeks.get(k).split("/");
				StubClass responsepart = new StubClass();
				responsepart.setKey("week" + String.valueOf(k));
				startDate = new Date(sqlDate.parse(days[0]).getTime());
				endDate = new Date(sqlDate.parse(days[1]).getTime());
				int totalforinstitution = transactionService.getTotalTransactionPerInstitute1(institution_id, startDate, endDate);
				int totalfornoninstituon= transactionService.getTotlTransactionNonInstitute(institution_id, startDate, endDate);
				System.out.println(totalforinstitution+"///"+totalfornoninstituon);
				responsepart.setValue(String.valueOf(totalforinstitution)+","+String.valueOf(totalfornoninstituon));
				response.add(responsepart);
			}
		}

		return response;
	}
	
}
