package co.simplon.finalproject2020.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.finalproject2020.model.Demande;
import co.simplon.finalproject2020.model.dto.DemandeDTO;
import co.simplon.finalproject2020.services.AgentServiceImpl;
import co.simplon.finalproject2020.services.DemandeServiceImpl;

@RestController
@RequestMapping("/demande")
@CrossOrigin(origins = "http://localhost:4200")
public class DemandeController {
	
	@Autowired 
	DemandeServiceImpl demandeService;
	
	@Autowired 
	AgentServiceImpl agentService;
	
	@PostMapping("/save")
	public ResponseEntity<Demande> addPublication(@RequestBody DemandeDTO demandeDTO) {
		try {
			// add the line that calls switch DTO to Demand on demandeDTO then use that as the parameter for the method below
			Demande demandeToSave = demandeService.dtoToDemande(demandeDTO);
			return new ResponseEntity<Demande>(demandeService.saveDemande(demandeToSave), HttpStatus.CREATED);	// 201	// should throw an error if the id of the author of the publi can't be found in base.
		} catch (Exception e) {														
			return new ResponseEntity<Demande>(HttpStatus.FORBIDDEN);
		}
		
	}
	
}
