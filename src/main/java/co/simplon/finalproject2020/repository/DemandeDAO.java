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
import co.simplon.finalproject2020.model.Nature;


@Repository
public interface DemandeDAO extends DAO<Demande> {

	Optional<Demande> findByNumero(String numero);	
	
	List<Demande> findAllByNature(Nature nature);
	
}

