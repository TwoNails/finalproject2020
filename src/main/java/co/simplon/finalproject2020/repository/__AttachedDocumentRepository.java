package co.simplon.finalproject2020.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import co.simplon.finalproject2020.model.AttachedDocument;

public interface __AttachedDocumentRepository extends JpaRepository<AttachedDocument, Integer> {
	
	List<AttachedDocument> findByDemandeAndName(String demandeNumero, String name);
	
	List<AttachedDocument> findAllByDemande(String demandeNumero);
	
}
