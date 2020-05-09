package co.simplon.finalproject2020.repository;

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

import co.simplon.finalproject2020.model.Utilisateur;
import co.simplon.finalproject2020.model.Equipe;
import co.simplon.finalproject2020.model.criteria.UtilisateurCriteria;

@Repository
public class CustomCriteriaUtilisateurRepositoryImpl implements CustomCriteriaUtilisateurRepository {

	@PersistenceContext					// apparamment @PersistenceContext est similaire à @Autowired
	EntityManager em;					// de fait il n'est pas possible d'utiliser ces lignes si on est dans une interface plutôt qu'une classe, pourquoi ?	
						
	@Override
	public List<Utilisateur> findAllWithCriteria(UtilisateurCriteria criteres) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Utilisateur> critquery = cb.createQuery(Utilisateur.class);
		Root<Utilisateur> utilisateurRoot = critquery.from(Utilisateur.class);
		Join<Utilisateur, Equipe> equipeJoin = utilisateurRoot.join("equipe");
		
		List<Predicate> predicates = new ArrayList<Predicate>();
		
		if(criteres.getIdentifiantRH() != null) {
			predicates.add(cb.equal(utilisateurRoot.get("identifiantRH"), criteres.getIdentifiantRH()));
		}
		
		if(criteres.getNom() != null) {
			predicates.add(cb.equal(utilisateurRoot.get("nom"), criteres.getNom()));
		}
		
		if(criteres.getPrenom() != null) {
			predicates.add(cb.equal(utilisateurRoot.get("prenom"), criteres.getPrenom()));
		}
		
		if(criteres.getEquipe() != null) {
			predicates.add(cb.equal(equipeJoin.get("libelle"), criteres.getEquipe()));
		}
		
		 // we define the query. It will be applied to this root (the database table associated with this entity) and will follow these restrictions.
		critquery.select(utilisateurRoot).where(predicates.toArray(new Predicate[] {}));
		// we create the query and store it in the appropriate Java Object TypedQuery
		TypedQuery<Utilisateur> query = em.createQuery(critquery);
		// we return the results
		return query.getResultList();
	}
	
}