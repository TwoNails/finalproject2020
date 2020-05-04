package co.simplon.finalproject2020.repository;

import java.time.LocalDate;
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

import org.apache.poi.ss.formula.functions.T;
import org.springframework.stereotype.Repository;

import co.simplon.finalproject2020.model.Agent;
import co.simplon.finalproject2020.model.Branche;
import co.simplon.finalproject2020.model.Demande;
import co.simplon.finalproject2020.model.Entite;
import co.simplon.finalproject2020.model.Nature;
import co.simplon.finalproject2020.model.Origine;
import co.simplon.finalproject2020.model.Statut;
import co.simplon.finalproject2020.model.criteria.DemandeCriteria;


@Repository
public class CustomCriteriaDemandeRepositoryImpl implements CustomCriteriaDemandeRepository {
	
	@PersistenceContext					// apparamment @PersistenceContext est similaire à @Autowired
	EntityManager em;					// de fait il n'est pas possible d'utiliser ces lignes si on est dans une interface plutôt qu'une classe, pourquoi ?			
	

	@Override 								// DemandeCriteria est un modèle compilant tous les différents critères que l'utilisateur peut, ou non, vouloir incorporer à sa requête.
	public List<Demande> findAllWithCriteria(DemandeCriteria criteres) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Demande> critquery = cb.createQuery(Demande.class);
		Root<Demande> demandeRoot = critquery.from(Demande.class);
		Join<Demande, Agent> agentJoin = demandeRoot.join("agent");
		Join<Demande, Nature> natureJoin = demandeRoot.join("nature");
		Join<Agent, Entite> entiteJoin = agentJoin.join("entite");
		Join<Entite, Branche> brancheJoin = entiteJoin.join("branche");
		Join<Demande, Statut> statutJoin = demandeRoot.join("statut");
		Join<Demande, Origine> origineJoin = demandeRoot.join("origine");
		
		// un predicate est une condition que je vais pouvoir appliquer à ma recherche. Je crée donc une nouvelle liste, pour l'instant vide, de ces conditions.
		List<Predicate> predicates = new ArrayList<Predicate>();
		
		if(criteres.getFromDateCreation() != null) {
			predicates.add(cb.greaterThanOrEqualTo(demandeRoot.get("dateCreation"), criteres.getFromDateCreation()));
		}
		if(criteres.getToDateCreation() != null) {
			predicates.add(cb.lessThanOrEqualTo(demandeRoot.get("dateCreation"), criteres.getToDateCreation()));
		}
		if(criteres.getFromDateAttribution() != null) {
			predicates.add(cb.greaterThanOrEqualTo(demandeRoot.get("dateAttribution"), criteres.getFromDateAttribution()));
		}
		if(criteres.getToDateAttribution() != null) {
			predicates.add(cb.lessThanOrEqualTo(demandeRoot.get("dateAttribution"), criteres.getToDateAttribution()));
		}
		if(criteres.getFromDateEcheance() != null) {
			predicates.add(cb.greaterThanOrEqualTo(demandeRoot.get("dateEcheance"), criteres.getFromDateEcheance()));
		}
		if(criteres.getToDateEcheance() != null) {
			predicates.add(cb.lessThanOrEqualTo(demandeRoot.get("dateEcheance"), criteres.getToDateEcheance()));
		}
		if(criteres.getFromDateCloture() != null) {
			predicates.add(cb.greaterThanOrEqualTo(demandeRoot.get("dateCloture"), criteres.getFromDateCloture()));
		}
		if(criteres.getToDateCloture() != null) {
			predicates.add(cb.lessThanOrEqualTo(demandeRoot.get("dateCloture"), criteres.getToDateCloture()));
		}
		
		if(criteres.getNumero() != null) {
			predicates.add(cb.equal(demandeRoot.get("numero"), criteres.getNumero()));
		}
		
		if(criteres.getAgentIdrh() != null) {
			System.out.println("agent idrh received in the request body : " + criteres.getAgentIdrh());
			System.out.println("hopefully this comes out as a list of String : " + demandeRoot.get("agent"));	// apparently not => "org.hibernate.query.criteria.internal.path.SingularAttributePath@3b9bb9bc"
			// predicates.add(cb.equal(demandeRoot.get("agent"), criteres.getAgentIdrh()));
			
			System.out.println("ok second try : " + agentJoin.get("identifiantRH")); // apprently still not, but somehow this time cb.equals seems to be able to compare both and declare them equal
			predicates.add(cb.equal(agentJoin.get("identifiantRH"), criteres.getAgentIdrh()));
		}
		
		if(criteres.getNature() != null) {
			predicates.add(cb.equal(natureJoin.get("libelle"), criteres.getNature()));
		}

		if(criteres.getBranche() != null) {
			predicates.add(cb.equal(brancheJoin.get("code"), criteres.getBranche()));
		}
		
		if(criteres.getObjet() != null) {
			String pattern = "%" + criteres.getObjet() + "%";
			predicates.add(cb.like(demandeRoot.get("objet"), pattern));
		}
		
		if(criteres.getStatut() != null) {
			predicates.add(cb.equal(statutJoin.get("libelle"), criteres.getStatut()));
		}
		
		if(criteres.getOrigine() != null) {
			predicates.add(cb.equal(origineJoin.get("libelle"), criteres.getOrigine()));
		}
		
		
		 // we define the query. It will be applied to this root (the database table associated with this entity) and will follow these restrictions.
		critquery.select(demandeRoot).where(predicates.toArray(new Predicate[] {}));
		// we create the query and store it in the appropriate Java Object TypedQuery
		TypedQuery<Demande> query = em.createQuery(critquery);
		// we return the results
		return query.getResultList();
	}
}
