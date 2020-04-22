package co.simplon.finalproject2020.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import co.simplon.finalproject2020.model.Demande;
import co.simplon.finalproject2020.model.criteria.DemandeCriteria;
import co.simplon.finalproject2020.model.dto.DemandeDTO;

public interface DemandeService {
	
	// CRUD
	
	public List<Demande> findAll(); 
	
	public List<Demande> practiceCriteria(LocalDate fromDate, LocalDate toDate);
	
	public List<Demande> findByCriteria(DemandeCriteria criteres);
	
	public Demande findById(int id) throws Exception;
	
	public Demande findByNumero(String numero) throws Exception;
	
	public Demande saveDemande(Demande demande) throws Exception;
	
	public boolean addDocuments(String numero, List<MultipartFile> files) throws Exception;
	
	
	
	
	// UTILS
	
	/**
	 * 
	 * @param demandeDTO The Demande Data Transfer Object containing the minimal data necessary to the creation of a new Demande entry.
	 * @return a Demande Object. As an Entity, this Object can be saved in the database.
	 * @throws Exception
	 */
	public Demande dtoToDemande(DemandeDTO demandeDTO) throws Exception;
	
	/**
	 * 
	 * @param demandeToSlim The Demande that we want stripped of all of its AttachedDocuments.
	 * @return a Demande Object identical in all aspects, except without any AttachedDocuments. 
	 */
	public Demande RemoveAttachedDocuments(Demande demandeToSlim);

}
