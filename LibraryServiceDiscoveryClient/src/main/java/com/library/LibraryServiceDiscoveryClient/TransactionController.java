package com.library.LibraryServiceDiscoveryClient;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.library.pojo.StubClass;
import com.library.pojo.Transactions;
import com.library.service.LibraryTransactionService;

@CrossOrigin(origins="*",allowedHeaders="*")
@RestController
@RequestMapping("/library/booktrans")
public class TransactionController {

	@Autowired
	LibraryTransactionService transactionService;
	
	@PostMapping("/save")
	public Transactions saveTransactons(@RequestBody Transactions transactions) {
		return transactionService.saveLibraryTransaction(transactions);
	}
	
	@GetMapping("/getAllTransactions")
	public List<Transactions> getAllTransactions(@RequestParam(value="id")String userId){
		return transactionService.getAllTransaction(userId);
	}
	
	@GetMapping("/getStatsInstitution")
	public StubClass getTransactionByInstitution(@RequestParam(value="instituionid")String institution) {
		return transactionService.getTransactionStatsByInstitution(institution);
	}
	
	@GetMapping("/getStatsGenre")
	public List<StubClass> getTransactionByGenre(@RequestParam(value="institutionid") String inst_id){
		return transactionService.getTransactionByGenre(inst_id);
	}
	
	@GetMapping("/getVolume")
	public List<StubClass> getVolumeofTransaction(@RequestParam("institutionid") String instituteId){
		return transactionService.getTransactionByVolume(instituteId);
	}
}
