package co.simplon.finalproject2020.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import co.simplon.finalproject2020.model.AttachedDocument;
import co.simplon.finalproject2020.model.Demande;

public interface AttachedDocumentDAO extends DAO<AttachedDocument> {
	
	
	//	@Query(value = "SELECT * FROM attached_documents a where t.title = ?0 AND t.description = ?1", 
	//	        nativeQuery=true
	//	    )
	AttachedDocument findByDemandeAndName(Demande demande, String name);
	
	//@Query("SELECT a from AttachedDocument a WHERE a.demande_nnumero = '22209998'")
	// @Query("SELECT a from AttachedDocument a WHERE a.demande_nnumero =:demandeNumero")
	List<AttachedDocument> findAllByDemande(Demande demande);

}
