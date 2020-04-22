package co.simplon.finalproject2020.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import co.simplon.finalproject2020.model.Utilisateur;
import co.simplon.finalproject2020.repository.UtilisateurDAO;
import co.simplon.finalproject2020.security.JwtTokenProvider;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	private UtilisateurDAO utilisateurDAO;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	
	@Autowired 
	private PasswordEncoder passwordEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<Utilisateur> optUtilisateur = utilisateurDAO.findByIdentifiantRH(username);
		
		if(optUtilisateur.isPresent()) {
			return optUtilisateur.get();
		} else throw new UsernameNotFoundException("Utilisateur introuvable");
		
	}
		
	public String signIn(String idrh, String password) throws BadCredentialsException {
		System.out.println("on est entré dans la méthode signIn de userService, avec les paramètres idrh = " + idrh + "et password = " + password);
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(idrh, password));
			return jwtTokenProvider.createToken(idrh, utilisateurDAO.findByIdentifiantRH(idrh).get().getAuthorities());
			/*
			if(checkPassword(idrh, password)) {
				return jwtTokenProvider.createToken(idrh, utilisateurDAO.findByIdentifiantRH(idrh).get().getAuthorities());
			} else {
				throw new BadCredentialsException("L'IDRH ou le mot de passe est incorrect");
			}*/
		} catch (AuthenticationException e) {
			throw new BadCredentialsException("L'IDRH ou le mot de passe est incorrect");
		}
	}
	
	public Utilisateur signUp(Utilisateur utilisateur) {
		utilisateur.setPassword(passwordEncoder.encode(utilisateur.getPassword()));
		return utilisateurDAO.saveAndFlush(utilisateur);
	}
	
	private boolean checkPassword(String idrh, String password) {
		Optional<Utilisateur> optUser = utilisateurDAO.findByIdentifiantRH(idrh);
		if(optUser.isPresent()) {
			if(optUser.get().getPassword().equals(password)) {
				return true;
			} else throw new UsernameNotFoundException("Pas de gestionnaire avec cet IDRH en base");
		} else return false;
	}
	
	
	

}
