package library.app.LibraryService;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import library.app.dao.model.StubClass;
import library.app.dao.model.library_transactions;
import library.app.services.BookTransactionService;

@RestController
@RequestMapping("/library/booktransactions")
public class TransactionServicesImpl {
	
	
	public TransactionServicesImpl() {
	}
	
	@Autowired
	BookTransactionService transactionService;

	@PostMapping("/save")
	public ResponseEntity<?> saveTransaction(@Valid @RequestBody library_transactions trans) {
		library_transactions trans1 = transactionService.save(trans);
		if (!trans1.equals(null)) {
			return ResponseEntity.ok().build();
		}
		return null;
	}

	@GetMapping("/get")
	public List<library_transactions> getTransactionById(@RequestParam(name="user_id") String user_id)
			throws NoSuchElementException {
		System.err.println(user_id+"-----------");
		List<library_transactions> transdata = transactionService.getBooksInfo(user_id);
		if (!transdata.isEmpty()) {
			return transdata;
		}

		return null;
	}

	@GetMapping("/getCount")
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
		for(String array:data) {
			System.err.println(array);
			String[] values = array.split(",");
			StubClass keyval = new StubClass();
			keyval.setKey(values[0]);
			keyval.setValue(values[1]);
			responseData.add(keyval);
		}
		return responseData;
	}
}
