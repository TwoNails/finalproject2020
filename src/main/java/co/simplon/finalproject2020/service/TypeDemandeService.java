package co.simplon.finalproject2020.service;

import java.util.List;

import co.simplon.finalproject2020.model.TypeDemande;
import co.simplon.finalproject2020.model.dto.TypeDemandeDTO;

public interface TypeDemandeService {
	
	// CRUD
	public List<TypeDemande> findAll();
	
	public List<TypeDemandeDTO> findAllAsDto() throws Exception;
	
	// UTILS
	public TypeDemandeDTO TypeToDto(TypeDemande typeDemande) throws Exception;
}
