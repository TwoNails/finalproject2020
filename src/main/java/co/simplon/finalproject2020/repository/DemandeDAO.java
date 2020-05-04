package co.simplon.finalproject2020.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import co.simplon.finalproject2020.model.Agent;
import co.simplon.finalproject2020.model.Demande;
import co.simplon.finalproject2020.model.criteria.DemandeCriteria;

@Repository
public interface DemandeDAO extends DAO<Demande> /*, CustomCriteriaDemandeRepository<Demande> */ {

	Optional<Demande> findByNumero(String numero);
	
	// we do not need to declare the findAll method, as it is described in the extended interface DAO, so we shouldn't need to add the criteria method either. or should we ?
	
	// List<Demande> findAllWithCriteria(DemandeCriteria criteres);
	
}

