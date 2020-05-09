package co.simplon.finalproject2020.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class TypeDemande {
	
	@Id
	@Column(name = "ID_TYPE_DEMANDE")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "LTYPE")
	private String libelle;
	
	@Column(name = "CTYPE")
	private String code;
	
	@Column(name = "ECHEANCE")
	private int echeance;		// nbr de jours
	
	
	// Relationships
	@ManyToOne
	@JoinColumn(name = "ID_NATURE")
	private Nature nature;

	
	// CONSTRUCTORS
	public TypeDemande() {
	}
	public TypeDemande(Nature nature, String libelle, String code, int echeance) {
		this.nature = nature;
		this.libelle = libelle;
		this.code = code;
		this.echeance = echeance;
	}
	
	// IMPORTANT OVERRIDES
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + echeance;
		result = prime * result + id;
		result = prime * result + ((libelle == null) ? 0 : libelle.hashCode());
		result = prime * result + ((nature == null) ? 0 : nature.hashCode());
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
		TypeDemande other = (TypeDemande) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (echeance != other.echeance)
			return false;
		if (id != other.id)
			return false;
		if (libelle == null) {
			if (other.libelle != null)
				return false;
		} else if (!libelle.equals(other.libelle))
			return false;
		if (nature != other.nature)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "TypeDemande [id=" + id + ", nature=" + nature + ", libelle=" + libelle + ", code=" + code
				+ ", echeance=" + echeance + "]";
	}
	
	
	// GETTERS / SETTERS
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public Nature getNature() {
		return nature;
	}
	public void setNature(Nature nature) {
		this.nature = nature;
	}

	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}

	public int getEcheance() {
		return echeance;
	}
	public void setEcheance(int echeance) {
		this.echeance = echeance;
	}
	
	
	
}
