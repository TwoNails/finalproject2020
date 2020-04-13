package co.simplon.finalproject2020.model.dto;

public class TypeDemandeDTO {

	// sera renvoyé dans la demandeDTO pour permettre d'identifier le type et la nature de la demande
	private String cType; 
	
	// sera affiché dans le menu déroulant du champ 'type' du formulaire
	private String lType;
	
	// sera affiché dans le menu déroulant du champ 'nature' du formulaire
	private String lNature;
	
	// CONSTRUCTORS
	public TypeDemandeDTO() {
	}
	
	public TypeDemandeDTO(String cType, String lType, String lNature) {
		this.cType = cType;
		this.lType = lType;
		this.lNature = lNature;
	}
	
	// IMPORTANT OVERRIDES
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cType == null) ? 0 : cType.hashCode());
		result = prime * result + ((lNature == null) ? 0 : lNature.hashCode());
		result = prime * result + ((lType == null) ? 0 : lType.hashCode());
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
		TypeDemandeDTO other = (TypeDemandeDTO) obj;
		if (cType == null) {
			if (other.cType != null)
				return false;
		} else if (!cType.equals(other.cType))
			return false;
		if (lNature == null) {
			if (other.lNature != null)
				return false;
		} else if (!lNature.equals(other.lNature))
			return false;
		if (lType == null) {
			if (other.lType != null)
				return false;
		} else if (!lType.equals(other.lType))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TypeDemandeDTO [cType=" + cType + ", lType=" + lType + ", lNature=" + lNature + "]";
	}


	// GETTERS / SETTERS
	public String getcType() {
		return cType;
	}
	public void setcType(String cType) {
		this.cType = cType;
	}

	public String getlType() {
		return lType;
	}
	public void setlType(String lType) {
		this.lType = lType;
	}

	public String getlNature() {
		return lNature;
	}
	public void setlNature(String lNature) {
		this.lNature = lNature;
	}
	
	


	
	
	
}
