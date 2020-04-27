package co.simplon.finalproject2020.repository;

import java.util.Optional;

import co.simplon.finalproject2020.model.Statut;

public interface StatutDAO extends DAO<Statut> {
	
	Optional<Statut> findByLibelle(String libelle);
	
}
