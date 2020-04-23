package co.simplon.finalproject2020.service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import co.simplon.finalproject2020.model.Agent;
import co.simplon.finalproject2020.model.AttachedDocument;
import co.simplon.finalproject2020.model.Demande;
import co.simplon.finalproject2020.model.Origine;
import co.simplon.finalproject2020.model.TypeDemande;
import co.simplon.finalproject2020.model.Utilisateur;
import co.simplon.finalproject2020.model.criteria.DemandeCriteria;
import co.simplon.finalproject2020.model.dto.DemandeDTO;
import co.simplon.finalproject2020.repository.AgentDAO;
import co.simplon.finalproject2020.repository.AttachedDocumentDAO;
import co.simplon.finalproject2020.repository.CustomCriteriaRepository;
//import co.simplon.finalproject2020.repository.CustomCriteriaRepository;
import co.simplon.finalproject2020.repository.DemandeDAO;
import co.simplon.finalproject2020.repository.OrigineDAO;
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
	
	@Autowired
	private OrigineDAO origineDAO;
	
	@Autowired
	private AttachedDocumentDAO attachedDocumentDAO;
	
	@Autowired
	private CustomCriteriaRepository<Demande> ccRepository; 
	
	/*
	 * Field ccRepository in co.simplon.finalproject2020.service.DemandeServiceImpl required a single bean, but 2 were found:
	- customCriteriaRepositoryDemandeImpl: defined in file [C:\Users\Olivier Piveteau\Dropbox\Programmation\springtoolsuite-workspace\finalproject2020\bin\main\co\simplon\finalproject2020\repository\CustomCriteriaDemandeRepositoryImpl.class]
	- demandeDAO: defined in null

	Action:
	
	Consider marking one of the beans as @Primary, updating the consumer to accept multiple beans, or using @Qualifier to identify the bean that should be consumed
	 * */
	
	@Override
	public List<Demande> findAll() {
		return demandeDAO.findAll();
	}
	
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
		System.out.println(" demande qu'on s'aprete � save : "  + demande);
		try {
			return demandeDAO.saveAndFlush(demande);
		} catch (Exception e) {
			throw e;
		}
	}
	
	
	 // TEST TEST TEST TEST TEST TEST TEST TEST TEST TEST TEST TEST 
	 public List<Demande> practiceCriteria(LocalDate fromDate, LocalDate toDate){
		 return ccRepository.findAllWithCreationDateBetween(fromDate, toDate);
		 // return null;
	 }
	 
	@Override
	public List<Demande> findByCriteria(DemandeCriteria criteres) {
		// return demandeDAO.findAllWithCriteria(criteres);
		return ccRepository.findAllWithCriteria(criteres);
		// return null;
	}
	
	// UTILS

	@Override
	public Demande dtoToDemande(DemandeDTO demandeDTO) throws Exception {
		Optional<Agent> optAgent = agentDAO.findByIdentifiantRH(demandeDTO.getIdrh());
		Optional<TypeDemande> optType = typeDemandeDAO.findByCode(demandeDTO.getCodeType());							// a priori ici on devrait pouvoir se passer d'optionnel
		Optional<Origine> optOrigine = origineDAO.findByLibelle(demandeDTO.getOrigine());
		
		if(!demandeDTO.getMatriculeGestionnaire().equals("")) {
			Optional<Utilisateur> optUtilisateur = utilisateurDAO.findByIdentifiantRH(demandeDTO.getMatriculeGestionnaire());
			if(optAgent.isPresent() && optOrigine.isPresent() && optType.isPresent() && optUtilisateur.isPresent()) {
				
				TypeDemande type = optType.get();
				LocalDate dateEcheance = LocalDate.now().plusDays((long)type.getEcheance());
				
				return new Demande	(	generateNum(type.getNature().getCode()),
										type.getNature(), 
										demandeDTO.getObjet(), 
										LocalDate.now(), 
										dateEcheance,
										new ArrayList<AttachedDocument>(),//demandeDTO.getListeDocuments(), 
										type,
										optOrigine.get(),
										optAgent.get(), 
										optUtilisateur.get());		
			} else {
				throw new Exception("Agent et/ou Gestionnaire introuvable en base");
			}
		} else {	// if MatriculeGestionnaire is equal to an empty String, we use the other constructor that won't try to instanciate it
			
			if(optAgent.isPresent() && optOrigine.isPresent() && optType.isPresent()) {
				TypeDemande type = optType.get();
				LocalDate dateEcheance = LocalDate.now().plusDays((long)type.getEcheance());
				
				return new Demande	(	generateNum(type.getNature().getCode()),
										type.getNature(), 
										demandeDTO.getObjet(), 
										LocalDate.now(), 
										dateEcheance, 
										new ArrayList<AttachedDocument>(),//demandeDTO.getListeDocuments(), 
										type,
										optOrigine.get(),
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
		System.out.println("incrementNumBase = " + incrementNumBase);
		
		int numBaseDigits = (int) (Math.log10(incrementNumBase) +1);
		
		for(int i=0; i < (4 - numBaseDigits); i++) {
			numeroDemande += "0";
		}
		numeroDemande += incrementNumBase.toString();
		
		System.out.println("current value of numeroDemande = " + numeroDemande);
		return numeroDemande;
	}

	@Override
	public boolean addDocuments(String numero, List<MultipartFile> files) throws Exception {
		Optional<Demande> optDemande = demandeDAO.findByNumero(numero);
		
		if(optDemande.isPresent()) {
			Demande demandeConcernee = optDemande.get();
			for (MultipartFile file : files) {
				try {
					AttachedDocument docToSave = new AttachedDocument(file.getOriginalFilename(), file.getContentType(), demandeConcernee, file.getBytes());
					attachedDocumentDAO.saveAndFlush(docToSave);
				} catch (IOException e) {
					// TODO: handle exception
				}
				
			}
			return true;
		} else {
			return false;
			// throw new Exception ("Demande introuvable en base");
		}
	}

	@Override
	public Demande RemoveAttachedDocuments(Demande demandeToSlim) {
		demandeToSlim.getListeDocuments().clear();
		return demandeToSlim;
	}

}
