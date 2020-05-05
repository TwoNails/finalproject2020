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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.finalproject2020.model.JsonWebToken;
import co.simplon.finalproject2020.model.Utilisateur;
import co.simplon.finalproject2020.model.dto.SigninDTO;
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
	public ResponseEntity<JsonWebToken> signIn(@RequestBody SigninDTO signinDTO) { // remplacer par DTO
		System.out.println("On est entré dans le endpoint /signin. Utilisateur = " + signinDTO);
		return new ResponseEntity(userService.signIn(signinDTO.getIdentifiantRH(), signinDTO.getPassword()), HttpStatus.OK) ;
	}
	
	@GetMapping("/{idrh}")
	public ResponseEntity<Utilisateur> getUser(@RequestParam String idrh) {
		try {
			return new ResponseEntity<Utilisateur>(userService.findByIdrh(idrh), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Utilisateur>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<Utilisateur>> findAll(){
		return new ResponseEntity<List<Utilisateur>>(utilisateurService.findAll(), HttpStatus.OK);
	}
	
	
	
	
	
}
