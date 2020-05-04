package co.simplon.finalproject2020.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "GRADE")
public class Grade implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4913973595990788620L;

	@Id
	@Column(name = "ID_GRADE")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "LGRADE", nullable = false)
	private String libelle;
	
	@Column(name = "CGRADE", nullable = false)
	private String code;

	@Column(name = "CNIV_GRADE", nullable = false)
	private String niveau;

	// CONSTRUCTORS
	
	public Grade() {
	}
	
	public Grade(String libelle, String code, String niveau) {
		this.libelle = libelle;
		this.code = code;
		this.niveau = niveau;
	}
	
	// IMPORTANT OVERRIDES
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + id;
		result = prime * result + ((libelle == null) ? 0 : libelle.hashCode());
		result = prime * result + ((niveau == null) ? 0 : niveau.hashCode());
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
		Grade other = (Grade) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (id != other.id)
			return false;
		if (libelle == null) {
			if (other.libelle != null)
				return false;
		} else if (!libelle.equals(other.libelle))
			return false;
		if (niveau == null) {
			if (other.niveau != null)
				return false;
		} else if (!niveau.equals(other.niveau))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Grade [id=" + id + ", libelle=" + libelle + ", code=" + code + ", niveau=" + niveau + "]";
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

	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}

	public String getNiveau() {
		return niveau;
	}
	public void setNiveau(String niveau) {
		this.niveau = niveau;
	}
	
	
	

}
