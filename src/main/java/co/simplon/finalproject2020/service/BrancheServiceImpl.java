package co.simplon.finalproject2020.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.simplon.finalproject2020.model.Branche;
import co.simplon.finalproject2020.model.Statut;
import co.simplon.finalproject2020.repository.BrancheDAO;
import co.simplon.finalproject2020.repository.StatutDAO;

@Service
public class BrancheServiceImpl implements BrancheService {

	@Autowired
	private BrancheDAO brancheDAO;
	
	@Override
	public List<String> findAll() {
		List<String> branchesAsStrings = new ArrayList<String>();
		for (Branche branche : brancheDAO.findAll()) {
			branchesAsStrings.add(branche.getLibelle());
		}
		return branchesAsStrings;
	}

}
