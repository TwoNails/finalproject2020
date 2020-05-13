package co.simplon.finalproject2020.repository.criteria;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

import co.simplon.finalproject2020.model.Demande;
import co.simplon.finalproject2020.model.criteria.DemandeCriteria;

@NoRepositoryBean
public interface CustomCriteriaDemandeRepository extends CustomCriteriaRepository<Demande> { // switch back to T asap

	public List<Demande> findAllWithCriteria(DemandeCriteria criteres); // eventually we'll have to make all Criteria models inherit from an abstract Class and use this one here instead of DemandeCrit

}
