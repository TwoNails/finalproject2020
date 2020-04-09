package co.simplon.finalproject2020.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.simplon.finalproject2020.model.Agent;
import co.simplon.finalproject2020.model.AttachedDocument;
import co.simplon.finalproject2020.model.Demande;
import co.simplon.finalproject2020.model.TypeDemande;
import co.simplon.finalproject2020.model.Utilisateur;
import co.simplon.finalproject2020.model.dto.DemandeDTO;
import co.simplon.finalproject2020.model.enums.NatureTypeDemande;
import co.simplon.finalproject2020.model.enums.OrigineDemande;
import co.simplon.finalproject2020.repository.AgentDAO;
import co.simplon.finalproject2020.repository.DemandeDAO;
import co.simplon.finalproject2020.repository.TypeDemandeDAO;
import co.simplon.finalproject2020.repository.UtilisateurDAO;

@Service
public class DemandeServiceImpl implements DemandeService {
	
	public static Integer incrementNumBase = 0;
	
	
	@Autowired
	private DemandeDAO demandeDAO;
	
	@Autowired 
	private AgentDAO agentDAO;
	
	@Autowired
	private UtilisateurDAO utilisateurDAO;
	
	@Autowired 
	private TypeDemandeDAO typeDemandeDAO;
	
	@Override
	public Demande findById(int id) throws Exception {
		 Optional<Demande> optDemande = demandeDAO.findById(id);
			if(optDemande.isPresent()) {
				return optDemande.get();
			} else {
				throw new Exception("id can't be found in database");
			}
	 }
	
	@Override
	public Demande findByNumero(String numero) throws Exception {
		Optional<Demande> optDemande = demandeDAO.findByNumero(numero);
		if(optDemande.isPresent()) {
			return optDemande.get();
		} else {
			throw new Exception("numero can't be found in database");
		}
	}

	@Override
	public Demande saveDemande(Demande demande) throws Exception {							// re�oit une nouvelle demande qui n'a pas encore de num�ro attribu�
		demande.setNumero(generateNum(demande.getNatureDemande().getCode()));
		return demandeDAO.saveAndFlush(demande);
	}

	@Override
	public Demande dtoToDemande(DemandeDTO demandeDTO) throws Exception {
		Optional<Agent> optAgent = agentDAO.findByIdentifiantRH(demandeDTO.getIdrh());
		Optional<TypeDemande> optType = typeDemandeDAO.findByCode(demandeDTO.getCodeType());							// a priori ici on devrait pouvoir se passer d'optionnel
		
		if(!demandeDTO.getMatriculeGestionnaire().equals("")) {
			Optional<Utilisateur> optUtilisateur = utilisateurDAO.findByIdentifiantRH(demandeDTO.getMatriculeGestionnaire());
			if(optAgent.isPresent() && optType.isPresent() && optUtilisateur.isPresent()) {
				
				TypeDemande type = optType.get();
				LocalDate dateEcheance = LocalDate.now().plusDays((long)type.getEcheance());
				
				return new Demande	(	generateNum(type.getNature().getCode()),
										type.getNature(), 
										OrigineDemande.valueOf(demandeDTO.getOrigine()),
										demandeDTO.getObjet(), 
										LocalDate.now(), 
										dateEcheance, 
										demandeDTO.getListeDocuments(), 
										type, 
										optAgent.get(), 
										optUtilisateur.get());		
			} else {
				throw new Exception("Agent et/ou Gestionnaire introuvable en base");
			}
		} else {	// if MatriculeGestionnaire is equal to an empty String, we use the other constructor that won't try to instanciate it
			
			if(optAgent.isPresent() && optType.isPresent()) {
				TypeDemande type = optType.get();
				LocalDate dateEcheance = LocalDate.now().plusDays((long)type.getEcheance());
				
				return new Demande	(	generateNum(type.getNature().getCode()),
										type.getNature(), 
										OrigineDemande.valueOf(demandeDTO.getOrigine()),
										demandeDTO.getObjet(), 
										LocalDate.now(), 
										dateEcheance, 
										demandeDTO.getListeDocuments(), 
										type, 
										optAgent.get());
			} else {
				throw new Exception("Agent introuvable en base");
			}
		}
	}
	
	public String generateNum(String nature) {
		String numeroDemande = "";
		String year = Integer.toString(LocalDate.now().getYear()).substring(2);
		numeroDemande += nature;
		numeroDemande += year;
		
		incrementNumBase++;
		int numBaseDigits = (int) (Math.log10(incrementNumBase) +1);
		
		for(int i=0; i < (4 - numBaseDigits); i++) {
			numeroDemande += "0";
		}
		numeroDemande += incrementNumBase.toString();
		
		return numeroDemande;
	}

	

}
