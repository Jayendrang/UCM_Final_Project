package library.hdfsmanagement;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import library.dao.FileLoggerPojo;

@Service
public class PdfToTextConverter {

	@Autowired
	HdfsProperties hdfsProperties;

	public List<FileLoggerPojo> convertToText(List<FileLoggerPojo> localFiles) throws Exception {
		
		List<FileLoggerPojo> processedFilePath = new ArrayList<>();

		for (FileLoggerPojo downloadedfiles : localFiles) {
			PDDocument pdfDocumentObject = PDDocument.load(new File(downloadedfiles.getDownload_path()));
			PDFTextStripper textStripper = new PDFTextStripper();
			String convertedPath=hdfsProperties.getLocalConvertedFilePath().concat("/"+downloadedfiles.getBook_id().concat(".txt"));
			textStripper.writeText(pdfDocumentObject,
					new FileWriter(new File(convertedPath)));
			downloadedfiles.setCoverted_path(convertedPath);
			processedFilePath.add(downloadedfiles);
			pdfDocumentObject.close();
		}

		return processedFilePath;
	}
}
