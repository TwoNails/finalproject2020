package co.simplon.finalproject2020.repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.poi.ss.formula.functions.T;
import org.springframework.stereotype.Repository;

import co.simplon.finalproject2020.model.Demande;
import co.simplon.finalproject2020.model.criteria.DemandeCriteria;


@Repository
public class CustomCriteriaRepositoryDemandeImpl implements CustomCriteriaRepositoryDemande {
	
	@PersistenceContext					// apparamment @PersistenceContext est similaire à @Autowired
	EntityManager em;					// de fait il n'est pas possible d'utiliser ces lignes si on est dans une interface plutôt qu'une classe, pourquoi ?			
		
	public List<Demande> findAllWithCreationDateBetween(LocalDate fromDate, LocalDate toDate){
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Demande> query = cb.createQuery(Demande.class);
		Root<Demande> demandeRoot = query.from(Demande.class);
		
		query.select(demandeRoot).where(cb.between((demandeRoot.get("dateCreation")), fromDate, toDate)); 
		TypedQuery<Demande> typedQuery = em.createQuery(query);
		
		return typedQuery.getResultList();
		
	}

	@Override 								// DemandeCriteria est un modèle compilant tous les différents critères que l'utilisateur peut, ou non, vouloir incorporer à sa requête.
	public List<Demande> findAllWithCriteria(DemandeCriteria criteres) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Demande> critquery = cb.createQuery(Demande.class);
		Root<Demande> demandeRoot = critquery.from(Demande.class);
		
		// un predicate est une condition que je vais pouvoir appliquer à ma recherche. Je crée donc une nouvelle liste, pour l'instant vide, de ces conditions.
		List<Predicate> predicates = new ArrayList<Predicate>();
		
		if(!criteres.getFromDateCreation().equals(null) && !criteres.getToDateCreation().equals(null)) {	// si cette valeur n'est pas nulle ( <=> si l'utilisateur l'a ajouté dans sa requête)
			predicates.add(cb.between((demandeRoot.get("dateCreation")), criteres.getFromDateCreation(), criteres.getToDateCreation())); // on crée le predicate correspondant et on l'ajoute à la liste
		}
		if(!criteres.getFromDateAttribution().equals(null) && !criteres.getToDateAttribution().equals(null)) {
			predicates.add(cb.between((demandeRoot.get("dateAttribution")), criteres.getFromDateAttribution(), criteres.getToDateAttribution()));
		}
		if(!criteres.getFromDateEcheance().equals(null) && !criteres.getToDateEcheance().equals(null)) {
			predicates.add(cb.between((demandeRoot.get("dateEcheance")), criteres.getFromDateEcheance(), criteres.getToDateEcheance()));
		}
		if(!criteres.getFromDateCloture().equals(null) && !criteres.getToDateCloture().equals(null)) {
			predicates.add(cb.between((demandeRoot.get("dateCloture")), criteres.getFromDateCloture(), criteres.getToDateCloture()));
		}
		if(!criteres.getAgentIdrh().equals(null)) {
			System.out.println("agent idrh received in the request body : " + criteres.getAgentIdrh());
			System.out.println("hopefully this comes out as a list of String : " + demandeRoot.get("agent"));
			predicates.add(cb.equal(demandeRoot.get("agent"), criteres.getAgentIdrh()));
		}
		
		 // we define the query. It will be applied to this root (the database table associated with this entity) and will follow these restrictions.
		critquery.select(demandeRoot).where(predicates.toArray(new Predicate[] {}));
		TypedQuery<Demande> query = em.createQuery(critquery);
		return query.getResultList();
	}
}
