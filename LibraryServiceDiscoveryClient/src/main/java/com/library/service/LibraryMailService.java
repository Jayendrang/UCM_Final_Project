package com.library.service;

import com.library.pojo.MailDetails;
import com.library.pojo.MailResponse;

public interface LibraryMailService {

	public MailResponse sendMailToLibrarian(MailDetails mailInfo);
}
