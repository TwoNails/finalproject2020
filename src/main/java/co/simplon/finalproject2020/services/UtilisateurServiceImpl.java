package co.simplon.finalproject2020.services;

import java.util.Arrays;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.simplon.finalproject2020.model.Utilisateur;
import co.simplon.finalproject2020.model.enums.ProfilUtilisateur;
import co.simplon.finalproject2020.repository.UtilisateurDAO;

@Service
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
}
