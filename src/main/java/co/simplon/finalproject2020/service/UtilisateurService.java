package co.simplon.finalproject2020.service;

import java.util.List;

import co.simplon.finalproject2020.model.Utilisateur;

public interface UtilisateurService {
	
	public Utilisateur findById(int id) throws Exception;
	
	public Utilisateur findByIdrh(String idrh) throws Exception;
	
	public List<Utilisateur> findAll();
	
	// public Utilisateur saveUser(Utilisateur user) throws Exception;

}
