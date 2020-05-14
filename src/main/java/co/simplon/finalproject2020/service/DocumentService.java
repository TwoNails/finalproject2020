package co.simplon.finalproject2020.service;

import java.sql.Blob;
import java.util.List;

import co.simplon.finalproject2020.model.AttachedDocument;

public interface DocumentService {
	
	public List<String> findNamesByDemandeNumero(String numero);
	
	public AttachedDocument findByName(String name);

	public AttachedDocument findByDemandeAndName(String numeroDemande, String nomDocument);
}
