package co.simplon.finalproject2020.service.impl;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.simplon.finalproject2020.model.AttachedDocument;
import co.simplon.finalproject2020.model.Demande;
import co.simplon.finalproject2020.repository.AttachedDocumentDAO;
import co.simplon.finalproject2020.repository.DemandeDAO;
import co.simplon.finalproject2020.service.DocumentService;

@Service
public class DocumentServiceImpl implements DocumentService {
	
	@Autowired
	private AttachedDocumentDAO documentDAO;
	
	@Autowired 
	private DemandeDAO demandeDAO;

	@Override
	public List<String> findNamesByDemandeNumero(String numero) {
		List<String> fileNames = new ArrayList<String>();
		
		Optional<Demande> optDemande = demandeDAO.findByNumero(numero);
		if(optDemande.isPresent()) {		
			for(AttachedDocument document : documentDAO.findAllByDemande(optDemande.get())) {
				fileNames.add(document.getName());
			}
			
		}
		return fileNames;
	}

	@Override
	public AttachedDocument findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public byte[] getDocumentData(String numeroDemande, String nomDocument) {
		System.out.println("nom doc : " + nomDocument);
		Optional<Demande> optDemande = demandeDAO.findByNumero(numeroDemande);
		System.out.println("document : " + documentDAO.findByDemandeAndName(optDemande.get(), nomDocument));
		return documentDAO.findByDemandeAndName(optDemande.get(), nomDocument).getContent();
	}
}
