package co.simplon.finalproject2020.repository;

import java.util.Optional;

import co.simplon.finalproject2020.model.TypeDemande;

public interface TypeDemandeDAO extends DAO<TypeDemande> {
	
	Optional<TypeDemande> findByCode(String code);
}
