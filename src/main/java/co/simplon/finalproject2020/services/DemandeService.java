package co.simplon.finalproject2020.services;

import co.simplon.finalproject2020.model.Demande;

public interface DemandeService {
	
	public Demande findById(int id) throws Exception;
	
	public Demande findByNumero(String numero) throws Exception;
	
	public Demande saveDemande(Demande demande) throws Exception;

}
