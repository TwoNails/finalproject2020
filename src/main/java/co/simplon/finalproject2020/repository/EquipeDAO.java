package co.simplon.finalproject2020.repository;

import java.util.Optional;

import co.simplon.finalproject2020.model.Equipe;

public interface EquipeDAO extends DAO<Equipe> {

	public Optional<Equipe> findByLibelle(String libelle);
}
