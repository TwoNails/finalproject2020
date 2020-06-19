package co.simplon.finalproject2020.repository;

import java.time.LocalDate;
import java.util.List;

import co.simplon.finalproject2020.model.ChangementStatut;
import co.simplon.finalproject2020.model.Demande;

public interface ChangementStatutDAO extends DAO<ChangementStatut> {
	
	public List<ChangementStatut> findAllByDateChangementBetween(LocalDate fromDate, LocalDate toDate);

}
