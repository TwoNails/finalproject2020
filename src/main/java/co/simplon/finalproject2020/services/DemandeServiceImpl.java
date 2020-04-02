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
	
	private String generateNum(String type, String year) {
		String numeroDemande = "";
		numeroDemande += type;
		numeroDemande += year;
		
		incrementNumBase++;
		
		for(int i=0; i < (4 - incrementNumBase.SIZE); i++) {
			numeroDemande += "0";
		}
		numeroDemande += incrementNumBase.toString();
		
		return numeroDemande;
	}
	
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
	public Demande saveDemande(Demande demande) throws Exception {							// reçoit une nouvelle demande qui n'a pas encore de numéro attribué
		Optional<Demande> optDemande = demandeDAO.findByNumero(demande.getNumero());		// Si la demande existe déj
		return null;
	}

	@Override
	public Demande dtoToDemande(DemandeDTO demandeDTO) throws Exception {
		Optional<Agent> optAgent = agentDAO.findByIdentifiantRH(demandeDTO.getIDRH());
		Optional<TypeDemande> optType = typeDemandeDAO.findByCode(demandeDTO.getLibelleType());							// a priori ici on devrait pouvoir se passer d'optionnel
		
		if(!demandeDTO.getMatriculeGestionnaire().equals("")) {
			Optional<Utilisateur> optUtilisateur = utilisateurDAO.findByMatricule(demandeDTO.getMatriculeGestionnaire());
			if(optAgent.isPresent() && optType.isPresent() && optUtilisateur.isPresent()) {
				
				TypeDemande type = optType.get();
				LocalDate dateEcheance = LocalDate.now().plusDays((long)type.getEcheance());
				
				return new Demande	(	optType.get().getNature(), 
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
				
				return new Demande	(	optType.get().getNature(), 
										OrigineDemande.valueOf(demandeDTO.getOrigine()),
										demandeDTO.getObjet(), 
										LocalDate.now(), 
										dateEcheance, 
										demandeDTO.getListeDocuments(), 
										type, 
										optAgent.get());
			}	
		}


		
		
		//Demande newDemande
		return null;
	}

	

}
