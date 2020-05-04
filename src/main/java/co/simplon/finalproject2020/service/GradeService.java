package co.simplon.finalproject2020.service;

import java.util.List;

import co.simplon.finalproject2020.model.Grade;

public interface GradeService {
	
	public List<String> findAllCodes();

	public String findCodeLibelle(String code);

	public Grade findByCode(String code);
}
