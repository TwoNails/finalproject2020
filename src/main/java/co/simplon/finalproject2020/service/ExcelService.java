package co.simplon.finalproject2020.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import co.simplon.finalproject2020.model.Demande;

public interface ExcelService {
	
	public ByteArrayInputStream excelFileFromDemandeSearch(List<Demande> demandes) throws IOException;
	
	public ByteArrayInputStream excelFileFromPeriod(LocalDate fromDate, LocalDate toDate) throws IOException, Exception;
	
}
