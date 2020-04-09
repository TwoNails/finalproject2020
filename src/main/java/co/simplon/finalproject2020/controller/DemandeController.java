package co.simplon.finalproject2020.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.finalproject2020.model.Demande;
import co.simplon.finalproject2020.model.dto.DemandeDTO;
import co.simplon.finalproject2020.services.AgentServiceImpl;
import co.simplon.finalproject2020.services.DemandeServiceImpl;
import co.simplon.finalproject2020.services.OrigineServiceImpl;

@RestController
@RequestMapping("/demande")
@CrossOrigin(origins = "http://localhost:4200")
public class DemandeController {
	
	@Autowired 
	DemandeServiceImpl demandeService;
	
	@Autowired 
	AgentServiceImpl agentService;
	
	@Autowired
	OrigineServiceImpl origineService;
	
	@PostMapping("/save")
	public ResponseEntity<Demande> addDemande(@RequestBody DemandeDTO demandeDTO) {

		try {
			Demande demandeToSave = demandeService.dtoToDemande(demandeDTO);		
			return new ResponseEntity<Demande>(demandeService.saveDemande(demandeToSave), HttpStatus.CREATED);	// 201	// should throw an error if the id of the author of the publi can't be found in base.
		} catch (Exception e) {
			return new ResponseEntity<Demande>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/all")
	public ResponseEntity<Set<Demande>> getDemandes() {
		// TODO
		return null;
	}
	
	@GetMapping("/origines")
	public ResponseEntity<List<String>> getManualOrigines() {								
		return new ResponseEntity<List<String>>(origineService.findAll(), HttpStatus.OK);
	}
	
}
