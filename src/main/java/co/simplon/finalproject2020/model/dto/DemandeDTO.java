package co.simplon.finalproject2020.model.dto;

import java.util.List;
import java.util.Set;

import co.simplon.finalproject2020.model.AttachedDocument;

// mappe le JSON reçu depuis le formulaire de création d'une demande.
public class DemandeDTO {
		
		// idrh de l'agent concerné par la demande
		private String idrh;
		
		// libellé du type de la demande. Permettra de décrire et le type et la nature de la demande
		private String codeType;
		
		private String objet;
		
		private String origine;
		
		// matricule du gestionnaire a qui est attribuée la demande. optionnel
		private String matriculeGestionnaire;
		
		// liste des pieces jointes à la demande
		// private List<AttachedDocument> listeDocuments;

		
		
		// CONSTRUCTORS	
		public DemandeDTO() {
		}

		public DemandeDTO(String idrh, String libelleType, String objet, String origine, String matriculeGestionnaire/*,
				List<AttachedDocument> listeDocuments*/) {
			this.idrh = idrh;
			this.codeType = libelleType;
			this.objet = objet;
			this.origine = origine;
			this.matriculeGestionnaire = matriculeGestionnaire;
			//this.listeDocuments = listeDocuments;
		}
		
		
		// IMPORTANT OVERRIDES
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((idrh == null) ? 0 : idrh.hashCode());
			result = prime * result + ((codeType == null) ? 0 : codeType.hashCode());
			// result = prime * result + ((listeDocuments == null) ? 0 : listeDocuments.hashCode());
			result = prime * result + ((matriculeGestionnaire == null) ? 0 : matriculeGestionnaire.hashCode());
			result = prime * result + ((objet == null) ? 0 : objet.hashCode());
			result = prime * result + ((origine == null) ? 0 : origine.hashCode());
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
			DemandeDTO other = (DemandeDTO) obj;
			if (idrh == null) {
				if (other.idrh != null)
					return false;
			} else if (!idrh.equals(other.idrh))
				return false;
			if (codeType == null) {
				if (other.codeType != null)
					return false;
			} else if (!codeType.equals(other.codeType))
				return false;
			/*
			if (listeDocuments == null) {
				if (other.listeDocuments != null)
					return false;
			} else if (!listeDocuments.equals(other.listeDocuments))
				return false; */
			if (matriculeGestionnaire == null) {
				if (other.matriculeGestionnaire != null)
					return false;
			} else if (!matriculeGestionnaire.equals(other.matriculeGestionnaire))
				return false;
			if (objet == null) {
				if (other.objet != null)
					return false;
			} else if (!objet.equals(other.objet))
				return false;
			if (origine == null) {
				if (other.origine != null)
					return false;
			} else if (!origine.equals(other.origine))
				return false;
			return true;
		}

		@Override
		public String toString() {
			return "DemandeDTO [idrh=" + idrh + ", codeType=" + codeType + ", objet=" + objet + ", origine="
					+ origine + ", matriculeGestionnaire=" + matriculeGestionnaire + ", listeDocuments="
					/* + listeDocuments*/ + "]";
		}

		// GETTERS / SETTERS
		public String getIdrh() {
			return idrh;
		}
		public void setIdrh(String idrh) {
			this.idrh = idrh;
		}

		public String getCodeType() {
			return codeType;
		}
		public void setCodeType(String codeType) {
			this.codeType = codeType;
		}

		public String getObjet() {
			return objet;
		}
		public void setObjet(String objet) {
			this.objet = objet;
		}

		public String getOrigine() {
			return origine;
		}
		public void setOrigine(String origine) {
			this.origine = origine;
		}

		public String getMatriculeGestionnaire() {
			return matriculeGestionnaire;
		}
		public void setMatriculeGestionnaire(String matriculeGestionnaire) {
			this.matriculeGestionnaire = matriculeGestionnaire;
		}
		
		/*
		public Set<AttachedDocument> getListeDocuments() {
			return listeDocuments;
		}
		public void setListeDocuments(Set<AttachedDocument> listeDocuments) {
			this.listeDocuments = listeDocuments;
		}*/
		
		
	
}
