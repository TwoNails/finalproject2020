package co.simplon.finalproject2020.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.simplon.finalproject2020.model.Utilisateur;

@Repository
public interface UtilisateurDAO extends DAO<Utilisateur>{

	Optional<Utilisateur> findByNom(String nom);
	
}
