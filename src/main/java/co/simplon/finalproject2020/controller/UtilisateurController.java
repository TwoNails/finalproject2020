package co.simplon.finalproject2020.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.finalproject2020.model.JsonWebToken;
import co.simplon.finalproject2020.model.Utilisateur;
import co.simplon.finalproject2020.service.UserService;
import co.simplon.finalproject2020.service.UtilisateurService;

@RestController
@RequestMapping("/utilisateur")
@CrossOrigin(origins = "http://localhost:4200")
public class UtilisateurController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UtilisateurService utilisateurService;

	
	// SECURITY RELATED
	
	@PostMapping("/save")
	public ResponseEntity<Utilisateur> saveNew (@RequestBody Utilisateur utilisateur) {
		return new ResponseEntity<Utilisateur>(userService.signUp(utilisateur), HttpStatus.OK);
	}
	
	@PostMapping("/signin")
	public ResponseEntity<JsonWebToken> signIn(@RequestBody Utilisateur utilisateur) { // remplacer par DTO
		System.out.println("On est entr� dans le endpoint /signin. Utilisateur = " + utilisateur);
		return new ResponseEntity(userService.signIn(utilisateur.getIdentifiantRH(), utilisateur.getPassword()), HttpStatus.OK) ;
	}
	
	//
	
	@GetMapping("/all")
	public ResponseEntity<List<Utilisateur>> findAll(){
		return new ResponseEntity<List<Utilisateur>>(utilisateurService.findAll(), HttpStatus.OK);
	}
	
	
	
}
