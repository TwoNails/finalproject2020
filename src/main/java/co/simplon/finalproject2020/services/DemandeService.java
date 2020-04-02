package co.simplon.finalproject2020.services;

import co.simplon.finalproject2020.model.Demande;
import co.simplon.finalproject2020.model.dto.DemandeDTO;

public interface DemandeService {
	
	// CRUD
	
	public Demande findById(int id) throws Exception;
	
	public Demande findByNumero(String numero) throws Exception;
	
	public Demande saveDemande(Demande demande) throws Exception;
	
	// UTILS
	
	public Demande dtoToDemande(DemandeDTO demandeDTO) throws Exception;

}
