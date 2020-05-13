package co.simplon.finalproject2020.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.simplon.finalproject2020.model.Grade;
import co.simplon.finalproject2020.repository.GradeDAO;
import co.simplon.finalproject2020.service.GradeService;

@Service
public class GradeServiceImpl implements GradeService {

	@Autowired
	private GradeDAO gradeDAO;
	
	@Override
	public List<String> findAllCodes() {
		List<String> gradeCodesAsStrings = new ArrayList<String>();
		for (Grade grade : this.gradeDAO.findAll()) {
			gradeCodesAsStrings.add(grade.getCode());
		}
		return gradeCodesAsStrings;
	}
	
	@Override
	public Grade findByCode(String code) {
		return this.gradeDAO.findByCode(code);
	}

	@Override
	public String findCodeLibelle(String code) {
		System.out.println("we entered GradeServiceImpl's findCodeLibelle's method with the argument code = " + code);
		return this.gradeDAO.findByCode(code).getLibelle(); // working as intended
	}

	
}
