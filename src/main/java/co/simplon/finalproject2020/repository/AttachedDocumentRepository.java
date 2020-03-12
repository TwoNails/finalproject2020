package co.simplon.finalproject2020.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import co.simplon.finalproject2020.model.AttachedDocument;

public interface AttachedDocumentRepository extends JpaRepository<AttachedDocument, Integer> {
	
	List<AttachedDocument> findByFileReferenceAndName(int fileReference, String name);
	
	List<AttachedDocument> findAllByFileReference(int fileReference);
	
}
