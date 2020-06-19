package co.simplon.finalproject2020.service.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.simplon.finalproject2020.model.ChangementStatut;
import co.simplon.finalproject2020.model.Demande;
import co.simplon.finalproject2020.model.Nature;
import co.simplon.finalproject2020.model.Statut;
import co.simplon.finalproject2020.model.Utilisateur;
import co.simplon.finalproject2020.model.Equipe;
import co.simplon.finalproject2020.repository.ChangementStatutDAO;
import co.simplon.finalproject2020.repository.DemandeDAO;
import co.simplon.finalproject2020.repository.NatureDAO;
import co.simplon.finalproject2020.repository.StatutDAO;
import co.simplon.finalproject2020.service.ExcelService;

@Service
public class ExcelServiceImpl implements ExcelService {
	
	@Autowired
	private NatureDAO natureDAO;
	
	@Autowired
	private StatutDAO statutDAO;
	
	@Autowired
	private DemandeDAO demandeDAO;
	
	@Autowired
	private ChangementStatutDAO cgtStatutDAO;
	
	private static String[] COLUMNS_DEMANDE_SEARCH = {	"Numero", "Nature", "Type", "Origine", 
														"Agent Concerné", "IDRH", "Branche", "Objet", 
														"Date Création", "Date Attribution", "Date Echeance", "Date Cloture"}; 
	
	private static String[] COLUMNS_REPORTING = { "Gestionnaire", "Demandes \nAttribuées", "Demandes \nCloturées"}; 

	@Override
	public ByteArrayInputStream excelFileFromDemandeSearch(List<Demande> demandes) throws IOException {
		
		Workbook workbook = this.generateFromDemandeSearch(demandes);
	
		return this.getContentAsBAIS(workbook);
	}
	
	@Override
	public ByteArrayInputStream excelFileFromPeriod(LocalDate fromDate, LocalDate toDate) throws Exception {
		
			Workbook workbook = this.generateFromPeriod(fromDate, toDate);
			
			return this.getContentAsBAIS(workbook);
	}
	
	
	private Workbook generateFromDemandeSearch(List<Demande> demandes) {
		Workbook workbook = new XSSFWorkbook(); 		
		Sheet sheet = workbook.createSheet("ResultatsRecherche");
		
		// Créer une font spécifique aux headers
		Font headerFont = workbook.createFont();
		headerFont.setBold(true);
		headerFont.setFontHeightInPoints((short) 12);
		
		// Créer un CellStyle avec la font
		CellStyle headerCellStyle = workbook.createCellStyle();
		headerCellStyle.setFont(headerFont);
		
		// Créer une première ligne pour le header
		Row headerRow = sheet.createRow(0);
				
		// Créer les cellules de cette ligne					
		for (int i = 0; i < COLUMNS_DEMANDE_SEARCH.length; i++) {
			Cell cell = headerRow.createCell(i);
			cell.setCellValue(COLUMNS_DEMANDE_SEARCH[i]);
			cell.setCellStyle(headerCellStyle);
		}
		
		// Créer les lignes suivantes contenant le détail des infos de chaque demande
		int rowNum=1;
		for(Demande demande: demandes) {
			Row row = sheet.createRow(rowNum++);
			row.createCell(0).setCellValue(demande.getNumero());
			row.createCell(1).setCellValue(demande.getNature().getLibelle());
			row.createCell(2).setCellValue(demande.getType().getLibelle());
			row.createCell(3).setCellValue(demande.getOrigine().getLibelle());
			row.createCell(4).setCellValue(demande.getAgent().getNom() + " " + demande.getAgent().getPrenom());
			row.createCell(5).setCellValue(demande.getAgent().getIdentifiantRH());
			row.createCell(6).setCellValue(demande.getAgent().getEntite().getBranche().getCode());
			
			if(demande.getObjet()!=null) {
				row.createCell(7).setCellValue(demande.getObjet());
			}
			if(demande.getDateCreation()!=null) {
				row.createCell(8).setCellValue(demande.getDateCreation().toString());
			}
			if(demande.getDateAttribution()!=null) {
				row.createCell(9).setCellValue(demande.getDateAttribution().toString());
			}
			if(demande.getDateEcheance()!=null) {
				row.createCell(10).setCellValue(demande.getDateEcheance().toString());
			}
			if(demande.getDateCloture()!=null) {
				row.createCell(11).setCellValue(demande.getDateCloture().toString());
			}
		}
		
		int smallColumn = 2500, mediumColumn = 4500, largeColumn = 9500;
		sheet.setColumnWidth(0, smallColumn);
		sheet.setColumnWidth(1, mediumColumn);
		sheet.setColumnWidth(2, mediumColumn);
		sheet.setColumnWidth(3, mediumColumn);
		sheet.setColumnWidth(4, largeColumn);
		sheet.setColumnWidth(5, smallColumn);
		sheet.setColumnWidth(6, smallColumn);
		sheet.setColumnWidth(7, largeColumn);
		sheet.setColumnWidth(8, mediumColumn);
		sheet.setColumnWidth(9, mediumColumn);
		sheet.setColumnWidth(10, mediumColumn);
		sheet.setColumnWidth(11, mediumColumn);
	
		return workbook;
	}
	
