package co.simplon.finalproject2020.service;

import java.util.List;

import org.springframework.stereotype.Service;

import co.simplon.finalproject2020.model.ChangementStatut;

@Service
public interface ChangementStatutService {
	
	public List<ChangementStatut> findAll();
	
	public void save(ChangementStatut cs);

}
