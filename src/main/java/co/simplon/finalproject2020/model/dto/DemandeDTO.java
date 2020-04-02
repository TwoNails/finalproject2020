package co.simplon.finalproject2020.model.dto;

import java.util.List;

import co.simplon.finalproject2020.model.AttachedDocument;

// mappe le JSON reçu depuis le formulaire de création d'une demande. (Peut être utilisé comme argument d'un constructeur de Demande ? Bonne ou mauvaise idée ?)
public class DemandeDTO {
		
		// IDRH de l'agent concerné par la demande
		private String IDRH;
		
		// libellé du type de la demande. Permettra de décrire et le type et la nature de la demande
		private String libelleType;
		
		private String objet;
		
		private String origine;
		
		// matricule du gestionnaire a qui est attribuée la demande. optionnel
		private String matriculeGestionnaire;
		
		// liste des pieces jointes à la demande
		private List<AttachedDocument> listeDocuments;

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((IDRH == null) ? 0 : IDRH.hashCode());
			result = prime * result + ((libelleType == null) ? 0 : libelleType.hashCode());
			result = prime * result + ((listeDocuments == null) ? 0 : listeDocuments.hashCode());
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
			if (IDRH == null) {
				if (other.IDRH != null)
					return false;
			} else if (!IDRH.equals(other.IDRH))
				return false;
			if (libelleType == null) {
				if (other.libelleType != null)
					return false;
			} else if (!libelleType.equals(other.libelleType))
				return false;
			if (listeDocuments == null) {
				if (other.listeDocuments != null)
					return false;
			} else if (!listeDocuments.equals(other.listeDocuments))
				return false;
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
			return "DemandeDTO [IDRH=" + IDRH + ", libelleType=" + libelleType + ", objet=" + objet + ", origine="
					+ origine + ", matriculeGestionnaire=" + matriculeGestionnaire + ", listeDocuments="
					+ listeDocuments + "]";
		}

		public DemandeDTO() {
			super();
		}

		public DemandeDTO(String iDRH, String libelleType, String objet, String origine, String matriculeGestionnaire,
				List<AttachedDocument> listeDocuments) {
			super();
			IDRH = iDRH;
			this.libelleType = libelleType;
			this.objet = objet;
			this.origine = origine;
			this.matriculeGestionnaire = matriculeGestionnaire;
			this.listeDocuments = listeDocuments;
		}

		public String getIDRH() {
			return IDRH;
		}

		public void setIDRH(String iDRH) {
			IDRH = iDRH;
		}

		public String getLibelleType() {
			return libelleType;
		}

		public void setLibelleType(String libelleType) {
			this.libelleType = libelleType;
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

		public List<AttachedDocument> getListeDocuments() {
			return listeDocuments;
		}

		public void setListeDocuments(List<AttachedDocument> listeDocuments) {
			this.listeDocuments = listeDocuments;
		}
		
		
	
}
