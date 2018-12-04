package com.library.service;

import java.util.HashMap;
import java.util.List;

import com.library.pojo.StubClass;
import com.library.pojo.Transactions;

public interface LibraryTransactionService {
	public Transactions saveLibraryTransaction(Transactions trans);
	public List<Transactions> getAllTransaction(String userId);
	public StubClass getTransactionStatsByInstitution(String institutionId);
	public List<StubClass> getTransactionByGenre(String institutionId);
	public List<StubClass> getTransactionByVolume(String institutionId);
}
