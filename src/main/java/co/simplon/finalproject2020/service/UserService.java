package co.simplon.finalproject2020.service;

import java.util.List;
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

import co.simplon.finalproject2020.model.Equipe;
import co.simplon.finalproject2020.model.JsonWebToken;
import co.simplon.finalproject2020.model.ProfilUtilisateur;
import co.simplon.finalproject2020.model.Utilisateur;
import co.simplon.finalproject2020.model.criteria.UtilisateurCriteria;
import co.simplon.finalproject2020.repository.EquipeDAO;
import co.simplon.finalproject2020.repository.RoleDAO;
import co.simplon.finalproject2020.repository.UtilisateurDAO;
import co.simplon.finalproject2020.repository.criteria.CustomCriteriaUtilisateurRepository;
import co.simplon.finalproject2020.security.JwtTokenProvider;

@Service
public class UserService implements UserDetailsService, UtilisateurService {

	@Autowired
	private UtilisateurDAO utilisateurDAO;
	
	@Autowired
	private EquipeDAO equipeDAO;
	
	@Autowired
	private RoleDAO roleDAO;
	
	@Autowired
	private CustomCriteriaUtilisateurRepository ccRepository;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	
	@Autowired 
	private PasswordEncoder passwordEncoder;
	
	// SECURITY RELATED
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<Utilisateur> optUtilisateur = utilisateurDAO.findByIdentifiantRH(username);
		
		if(optUtilisateur.isPresent()) {
			return optUtilisateur.get();
		} else throw new UsernameNotFoundException("Utilisateur introuvable");
		
	}
		
	public JsonWebToken signIn(String idrh, String password) throws BadCredentialsException {
		System.out.println("on est entré dans la méthode signIn de userService, avec les paramètres idrh = " + idrh + "et password = " + password);
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(idrh, password));
			return new JsonWebToken(jwtTokenProvider.createToken(idrh, utilisateurDAO.findByIdentifiantRH(idrh).get().getAuthorities())) ;
		} catch (AuthenticationException e) {
			System.out.println("Bad Credentials");
			throw new BadCredentialsException("L'IDRH ou le mot de passe est incorrect");
		}
	}
	
	public Utilisateur signUp(Utilisateur utilisateur) {
		utilisateur.setPassword(passwordEncoder.encode(utilisateur.getPassword()));
		return utilisateurDAO.saveAndFlush(utilisateur);
	}
	
	
	// CRUD

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
	public Utilisateur findByIdrh(String idrh) throws Exception {
		Optional<Utilisateur> optUser = utilisateurDAO.findByIdentifiantRH(idrh);
		if(optUser.isPresent()) {
			return optUser.get();
		} else {
			throw new Exception("id can't be found in database");
		}
	}

	@Override
	public List<Utilisateur> findAll() {
		return utilisateurDAO.findAll();
	}

	@Override
	public List<Utilisateur> findByCriteria(UtilisateurCriteria criteres) {
		System.out.println(criteres);
		return ccRepository.findAllWithCriteria(criteres);
	}

	@Override
	public Utilisateur remove(String idrh) throws Exception {
		Optional<Utilisateur> optUser = utilisateurDAO.findByIdentifiantRH(idrh);
		if(optUser.isPresent()) {
			Utilisateur userInBase = optUser.get();
			System.out.println("we found the user : " + userInBase);
			if(userInBase.getEstActif()) {
				userInBase.setEstActif(false);
				return utilisateurDAO.saveAndFlush(userInBase);
			} else throw new Exception("user already inactive");
			
		} else {
			throw new Exception("id can't be found in database");
		}
		
	}

	@Override
	public Utilisateur update(String idrh, String role, String equipe) throws Exception {
		Optional<Utilisateur> optUser = utilisateurDAO.findByIdentifiantRH(idrh);
		Optional<Equipe> optEquipe = equipeDAO.findByLibelle(equipe);
		Optional<ProfilUtilisateur> optRole = roleDAO.findByLibelle(role);
		if(optUser.isPresent()) {
			Utilisateur userInBase = optUser.get();
			if(optEquipe.isPresent()) {
				userInBase.setEquipe(optEquipe.get());
			}
			if(optRole.isPresent()) {
				userInBase.getRoles().clear();
				List<ProfilUtilisateur> allRoles = roleDAO.findAll();
				for(int i = 0 ; i < allRoles.size() ; i++) {
					if(allRoles.get(i).getId() <= optRole.get().getId()) {
						userInBase.getRoles().add(allRoles.get(i));
					}
				}
			}
			
			
			return utilisateurDAO.saveAndFlush(userInBase);
		} else {
			throw new Exception("id can't be found in database");
		}
	}

	

	
}
