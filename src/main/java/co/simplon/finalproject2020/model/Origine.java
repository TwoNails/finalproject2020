package co.simplon.finalproject2020.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ORIGINE")
public class Origine {
	
	@Id
	@Column(name = "ID_ORIGINE")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "LORIGINE", nullable = false, length = 10)
	private String libelle;
	
	
	// CONSTRUCTORS
	public Origine() {
	}

	public Origine(String libelle) {
		this.libelle = libelle;
	}
	
	// IMPORTANT OVERRIDES
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		Origine other = (Origine) obj;
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
		return "Origine [id=" + id + ", libelle=" + libelle + "]";
	}

	// GETTERS / SETTERS
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
	
	
	
}
