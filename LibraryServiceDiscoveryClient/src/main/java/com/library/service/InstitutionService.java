package com.library.service;

import java.util.List;

import com.library.pojo.InstitutionInfo;
import com.library.pojo.StubClass;

public interface InstitutionService {
	public InstitutionInfo getInstitutionInfo(String id);

	public List<InstitutionInfo> getAllInstitutionInfo();

	public InstitutionInfo saveNewInstitution(InstitutionInfo info);

	public boolean updateInstitutionStatus(String status);

	public InstitutionInfo updateInstitutionProfile(InstitutionInfo info);
	
	public StubClass getTotalCount();
}
