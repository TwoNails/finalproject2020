package co.simplon.finalproject2020.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "UTILISATEUR")
public class Utilisateur {
	
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "CMATRICULE", nullable = false, length = 7)
	private String identifiantRH;
	
	@Column(name = "LNOM", nullable = false, length = 40)
	private String nom;
	
	@Column(name = "LPRENOM", nullable = false, length = 30)
	private String prenom;

	
	/* GETTERS / SETTERS */
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getMatricule() {
		return identifiantRH;
	}
	public void setMatricule(String matricule) {
		this.identifiantRH = matricule;
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
	
	/* IMPORTANT OVERRIDES */
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		Utilisateur other = (Utilisateur) obj;
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
		if (prenom == null) {
			if (other.prenom != null)
				return false;
		} else if (!prenom.equals(other.prenom))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Utilisateur [id=" + id + ", identifiantRH=" + identifiantRH + ", nom=" + nom + ", prenom=" + prenom + "]";
	}	
}
