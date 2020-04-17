package co.simplon.finalproject2020.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import co.simplon.finalproject2020.model.Utilisateur;
import co.simplon.finalproject2020.repository.UtilisateurDAO;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	private UtilisateurDAO utilisateurDAO;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<Utilisateur> optUtilisateur = utilisateurDAO.findByIdentifiantRH(username);
		
		if(optUtilisateur.isPresent()) {
			return optUtilisateur.get();
		} else throw new UsernameNotFoundException("Utilisateur introuvable");
		
	}

}
