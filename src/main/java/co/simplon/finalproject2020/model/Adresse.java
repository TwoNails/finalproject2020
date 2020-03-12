package co.simplon.finalproject2020.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ADRESSE")
public class Adresse {
	
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
													/* direction/service, n° etage */
	@Column(name = "LLIGNE2", nullable = true, length = 48)
	private String ligne2;
	
													/* n° de l'entree / du batiment / de la Z.I. */
	@Column(name = "LLIGNE3", nullable = true, length = 48)
	private String ligne3;
	
													/* n°, type et nom de la voie */
	@Column(name = "LLIGNE4", nullable = true, length = 48)
	private String ligne4;
	
													/* Cedex si applicable (BP et Poste Restante) */
	@Column(name = "LLIGNE5", nullable = true, length = 48)
	private String ligne5;
	
													/* Code Postal et Ville */
	@Column(name = "LLIGNE6", nullable = true, length = 48)
	private String ligne6;
	
													/* Pays */
	@Column(name = "LLIGNE7", nullable = true, length = 48)
	private String ligne7;
	

	/* GETTERS / SETTERS */
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getLigne2() {
		return ligne2;
	}
	public void setLigne2(String ligne2) {
		this.ligne2 = ligne2;
	}

	public String getLigne3() {
		return ligne3;
	}
	public void setLigne3(String ligne3) {
		this.ligne3 = ligne3;
	}

	public String getLigne4() {
		return ligne4;
	}
	public void setLigne4(String ligne4) {
		this.ligne4 = ligne4;
	}

	public String getLigne5() {
		return ligne5;
	}
	public void setLigne5(String ligne5) {
		this.ligne5 = ligne5;
	}

	public String getLigne6() {
		return ligne6;
	}
	public void setLigne6(String ligne6) {
		this.ligne6 = ligne6;
	}

	public String getLigne7() {
		return ligne7;
	}
	public void setLigne7(String ligne7) {
		this.ligne7 = ligne7;
	}

	/* IMPORTANT OVERRIDES */
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((ligne2 == null) ? 0 : ligne2.hashCode());
		result = prime * result + ((ligne3 == null) ? 0 : ligne3.hashCode());
		result = prime * result + ((ligne4 == null) ? 0 : ligne4.hashCode());
		result = prime * result + ((ligne5 == null) ? 0 : ligne5.hashCode());
		result = prime * result + ((ligne6 == null) ? 0 : ligne6.hashCode());
		result = prime * result + ((ligne7 == null) ? 0 : ligne7.hashCode());
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
		Adresse other = (Adresse) obj;
		if (id != other.id)
			return false;
		if (ligne2 == null) {
			if (other.ligne2 != null)
				return false;
		} else if (!ligne2.equals(other.ligne2))
			return false;
		if (ligne3 == null) {
			if (other.ligne3 != null)
				return false;
		} else if (!ligne3.equals(other.ligne3))
			return false;
		if (ligne4 == null) {
			if (other.ligne4 != null)
				return false;
		} else if (!ligne4.equals(other.ligne4))
			return false;
		if (ligne5 == null) {
			if (other.ligne5 != null)
				return false;
		} else if (!ligne5.equals(other.ligne5))
			return false;
		if (ligne6 == null) {
			if (other.ligne6 != null)
				return false;
		} else if (!ligne6.equals(other.ligne6))
			return false;
		if (ligne7 == null) {
			if (other.ligne7 != null)
				return false;
		} else if (!ligne7.equals(other.ligne7))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Adresse [id=" + id + ", ligne2=" + ligne2 + ", ligne3=" + ligne3 + ", ligne4=" + ligne4 + ", ligne5="
				+ ligne5 + ", ligne6=" + ligne6 + ", ligne7=" + ligne7 + "]";
	}
	
}



