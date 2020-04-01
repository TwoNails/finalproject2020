package co.simplon.finalproject2020.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "AGENT")
public class Agent {
	
	@Id
	@Column(name = "ID_AGENT")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "CMATRICULE", nullable = false, length = 7)
	private String identifiantRH;
	
	@Column(name = "LNOM", nullable = false, length = 40)
	private String nom;
	
	@Column(name = "LPRENOM", nullable = false, length = 30)
	private String prenom;
														/* m f (a?)*/
	@Column(name = "CCIVILITE", /*nullable = false,*/ length = 1)
	private String civilite;
	
	@Column(name = "CGRADE", length = 6)
	private String grade;
	
	@Column(name = "DNAISSANCE"/*, nullable = false*/)
	@Temporal(TemporalType.DATE)
	private Date dateNaissance;
	
	@Column(name = "DENTREE_POSTE"/*, nullable = false*/)
	@Temporal(TemporalType.DATE)
	private Date dateEntree;
	
	@Column(name = "NTELEPHONE", nullable = true, length = 16)
	private String numTelephone;
										/* the DTO should allow null here, but then build a mail nom.prenom@laposte.fr as default */
	@Column(name = "LMAIL"/*, nullable = false*/)
	private String adresseMail;
	
	// relations
	@ManyToOne
	@JoinColumn(name = "ID_UTILISATEUR", referencedColumnName = "ID_AGENT")
	private Agent correpondant;
	
	@ManyToOne
	@JoinColumn(name = "CREGATE", referencedColumnName = "CREGATE")
	private Entite entite;
	
	@OneToOne
	@JoinColumn(name = "CI_ADRESSE", referencedColumnName = "ID")
	private Adresse adresse;

	
	/* GETTERS / SETTERS */
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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

	public String getCivilite() {
		return civilite;
	}
	public void setCivilite(String civilite) {
		this.civilite = civilite;
	}

	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}

	public Date getDateNaissance() {
		return dateNaissance;
	}
	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public Date getDateEntree() {
		return dateEntree;
	}
	public void setDateEntree(Date dateEntree) {
		this.dateEntree = dateEntree;
	}

	public String getNumTelephone() {
		return numTelephone;
	}
	public void setNumTelephone(String numTelephone) {
		this.numTelephone = numTelephone;
	}

	public String getAdresseMail() {
		return adresseMail;
	}
	public void setAdresseMail(String adresseMail) {
		this.adresseMail = adresseMail;
	}

	public Agent getCorrepondant() {
		return correpondant;
	}
	public void setCorrepondant(Agent correpondant) {
		this.correpondant = correpondant;
	}

	public Entite getEntite() {
		return entite;
	}
	public void setEntite(Entite entite) {
		this.entite = entite;
	}

	public Adresse getAdresse() {
		return adresse;
	}
	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}
	
	/* IMPORTANT OVERRIDES */
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((adresse == null) ? 0 : adresse.hashCode());
		result = prime * result + ((adresseMail == null) ? 0 : adresseMail.hashCode());
		result = prime * result + ((civilite == null) ? 0 : civilite.hashCode());
		result = prime * result + ((correpondant == null) ? 0 : correpondant.hashCode());
		result = prime * result + ((dateEntree == null) ? 0 : dateEntree.hashCode());
		result = prime * result + ((dateNaissance == null) ? 0 : dateNaissance.hashCode());
		result = prime * result + ((entite == null) ? 0 : entite.hashCode());
		result = prime * result + ((grade == null) ? 0 : grade.hashCode());
		result = prime * result + id;
		result = prime * result + ((identifiantRH == null) ? 0 : identifiantRH.hashCode());
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		result = prime * result + ((numTelephone == null) ? 0 : numTelephone.hashCode());
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
		Agent other = (Agent) obj;
		if (adresse == null) {
			if (other.adresse != null)
				return false;
		} else if (!adresse.equals(other.adresse))
			return false;
		if (adresseMail == null) {
			if (other.adresseMail != null)
				return false;
		} else if (!adresseMail.equals(other.adresseMail))
			return false;
		if (civilite == null) {
			if (other.civilite != null)
				return false;
		} else if (!civilite.equals(other.civilite))
			return false;
		if (correpondant == null) {
			if (other.correpondant != null)
				return false;
		} else if (!correpondant.equals(other.correpondant))
			return false;
		if (dateEntree == null) {
			if (other.dateEntree != null)
				return false;
		} else if (!dateEntree.equals(other.dateEntree))
			return false;
		if (dateNaissance == null) {
			if (other.dateNaissance != null)
				return false;
		} else if (!dateNaissance.equals(other.dateNaissance))
			return false;
		if (entite == null) {
			if (other.entite != null)
				return false;
		} else if (!entite.equals(other.entite))
			return false;
		if (grade == null) {
			if (other.grade != null)
				return false;
		} else if (!grade.equals(other.grade))
			return false;
		if (id != other.id)
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
		if (numTelephone == null) {
			if (other.numTelephone != null)
				return false;
		} else if (!numTelephone.equals(other.numTelephone))
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
		return "Agent [id=" + id + ", identifiantRH=" + identifiantRH + ", nom=" + nom + ", prenom=" + prenom
				+ ", civilite=" + civilite + ", grade=" + grade + ", dateNaissance=" + dateNaissance + ", dateEntree="
				+ dateEntree + ", numTelephone=" + numTelephone + ", adresseMail=" + adresseMail + ", correpondant="
				+ correpondant + ", entite=" + entite + ", adresse=" + adresse + "]";
	}
	
	

}
