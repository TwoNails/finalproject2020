package co.simplon.finalproject2020.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import co.simplon.finalproject2020.model.Utilisateur;
import co.simplon.finalproject2020.repository.UtilisateurDAO;

public class UtilisateurServiceImpl implements UtilisateurService {
	
	@Autowired
	private UtilisateurDAO utilisateurDAO;

	@Override
	public Utilisateur findById(int id) throws Exception {
		 Optional<Utilisateur> optUser = utilisateurDAO.findById(id);
			if(optUser.isPresent()) {
				return optUser.get();
			} else {
				throw new Exception("id can't be found in database");
			}
	 }

	@Override
	public Utilisateur saveUser(Utilisateur utilisateur) throws Exception {
		Optional<Utilisateur> optUser = utilisateurDAO.findByNom(utilisateur.getNom());
		if(optUser.isPresent()) {
			throw new Exception("username not available");
		} else {
			// user.setPassword(passwordEncoder.encode(user.getPassword()));		PLUS TARD
			// utilisateur.se
			return utilisateurDAO.saveAndFlush(utilisateur);
		}
	}

}
