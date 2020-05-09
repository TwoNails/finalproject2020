package co.simplon.finalproject2020.service;

import java.util.List;

import co.simplon.finalproject2020.model.Utilisateur;
import co.simplon.finalproject2020.model.criteria.UtilisateurCriteria;

public interface UtilisateurService {
	
	public Utilisateur findById(int id) throws Exception;
	
	public Utilisateur findByIdrh(String idrh) throws Exception;
	
	public List<Utilisateur> findAll();

	public List<Utilisateur> findByCriteria(UtilisateurCriteria criteres);

	public Utilisateur remove(String idrh) throws Exception;

	public Utilisateur update(String idrh, String role, String equipe) throws Exception;
	
	// public Utilisateur saveUser(Utilisateur user) throws Exception;

}
