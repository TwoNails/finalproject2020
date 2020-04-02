package co.simplon.finalproject2020.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import co.simplon.finalproject2020.model.Demande;
import co.simplon.finalproject2020.model.Utilisateur;
import co.simplon.finalproject2020.repository.DemandeDAO;
import co.simplon.finalproject2020.repository.UtilisateurDAO;

public class DemandeServiceImpl implements DemandeService {
	
	@Autowired
	private DemandeDAO demandeDAO;
	
	@Override
	public Demande findById(int id) throws Exception {
		 Optional<Demande> optDemande = demandeDAO.findById(id);
			if(optDemande.isPresent()) {
				return optDemande.get();
			} else {
				throw new Exception("id can't be found in database");
			}
	 }
	
	@Override
	public Demande findByNumero(String numero) throws Exception {
		Optional<Demande> optDemande = demandeDAO.findByNumero(numero);
		if(optDemande.isPresent()) {
			return optDemande.get();
		} else {
			throw new Exception("numero can't be found in database");
		}
	}

	@Override
	public Demande saveDemande(Demande demande) throws Exception {
		Optional<Demande> optDemande = demandeDAO.findByNumero(demande.getNumero());
		return null;
	}

	

}
