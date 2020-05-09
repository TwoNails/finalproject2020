package co.simplon.finalproject2020.repository;

import java.util.Optional;

import co.simplon.finalproject2020.model.ProfilUtilisateur;

public interface RoleDAO extends DAO<ProfilUtilisateur> {
	
	Optional<ProfilUtilisateur> findByLibelle(String libelle);
	
}
