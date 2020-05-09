package co.simplon.finalproject2020.repository;

import java.util.List;

import co.simplon.finalproject2020.model.Utilisateur;
import co.simplon.finalproject2020.model.criteria.UtilisateurCriteria;

public interface CustomCriteriaUtilisateurRepository extends CustomCriteriaRepository<Utilisateur> {

	public List<Utilisateur> findAllWithCriteria(UtilisateurCriteria criteres);
}
