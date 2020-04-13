package co.simplon.finalproject2020.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.simplon.finalproject2020.model.Origine;
import co.simplon.finalproject2020.repository.OrigineDAO;


@Service
public class OrigineServiceImpl implements OrigineService {

	@Autowired
	private OrigineDAO origineDAO;
	
	@Override
	public List<String> findAllNotAuto() {
		List<Origine> listOrigines = origineDAO.findAll();
		List<String> listOriginesAsString = new ArrayList<String>();
		for (Origine origine : listOrigines) {
			if(!origine.getLibelle().equals("BATCH")) {
			listOriginesAsString.add(origine.getLibelle());
			}
		}
		// System.out.println("origineService.findAll has been called. About to return a list of size : " + listOriginesAsString.size());
		return listOriginesAsString;
	}

}
