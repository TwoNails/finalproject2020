//package co.simplon.finalproject2020.service;
//
//import java.time.LocalDate;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import co.simplon.finalproject2020.model.Demande;
//import co.simplon.finalproject2020.repository.CustomCriteriaRepositoryDemande;
//
//@Service
//public class TestCriteriaSercice {
//	
//	@Autowired
//	private CustomCriteriaRepositoryDemande<Demande> ccRepository;
//	
//	public List<Demande> practiceCriteria(LocalDate fromDate, LocalDate toDate){
//		return ccRepository.findAllWithCreationDateBetween(fromDate, toDate);
//	}
//}
