package co.simplon.finalproject2020.repository;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

import co.simplon.finalproject2020.model.Agent;
import co.simplon.finalproject2020.model.Demande;
import co.simplon.finalproject2020.model.Entite;
import co.simplon.finalproject2020.model.Grade;
import co.simplon.finalproject2020.model.criteria.AgentCriteria;

@Repository
public class CustomCriteriaAgentRepositoryImpl implements CustomCriteriaAgentRepository {

	@PersistenceContext					// apparamment @PersistenceContext est similaire à @Autowired
	EntityManager em;					// de fait il n'est pas possible d'utiliser ces lignes si on est dans une interface plutôt qu'une classe, pourquoi ?			
	
	@Override
	public List<Agent> findAllWithCriteria(AgentCriteria criteres) {
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Agent> critquery = cb.createQuery(Agent.class);
		Root<Agent> agentRoot = critquery.from(Agent.class);
		Join<Agent, Entite> entiteJoin = agentRoot.join("entite");
		Join<Agent, Grade> gradeJoin = agentRoot.join("grade");
		
		// un predicate est une condition que je vais pouvoir appliquer à ma recherche. Je crée donc une nouvelle liste, pour l'instant vide, de ces conditions.
		List<Predicate> predicates = new ArrayList<Predicate>();
		
		if(criteres.getIdentifiantRH() != null) {
			predicates.add(cb.equal(agentRoot.get("identifiantRH"), criteres.getIdentifiantRH()));
		}
		
		if(criteres.getNom() != null) {
			predicates.add(cb.equal(agentRoot.get("nom"), criteres.getNom()));
		}
		
		if(criteres.getPrenom() != null) {
			predicates.add(cb.equal(agentRoot.get("prenom"), criteres.getPrenom()));
		}
		
		if(criteres.getBranche() != null) {												// A remplacer pour la cohérence du code. Mofifier l'entité Entité puis créer un brancheJoin
			predicates.add(cb.equal(entiteJoin.get("branche"), criteres.getBranche()));
		}
		
		if(criteres.getCodeRegate() != null) {	
			predicates.add(cb.equal(entiteJoin.get("codeRegate"), criteres.getCodeRegate()));
		}
		
		if(criteres.getGrade() != null) {
			predicates.add(cb.equal(gradeJoin.get("code"), criteres.getGrade()));
		}
		
		if(criteres.getAnneeNaissance() != null) {
			LocalDate anneeNaissanceDebut = criteres.getAnneeNaissance().atMonth(Month.JANUARY).atDay(1);
			LocalDate anneeNaissanceFin = criteres.getAnneeNaissance().atMonth(Month.DECEMBER).atDay(31);
			predicates.add(cb.between(agentRoot.get("dateNaissance"), anneeNaissanceDebut, anneeNaissanceFin));
		}
		
		if(criteres.getAnneeEntreePoste() != null) {
			LocalDate anneeEntreeDebut = criteres.getAnneeEntreePoste().atMonth(Month.JANUARY).atDay(1);
			LocalDate anneeEntreeFin = criteres.getAnneeEntreePoste().atMonth(Month.DECEMBER).atDay(31);
			predicates.add(cb.between(agentRoot.get("dateEntree"), anneeEntreeDebut, anneeEntreeFin));
		}
		
		critquery.select(agentRoot).where(predicates.toArray(new Predicate[] {}));

		TypedQuery<Agent> query = em.createQuery(critquery);
		// we return the results
		return query.getResultList();// TODO Auto-generated method stub
	}

}
