package com.library.remoteservices.repository;

import java.util.HashMap;
import java.util.List;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.library.pojo.StubClass;
import com.library.pojo.Transactions;
import com.library.service.LibraryTransactionService;

public class TransactionRepository implements LibraryTransactionService {

	@Autowired
	private RestTemplate restTemplate;

	protected final String transactionRemoteServiceURL = "http://LIBRARY-DAO-SERIVCE".concat("/library")
			.concat("/transactions");
	private final String saveTransaction = transactionRemoteServiceURL.concat("/save");
	private final String getAllTransctionById = transactionRemoteServiceURL.concat("/get");
	private final String getStatsTransactionByInstitution = transactionRemoteServiceURL
			.concat("/getCountByInstitution");
	private final String getStatsTransactionByGenre = transactionRemoteServiceURL.concat("/getAllByGenre");
	private final String getStatsTransactionVolume = transactionRemoteServiceURL.concat("/getVolumeByDate");

	@Override
	public Transactions saveLibraryTransaction(Transactions trans) {
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(saveTransaction);
		ResponseEntity<Transactions> response = restTemplate.postForEntity(builder.build().toUriString(), trans,
				Transactions.class);
		return response.getBody();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Transactions> getAllTransaction(String userId) {
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(getAllTransctionById).queryParam("user_id",
				userId);
		return restTemplate.getForObject(builder.build().toUriString(), List.class);
	}

	@Override
	public StubClass getTransactionStatsByInstitution(String institutionId) {
		// TODO Auto-generated method stub
		return restTemplate.getForObject(UriComponentsBuilder.fromUriString(getStatsTransactionByInstitution)
				.queryParam("institution_id", institutionId).build().toUriString(), StubClass.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<StubClass> getTransactionByGenre(String institutionId) {
		return restTemplate.getForObject(UriComponentsBuilder.fromUriString(getStatsTransactionByGenre)
				.queryParam("institution_id", institutionId).build().toUriString(), List.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<StubClass> getTransactionByVolume(String institutionId) {
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(getStatsTransactionVolume)
				.queryParam("institution_id", institutionId);
		return (List<StubClass>) restTemplate.getForObject(builder.build().toUriString(), List.class);
	}

}