	private Workbook generateFromPeriod(LocalDate fromDate, LocalDate toDate) throws Exception {
		Workbook workbook = new XSSFWorkbook();
		
		Optional<Statut> optStatutAttribuee = statutDAO.findByLibelle("ATTRIBUEE");
		Optional<Statut> optStatutCloturee = statutDAO.findByLibelle("CLOTUREE");
		
		if(optStatutAttribuee.isPresent() && optStatutCloturee.isPresent()) {
			Statut ATTRIBUEE = optStatutAttribuee.get();
			Statut CLOTUREE = optStatutCloturee.get();
			
			// On créera une feuille de classeur par nature de demande.
			// La construction de chacune de ces feuilles est décrite dans la boucle ci-dessous 
			for(Nature nature: natureDAO.findAll()) {
				
				Sheet sheet = workbook.createSheet(nature.getLibelle());	
				
				// Créer une font spécifique aux headers
				Font headerFont = workbook.createFont();
				headerFont.setBold(true);
				headerFont.setFontHeightInPoints((short) 12);					// celui ci et celui dessous
				
				// Créer un CellStyle avec la font
				CellStyle headerCellStyle = workbook.createCellStyle();
				headerCellStyle.setFont(headerFont);							// peut être on peut sortir ces deux blocs de la boucle.
				
				// Créer une première ligne pour le header
				Row headerRow = sheet.createRow(0);
				
				// Créer les cellules de cette ligne					
				for (int i = 0; i < COLUMNS_REPORTING.length; i++) {
					Cell cell = headerRow.createCell(i);
					cell.setCellValue(COLUMNS_REPORTING[i]);
					cell.setCellStyle(headerCellStyle);
				}
				
				List<ChangementStatut> cgtStatutForPeriod = cgtStatutDAO.findAllByDateChangementBetween(fromDate, toDate);
				
				System.out.println("nombre de ChangementStatut dans la periode donnée toutes natures confondues = " + cgtStatutForPeriod.size());
				
				// On ne s'intéresse qu'aux changements de statut sur les demandes de la nature évaluée en ce moment.
				Predicate<ChangementStatut> deNature = cgtStatut -> cgtStatut.getDemande().getNature().equals(nature);
				
				// On filtre donc la liste des demandes grêce à la condition décrite à la ligne précédente
				cgtStatutForPeriod = cgtStatutForPeriod.stream().filter(deNature).collect(Collectors.toList());
				
				System.out.println("nombre de ChangementStatut après filtrage (nature traitée dans ce tour de boucle = " + cgtStatutForPeriod.size());
				
				if(cgtStatutForPeriod.size()>0) {
					
					System.out.println("contenu exact après filtrage : " + cgtStatutForPeriod);
					
					// On construit une Hashmap qui nous permettra de remplir la première colonne
					HashMap<Equipe, List<Utilisateur>> utilisateursConcernes = new HashMap<Equipe, List<Utilisateur>>();		
					
					// On liste les clés de la Hasmap (les équipes)
					for(ChangementStatut cgtStatut : cgtStatutForPeriod) {
						Equipe newEquipe = cgtStatut.getUtilisateur().getEquipe();
						if(!utilisateursConcernes.containsKey(newEquipe)) {
							utilisateursConcernes.put(newEquipe, new ArrayList<Utilisateur>());
						}
						Utilisateur newUtilisateur = cgtStatut.getUtilisateur();
						if(!utilisateursConcernes.get(newEquipe).contains(newUtilisateur)) {
							utilisateursConcernes.get(newEquipe).add(newUtilisateur);
						}
						
					}
					
					// Notre HashMap completée nous permet de remplir l'Excel
					int rowNum=1;
					double traiteesTotal = 0;
					double clotureesTotal = 0;
					
					// pour chaque entrée de la Hasmap (chaque équipe)
					for (Map.Entry<Equipe, List<Utilisateur>> entry : utilisateursConcernes.entrySet()) {
						
						System.out.println("Tour de boucle par équipe. On est dans l'équipe : " + entry.getKey().getLibelle());
						System.out.println("Les membres sont : " + entry.getValue().toString());
						
						double traiteesTotalEquipe = 0;
						double clotureesTotalEquipe = 0;
						Row row = sheet.createRow(rowNum++); // on saute une ligne
						
						// on crée une ligne par membre de l'équipe
						for (Utilisateur utilisateur : entry.getValue()) {
							
							System.out.println("Tour de boucle par membre d'équipe. On va incrémenter pour : " + utilisateur.getIdentifiantRH() + " " + utilisateur.getNom());
							
							row = sheet.createRow(rowNum++);
							row.createCell(0).setCellValue(utilisateur.getNom() + " " + utilisateur.getPrenom());
							
							double demandesTraitees = 0;
							double demandesCloturees = 0;
							for(ChangementStatut cgtStatut : cgtStatutForPeriod) {
								System.out.println("Tour de boucle dans notre liste de statuts correctement filtrée."
										+ "\nLe nouveau statut du cgtStt n° " + cgtStatut.getId() +" est " + cgtStatut.getNouveauStatut().getLibelle()
										+ "\nLe responsable de ce cgtStt est " + cgtStatut.getUtilisateur().getIdentifiantRH());
								
								if(cgtStatut.getUtilisateur().equals(utilisateur)) {
									System.out.println("l'auteur de ce changement de statut correspond à celui pour lequel on est en train de boucler");
									System.out.println("On s'apprète donc à observer la valeur 'nouveau statut' de ce cgtStt. Elle vaut " + cgtStatut.getNouveauStatut().getLibelle());
									
									if(cgtStatut.getNouveauStatut().equals(ATTRIBUEE)) {
										demandesTraitees++;
									}
									if(cgtStatut.getNouveauStatut().equals(CLOTUREE)) {
										demandesCloturees++;
									}
								}
							}
							row.createCell(1).setCellValue(demandesTraitees);
							row.createCell(2).setCellValue(demandesCloturees);
							
							// on additionne ces valeurs au total de l'équipe
							traiteesTotalEquipe += demandesTraitees;
							clotureesTotalEquipe += demandesCloturees;
						}
						
						// puis une ligne pour le total de l'équipe
						row = sheet.createRow(rowNum++);
						row.setRowStyle(headerCellStyle);
						row.createCell(0).setCellValue(entry.getKey().getLibelle());
						row.createCell(1).setCellValue(traiteesTotalEquipe);
						row.createCell(2).setCellValue(clotureesTotalEquipe);
						
						// on additionne ces valeurs au total toutes équipes confondues
						traiteesTotal += traiteesTotalEquipe;
						clotureesTotal += clotureesTotalEquipe;
					}
					
					// on conclue avec la ligne des totaux toutes equipes confondues
					Row row = sheet.createRow(rowNum+=1);
					row.setRowStyle(headerCellStyle);
					row.createCell(0).setCellValue("TOTAL :");
					row.createCell(1).setCellValue(traiteesTotal);
					row.createCell(2).setCellValue(clotureesTotal);
						
				}
				
				sheet.setColumnWidth(0, 9000);
				sheet.setColumnWidth(1, 7500);
				sheet.setColumnWidth(2, 7500);
				
			}	

		} else throw new Exception();
		
		
		

		return workbook;
	}
	
	
	
	private ByteArrayInputStream getContentAsBAIS(Workbook workbook) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    workbook.write(baos);
	    return new ByteArrayInputStream(baos.toByteArray());
	}

}
