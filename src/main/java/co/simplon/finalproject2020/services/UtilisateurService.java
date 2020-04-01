package co.simplon.finalproject2020.services;

import co.simplon.finalproject2020.model.Utilisateur;

public interface UtilisateurService {
	
	public Utilisateur findById(int id) throws Exception;
	
	public Utilisateur saveUser(Utilisateur user) throws Exception;

}
