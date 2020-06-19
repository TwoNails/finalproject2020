package co.simplon.finalproject2020.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

import co.simplon.finalproject2020.model.AttachedDocument;
import co.simplon.finalproject2020.model.Demande;
import co.simplon.finalproject2020.model.Statut;
import co.simplon.finalproject2020.model.criteria.DemandeCriteria;
import co.simplon.finalproject2020.model.dto.DemandeDTO;
import co.simplon.finalproject2020.model.dto.TypeDemandeDTO;
import co.simplon.finalproject2020.service.AgentService;
import co.simplon.finalproject2020.service.BrancheService;
import co.simplon.finalproject2020.service.DemandeService;
import co.simplon.finalproject2020.service.DocumentService;
import co.simplon.finalproject2020.service.ExcelService;
import co.simplon.finalproject2020.service.OrigineService;
import co.simplon.finalproject2020.service.StatutService;
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
	
	@Autowired
	StatutService statutService;
	
	@Autowired
	BrancheService brancheService;
	
	@Autowired
	DocumentService documentService;
	
	@Autowired
	ExcelService excelService;
	
	
	
	// M�thodes CRUD sur l'entit� Demande

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
			Demande demande = demandeService.findByNumero(num);
			demandeService.RemoveAttachedDocuments(demande);
			return new ResponseEntity<Demande>(demande, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Demande>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	
	/**
	 * CRUD (U) => ajout ou mise � jour du responsable de la demande
	 * 
	 * @param num : le num�ro de la demande qui sera modifi�e
	 * @param idrh : l'idrh du gestionnaire qui se voit attribuer la Demande
	 * @return l'objet Demande mis � jour
	 */
	
	@GetMapping("update/{num}/{idrh}")
	public ResponseEntity<Demande> updateResponsable(@PathVariable String num, @PathVariable String idrh){
		System.out.println("on entre dans le controller avec num = " + num + ", idrh = " + idrh);
		try {
			return new ResponseEntity<Demande>(demandeService.assign(num, idrh), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * CRUD (U) => ajout ou mise � jour d'un commentaire
	 * 
	 * @param num : le num�ro de la demande qui sera modifi�e
	 * @param commentaire : la nouvelle valeur du champ commentaire
	 * @return l'objet Demande mis � jour
	 */
	@PostMapping("update/{num}")
	public ResponseEntity<Demande> updateObjet(@PathVariable String num, @RequestBody String commentaire){
		try {
			return new ResponseEntity<Demande>(demandeService.updateComment(num, commentaire), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/close/{num}")
	public ResponseEntity<Demande> closeDemande(@PathVariable String num) {
		try {
			return new ResponseEntity<Demande>(demandeService.closeDemande(num), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Demande>(HttpStatus.NOT_FOUND);
		}
		
	}
	
	/**
	 * CRUD (D) => suppression d'une demande. (� remplacer par un changement de Statut => clotur�e ?) 
	 * @param num : le num�ro de la demande qui sera modifi�e
	 * @return void
	 */
	@GetMapping("delete/{num}")
	public ResponseEntity<?> delete(@PathVariable String num){
		try {
			demandeService.delete(num);
			return ResponseEntity.noContent().build();
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	
	
	
	/**********************************************************************************************************************************************************************/
	
	
	// M�thodes de traitement des documents. 
	//  - Lecture et �criture en base (Pi�ces jointes des demandes)
	//	- G�n�ration (Excel)
	
	/**
	 * CRUD (R) => T�l�chargement d'une pi�ce jointe
	 * 
	 * @param num
	 * @param nomdoc
	 * @return
	 */
	@GetMapping("/documents/{num}/{docname}")
	public ResponseEntity<ByteArrayResource> getDocument(@PathVariable String num, @PathVariable String docname){
		AttachedDocument docInBase = documentService.findByDemandeAndName(num, docname);
		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType(docInBase.getFileExtension()))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" +docInBase.getName() + "\"")
				.body(new ByteArrayResource(docInBase.getContent()));
	}
	
	
	/**
	 * CRUD (C/U) => Ajout / Mise � jour des pi�ces jointes
	 * 
	 * @param num
	 * @param files
	 * @return
	 */
	@PostMapping("/documents/{num}")
	public ResponseEntity<Boolean> addDocuments(@PathVariable String num ,@RequestParam("files") List<MultipartFile> files) {
				
		try {
			return new ResponseEntity<Boolean>(demandeService.addDocuments(num, files), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Boolean>(false, HttpStatus.PAYLOAD_TOO_LARGE);
		}
	}
	
	

	
	@PostMapping("/excel/demandesearch")
	public ResponseEntity<InputStreamResource> getExcelFromSearchResults(@RequestBody DemandeCriteria criteres){	
		List<Demande> listDemandes = demandeService.findByCriteria(criteres);
		
		String filename = "RechercheDemande_" + LocalDate.now().getDayOfMonth() + "" + LocalDate.now().getMonthValue() + ".xlsx";
		
		HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename);
        
        try {
        ByteArrayInputStream baisBody = excelService.excelFileFromDemandeSearch(listDemandes);
		
			return ResponseEntity.ok()
					.headers(headers)
					.body(new InputStreamResource(baisBody));
					
		} catch (IOException e) {
			return new ResponseEntity<InputStreamResource>(HttpStatus.NOT_FOUND);
		}
	}
	
	/**********************************************************************************************************************************************************************/
	
	
	// Methodes de lecture destin�es � produire des listes d'options � proposer en menu d�roulant
	// Dans le cas des pi�ces jointes, liste des noms des pi�ces en base
	
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
	
	@GetMapping("/statuts")
	public ResponseEntity<List<String>> getStatuts() {
		return new ResponseEntity<List<String>>(statutService.findAll(), HttpStatus.OK);
	}
	
	@GetMapping("/branches")
	public ResponseEntity<List<String>> getBranches() {
		return new ResponseEntity<List<String>>(brancheService.findAll(), HttpStatus.OK);
	}
	
	@GetMapping("/documents/{numeroDemande}")
	public ResponseEntity<List<String>> getDocuments(@PathVariable String numeroDemande) {
		return new ResponseEntity<List<String>>(documentService.findNamesByDemandeNumero(numeroDemande), HttpStatus.OK);
	}
	
	
	
	
	/*****************************************************************************************************************************************************************************/
	
}
