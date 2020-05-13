package co.simplon.finalproject2020.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.simplon.finalproject2020.model.TypeDemande;
import co.simplon.finalproject2020.model.dto.TypeDemandeDTO;
import co.simplon.finalproject2020.repository.TypeDemandeDAO;
import co.simplon.finalproject2020.service.TypeDemandeService;

@Service
public class TypeDemandeServiceImpl implements TypeDemandeService {
	
	@Autowired 
	private TypeDemandeDAO typeDemandeDAO;

	@Override
	public List<TypeDemande> findAll() {
		return typeDemandeDAO.findAll();
	}

	@Override
	public TypeDemandeDTO TypeToDto(TypeDemande typeDemande) throws Exception {
		return new TypeDemandeDTO(typeDemande.getCode(), typeDemande.getLibelle(), typeDemande.getNature().getLibelle());
	}

	@Override
	public List<TypeDemandeDTO> findAllAsDto() throws Exception {
		List<TypeDemande> allTypes = findAll();
		List<TypeDemandeDTO> allTypesAsDto = new ArrayList<TypeDemandeDTO>();
		for (TypeDemande typeDemande : allTypes) {
			allTypesAsDto.add(TypeToDto(typeDemande));
		}
		return allTypesAsDto;
	}

}
