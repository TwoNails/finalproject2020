package co.simplon.finalproject2020.model.criteria;

public class UtilisateurCriteria {
	
	private String identifiantRH;
	private String nom;
	private String prenom;
	
	private String equipe;
	
	

	public UtilisateurCriteria() {
	}

	public UtilisateurCriteria(String identifiantRH, String nom, String prenom, String equipe) {
		this.identifiantRH = identifiantRH;
		this.nom = nom;
		this.prenom = prenom;
		this.equipe = equipe;
	}
	
	
	
	@Override
	public String toString() {
		return "UtilisateurCriteria [identifiantRH=" + identifiantRH + ", nom=" + nom + ", prenom=" + prenom
				+ ", equipe=" + equipe + "]";
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

	public String getEquipe() {
		return equipe;
	}
	public void setEquipe(String equipe) {
		this.equipe = equipe;
	}
	
	

}
