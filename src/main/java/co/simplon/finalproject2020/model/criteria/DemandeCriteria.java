package co.simplon.finalproject2020.model.criteria;

import java.time.LocalDate;

public class DemandeCriteria {
	
	private LocalDate fromDateCreation, toDateCreation;
	private LocalDate fromDateAttribution, toDateAttribution;
	private LocalDate fromDateEcheance, toDateEcheance;
	private LocalDate fromDateCloture, toDateCloture;
	
	private String numero;
	private String nature;
	private String branche;
	private String objet;
	private String statut;
	private String origine;
	private String agentIdrh;
	
	
	public DemandeCriteria() {
	}
	
	public DemandeCriteria(LocalDate fromDateCreation, LocalDate toDateCreation, LocalDate fromDateAttribution,
			LocalDate toDateAttribution, LocalDate fromDateEcheance, LocalDate toDateEcheance,
			LocalDate fromDateCloture, LocalDate toDateCloture, String numero, String nature, String branche,
			String objet, String statut, String origine, String agentIdrh) {
		this.fromDateCreation = fromDateCreation;
		this.toDateCreation = toDateCreation;
		this.fromDateAttribution = fromDateAttribution;
		this.toDateAttribution = toDateAttribution;
		this.fromDateEcheance = fromDateEcheance;
		this.toDateEcheance = toDateEcheance;
		this.fromDateCloture = fromDateCloture;
		this.toDateCloture = toDateCloture;
		this.numero = numero;
		this.nature = nature;
		this.branche = branche;
		this.objet = objet;
		this.statut = statut;
		this.origine = origine;
		this.agentIdrh = agentIdrh;
	}

	public LocalDate getFromDateCreation() {
		return fromDateCreation;
	}
	public void setFromDateCreation(LocalDate fromDateCreation) {
		this.fromDateCreation = fromDateCreation;
	}
	public LocalDate getToDateCreation() {
		return toDateCreation;
	}
	public void setToDateCreation(LocalDate toDateCreation) {
		this.toDateCreation = toDateCreation;
	}
	public LocalDate getFromDateAttribution() {
		return fromDateAttribution;
	}
	public void setFromDateAttribution(LocalDate fromDateAttribution) {
		this.fromDateAttribution = fromDateAttribution;
	}
	public LocalDate getToDateAttribution() {
		return toDateAttribution;
	}
	public void setToDateAttribution(LocalDate toDateAttribution) {
		this.toDateAttribution = toDateAttribution;
	}
	public LocalDate getFromDateEcheance() {
		return fromDateEcheance;
	}
	public void setFromDateEcheance(LocalDate fromDateEcheance) {
		this.fromDateEcheance = fromDateEcheance;
	}
	public LocalDate getToDateEcheance() {
		return toDateEcheance;
	}
	public void setToDateEcheance(LocalDate toDateEcheance) {
		this.toDateEcheance = toDateEcheance;
	}
	public LocalDate getFromDateCloture() {
		return fromDateCloture;
	}
	public void setFromDateCloture(LocalDate fromDateCloture) {
		this.fromDateCloture = fromDateCloture;
	}
	public LocalDate getToDateCloture() {
		return toDateCloture;
	}
	public void setToDateCloture(LocalDate toDateCloture) {
		this.toDateCloture = toDateCloture;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getNature() {
		return nature;
	}
	public void setNature(String nature) {
		this.nature = nature;
	}
	public String getBranche() {
		return branche;
	}
	public void setBranche(String branche) {
		this.branche = branche;
	}
	public String getObjet() {
		return objet;
	}
	public void setObjet(String objet) {
		this.objet = objet;
	}
	public String getStatut() {
		return statut;
	}
	public void setStatut(String statut) {
		this.statut = statut;
	}
	public String getOrigine() {
		return origine;
	}
	public void setOrigine(String origine) {
		this.origine = origine;
	}
	public String getAgentIdrh() {
		return agentIdrh;
	}
	public void setAgentIdrh(String agentIdrh) {
		this.agentIdrh = agentIdrh;
	}
	
}
