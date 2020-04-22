package co.simplon.finalproject2020.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ENTITE")
public class Entite {
	
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "LLENTITE")
	private String libelle;
	
	@Column(name = "CREGATE")
	private String codeRegate;
	
	@Column(name = "CBRANCHE", nullable = false, length = 4)
	private String branche;
	
	@OneToOne
	@JoinColumn(name ="ID_ADRESSE", referencedColumnName = "ID")
	private Adresse adresse;
	
	/* GETTERS / SETTERS */
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public String getCodeRegate() {
		return codeRegate;
	}
	public void setCodeRegate(String codeRegate) {
		this.codeRegate = codeRegate;
	}

	public String getBranche() {
		return branche;
	}
	public void setBranche(String branche) {
		this.branche = branche;
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
		result = prime * result + ((branche == null) ? 0 : branche.hashCode());
		result = prime * result + ((codeRegate == null) ? 0 : codeRegate.hashCode());
		result = prime * result + id;
		result = prime * result + ((libelle == null) ? 0 : libelle.hashCode());
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
		Entite other = (Entite) obj;
		if (adresse == null) {
			if (other.adresse != null)
				return false;
		} else if (!adresse.equals(other.adresse))
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
		if (id != other.id)
			return false;
		if (libelle == null) {
			if (other.libelle != null)
				return false;
		} else if (!libelle.equals(other.libelle))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Entite [id=" + id + ", libelle=" + libelle + ", codeRegate=" + codeRegate + ", branche=" + branche
				+ ", adresse=" + adresse + "]";
	}
	
	
	
	
	
}
