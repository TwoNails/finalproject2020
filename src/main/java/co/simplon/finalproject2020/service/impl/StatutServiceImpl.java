package co.simplon.finalproject2020.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.simplon.finalproject2020.model.Statut;
import co.simplon.finalproject2020.repository.StatutDAO;
import co.simplon.finalproject2020.service.StatutService;

@Service
public class StatutServiceImpl implements StatutService {
	
	@Autowired
	private StatutDAO statutDAO;
	
	@Override
	public List<String> findAll() {
		List<String> statutsAsStrings = new ArrayList<String>();
		for (Statut statut : this.statutDAO.findAll()) {
			statutsAsStrings.add(statut.getLibelle());
		}
		return statutsAsStrings;
	}

}
