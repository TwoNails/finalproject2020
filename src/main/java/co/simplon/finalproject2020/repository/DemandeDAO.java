package co.simplon.finalproject2020.repository;

import java.util.Optional;

import co.simplon.finalproject2020.model.Demande;

public interface DemandeDAO extends DAO<Demande> {
	
	Optional<Demande> findByNumero(String numero);
}
