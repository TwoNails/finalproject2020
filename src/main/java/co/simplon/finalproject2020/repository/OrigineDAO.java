package co.simplon.finalproject2020.repository;

import java.util.Optional;

import co.simplon.finalproject2020.model.Origine;

public interface OrigineDAO extends DAO<Origine> {
	
	Optional<Origine> findByLibelle(String libelle);

}
