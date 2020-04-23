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
	
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((agentIdrh == null) ? 0 : agentIdrh.hashCode());
		result = prime * result + ((branche == null) ? 0 : branche.hashCode());
		result = prime * result + ((fromDateAttribution == null) ? 0 : fromDateAttribution.hashCode());
		result = prime * result + ((fromDateCloture == null) ? 0 : fromDateCloture.hashCode());
		result = prime * result + ((fromDateCreation == null) ? 0 : fromDateCreation.hashCode());
		result = prime * result + ((fromDateEcheance == null) ? 0 : fromDateEcheance.hashCode());
		result = prime * result + ((nature == null) ? 0 : nature.hashCode());
		result = prime * result + ((numero == null) ? 0 : numero.hashCode());
		result = prime * result + ((objet == null) ? 0 : objet.hashCode());
		result = prime * result + ((origine == null) ? 0 : origine.hashCode());
		result = prime * result + ((statut == null) ? 0 : statut.hashCode());
		result = prime * result + ((toDateAttribution == null) ? 0 : toDateAttribution.hashCode());
		result = prime * result + ((toDateCloture == null) ? 0 : toDateCloture.hashCode());
		result = prime * result + ((toDateCreation == null) ? 0 : toDateCreation.hashCode());
		result = prime * result + ((toDateEcheance == null) ? 0 : toDateEcheance.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DemandeCriteria other = (DemandeCriteria) obj;
		if (agentIdrh == null) {
			if (other.agentIdrh != null)
				return false;
		} else if (!agentIdrh.equals(other.agentIdrh))
			return false;
		if (branche == null) {
			if (other.branche != null)
				return false;
		} else if (!branche.equals(other.branche))
			return false;
		if (fromDateAttribution == null) {
			if (other.fromDateAttribution != null)
				return false;
		} else if (!fromDateAttribution.equals(other.fromDateAttribution))
			return false;
		if (fromDateCloture == null) {
			if (other.fromDateCloture != null)
				return false;
		} else if (!fromDateCloture.equals(other.fromDateCloture))
			return false;
		if (fromDateCreation == null) {
			if (other.fromDateCreation != null)
				return false;
		} else if (!fromDateCreation.equals(other.fromDateCreation))
			return false;
		if (fromDateEcheance == null) {
			if (other.fromDateEcheance != null)
				return false;
		} else if (!fromDateEcheance.equals(other.fromDateEcheance))
			return false;
		if (nature == null) {
			if (other.nature != null)
				return false;
		} else if (!nature.equals(other.nature))
			return false;
		if (numero == null) {
			if (other.numero != null)
				return false;
		} else if (!numero.equals(other.numero))
			return false;
		if (objet == null) {
			if (other.objet != null)
				return false;
		} else if (!objet.equals(other.objet))
			return false;
		if (origine == null) {
			if (other.origine != null)
				return false;
		} else if (!origine.equals(other.origine))
			return false;
		if (statut == null) {
			if (other.statut != null)
				return false;
		} else if (!statut.equals(other.statut))
			return false;
		if (toDateAttribution == null) {
			if (other.toDateAttribution != null)
				return false;
		} else if (!toDateAttribution.equals(other.toDateAttribution))
			return false;
		if (toDateCloture == null) {
			if (other.toDateCloture != null)
				return false;
		} else if (!toDateCloture.equals(other.toDateCloture))
			return false;
		if (toDateCreation == null) {
			if (other.toDateCreation != null)
				return false;
		} else if (!toDateCreation.equals(other.toDateCreation))
			return false;
		if (toDateEcheance == null) {
			if (other.toDateEcheance != null)
				return false;
		} else if (!toDateEcheance.equals(other.toDateEcheance))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "DemandeCriteria [fromDateCreation=" + fromDateCreation + ", toDateCreation=" + toDateCreation
				+ ", fromDateAttribution=" + fromDateAttribution + ", toDateAttribution=" + toDateAttribution
				+ ", fromDateEcheance=" + fromDateEcheance + ", toDateEcheance=" + toDateEcheance + ", fromDateCloture="
				+ fromDateCloture + ", toDateCloture=" + toDateCloture + ", numero=" + numero + ", nature=" + nature
				+ ", branche=" + branche + ", objet=" + objet + ", statut=" + statut + ", origine=" + origine
				+ ", agentIdrh=" + agentIdrh + "]";
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
