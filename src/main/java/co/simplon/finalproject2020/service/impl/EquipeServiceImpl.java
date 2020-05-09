package co.simplon.finalproject2020.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.simplon.finalproject2020.model.Equipe;
import co.simplon.finalproject2020.repository.EquipeDAO;
import co.simplon.finalproject2020.service.EquipeService;

@Service
public class EquipeServiceImpl implements EquipeService {

	@Autowired
	private EquipeDAO equipeDAO;
	
	@Override
	public List<String> findAll() {
		List<String> EquipesAsStrings = new ArrayList<String>();
		for (Equipe equipe : equipeDAO.findAll()) {
			EquipesAsStrings.add(equipe.getLibelle());
		}
		return EquipesAsStrings;
	}

}
