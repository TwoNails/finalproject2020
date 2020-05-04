package co.simplon.finalproject2020.model.criteria;

import java.time.Year;

public class AgentCriteria {
	
	private String identifiantRH;
	private String nom;
	private String prenom;
	private String branche;
	private String codeRegate;
	private String grade;
	
	private Year anneeNaissance;
	private Year anneeEntreePoste;
	
	
	
	
	public AgentCriteria() {
	}
	public AgentCriteria(String identifiantRH, String nom, String prenom, String branche, String codeRegate,
			String grade, Year anneeNaissance, Year anneeEntreePoste) {
		this.identifiantRH = identifiantRH;
		this.nom = nom;
		this.prenom = prenom;
		this.branche = branche;
		this.codeRegate = codeRegate;
		this.grade = grade;
		this.anneeNaissance = anneeNaissance;
		this.anneeEntreePoste = anneeEntreePoste;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((anneeEntreePoste == null) ? 0 : anneeEntreePoste.hashCode());
		result = prime * result + ((anneeNaissance == null) ? 0 : anneeNaissance.hashCode());
		result = prime * result + ((branche == null) ? 0 : branche.hashCode());
		result = prime * result + ((codeRegate == null) ? 0 : codeRegate.hashCode());
		result = prime * result + ((grade == null) ? 0 : grade.hashCode());
		result = prime * result + ((identifiantRH == null) ? 0 : identifiantRH.hashCode());
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		result = prime * result + ((prenom == null) ? 0 : prenom.hashCode());
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
		AgentCriteria other = (AgentCriteria) obj;
		if (anneeEntreePoste == null) {
			if (other.anneeEntreePoste != null)
				return false;
		} else if (!anneeEntreePoste.equals(other.anneeEntreePoste))
			return false;
		if (anneeNaissance == null) {
			if (other.anneeNaissance != null)
				return false;
		} else if (!anneeNaissance.equals(other.anneeNaissance))
			return false;
		if (branche == null) {
			if (other.branche != null)
				return false;
		} else if (!branche.equals(other.branche))
			return false;
		if (codeRegate == null) {
			if (other.codeRegate != null)
				return false;
		} else if (!codeRegate.equals(other.codeRegate))
			return false;
		if (grade == null) {
			if (other.grade != null)
				return false;
		} else if (!grade.equals(other.grade))
			return false;
		if (identifiantRH == null) {
			if (other.identifiantRH != null)
				return false;
		} else if (!identifiantRH.equals(other.identifiantRH))
			return false;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		if (prenom == null) {
			if (other.prenom != null)
				return false;
		} else if (!prenom.equals(other.prenom))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "AgentCriteria [identifiantRH=" + identifiantRH + ", nom=" + nom + ", prenom=" + prenom + ", branche="
				+ branche + ", codeRegate=" + codeRegate + ", grade=" + grade + ", anneeNaissance=" + anneeNaissance
				+ ", anneeEntreePoste=" + anneeEntreePoste + "]";
	}
	public String getIdentifiantRH() {
		return identifiantRH;
	}
	public void setIdentifiantRH(String identifiantRH) {
		this.identifiantRH = identifiantRH;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getBranche() {
		return branche;
	}
	public void setBranche(String branche) {
		this.branche = branche;
	}
	public String getCodeRegate() {
		return codeRegate;
	}
	public void setCodeRegate(String codeRegate) {
		this.codeRegate = codeRegate;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public Year getAnneeNaissance() {
		return anneeNaissance;
	}
	public void setAnneeNaissance(Year anneeNaissance) {
		this.anneeNaissance = anneeNaissance;
	}
	public Year getAnneeEntreePoste() {
		return anneeEntreePoste;
	}
	public void setAnneeEntreePoste(Year anneeEntreePoste) {
		this.anneeEntreePoste = anneeEntreePoste;
	}

	
}
