package co.simplon.finalproject2020.controller;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import co.simplon.finalproject2020.model.Demande;
import co.simplon.finalproject2020.model.criteria.DemandeCriteria;
import co.simplon.finalproject2020.model.dto.DemandeDTO;
import co.simplon.finalproject2020.model.dto.TypeDemandeDTO;
import co.simplon.finalproject2020.service.AgentService;
import co.simplon.finalproject2020.service.DemandeService;
import co.simplon.finalproject2020.service.OrigineService;
import co.simplon.finalproject2020.service.TypeDemandeService;

@RestController
@RequestMapping("/demande")
@CrossOrigin(origins = "http://localhost:4200")
public class DemandeController {
	
	@Autowired 
	DemandeService demandeService;
	
	@Autowired 
	AgentService agentService;
	
	@Autowired
	TypeDemandeService typeDemandeService;
	
	@Autowired
	OrigineService origineService;
	
	
	
	
	/**
	 * CRUD (C) => Cr�ation d'un objet Demande en base
	 * 
	 * @param demandeDTO : mod�le repr�sentant le nombre minimal d'informations n�cessaires � l'ajout d'une nouvelle demande en base.
	 * @return l'objet Demande tel qu'ajout� en base.
	 */
	@PostMapping("/save")
	public ResponseEntity<Demande> addDemande(@RequestBody DemandeDTO demandeDTO) {

		try {
			Demande demandeToSave = demandeService.dtoToDemande(demandeDTO);
			System.out.println("demande qu'on vient d'obtenir � partir du DTO" + demandeToSave);
			return new ResponseEntity<Demande>(demandeService.saveDemande(demandeToSave), HttpStatus.CREATED);	// 201	// should throw an error if the id of the author of the publi can't be found in base.
		} catch (Exception e) {
			System.out.println("an error occured");
			return new ResponseEntity<Demande>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	/**
	 * CRUD (R) => Lecture de la table Demande
	 * 
	 * @return la liste de tous les objets Demande situ�s dans la table correspondante
	 */
	@GetMapping("/all")
	public ResponseEntity<List<Demande>> getDemandes() {
		List<Demande> demandes = demandeService.findAll();
		for (Demande demande : demandes) {
			demandeService.RemoveAttachedDocuments(demande);
		}
		return new ResponseEntity<List<Demande>>(demandes, HttpStatus.OK);	// eventually replace that endpoint with last 2 months demands																						
	}
	
	
	/**
	 * CRUD (R) => Lecture et recherche par crit�res
	 * 
	 * @param criteres : mod�le rassemblant les diff�rents crit�res de recherche que l'utilisateur peut employer, individuellement ou simultan�ment
	 * @return la liste des objets Demande respectant les crit�res re�us.
	 */
	@PostMapping("/all") // with criterias
	public ResponseEntity<List<Demande>> getDemandesWithCrits(@RequestBody DemandeCriteria criteres) {
		System.out.println(criteres);
		List<Demande> demandes = demandeService.findByCriteria(criteres);
		System.out.println(demandes);
		for (Demande demande : demandes) {
			demandeService.RemoveAttachedDocuments(demande);
		}
		return new ResponseEntity<List<Demande>>(demandes, HttpStatus.OK);
	}
	
	
	/**
	 * CRUD (R) => Lecture et recherche par num�ro
	 * 
	 * @param num : le num�ro de la demande
	 * @return
	 */
	@GetMapping("/{num}")
	public ResponseEntity<Demande> getDemande(@PathVariable String num){
		try {
			return new ResponseEntity<Demande>(demandeService.findByNumero(num), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Demande>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	/**
	 * CRUD (U) => Mise � jour des pi�ces jointes
	 * 
	 * @param num
	 * @param files
	 * @return
	 */
	@PostMapping("/documents/{num}")
	public ResponseEntity<Boolean> addDocuments(@PathVariable String num ,@RequestParam("files") List<MultipartFile> files) {
		
		// TODO IN THE SERVICE : search the demande by Num. Instanciate an AttachedDoc for each file on the list and add them to the demande list of Attached Documents. Save&Flush.
		
		try {
			return new ResponseEntity<Boolean>(demandeService.addDocuments(num, files), HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<Boolean>(false, HttpStatus.PAYLOAD_TOO_LARGE);
		}
	}
	
	/**
	 * CRUD (U) => ajout ou mise � jour du responsable de la demande
	 * 
	 * @param idrh : l'idrh du gestionnaire qui se voit attribuer la Demande
	 * @return l'objet Demande mis � jour
	 */
	/**
	 * 
	 * @param idrh : l'idrh du gestionnaire qui se voit attribuer la Demande
	 * @param num
	 * @return l'objet Demande mis � jour
	 */
	@GetMapping("update/{num}/{idrh}")
	public ResponseEntity<Demande> updateResponsable(@PathVariable String idrh, String num){
		return new ResponseEntity<Demande>(null);
	}
	
	
	@PostMapping("update/{num}")
	public ResponseEntity<Demande> updateObjet(@PathVariable String num){
		return new ResponseEntity<Demande>(null);
	}
	
	
	
	
	// Listes d'options � proposer en menu d�roulant
	
	@GetMapping("/origines")
	public ResponseEntity<List<String>> getManualOrigines() {								
		return new ResponseEntity<List<String>>(origineService.findAllNotAuto(), HttpStatus.OK);
	}
	
	@GetMapping("/types")
	public ResponseEntity<List<TypeDemandeDTO>> getTypesAndNatures() {
		try {
			return new ResponseEntity<List<TypeDemandeDTO>>(typeDemandeService.findAllAsDto(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<TypeDemandeDTO>>(HttpStatus.NOT_FOUND);
		}
	}
	
}
