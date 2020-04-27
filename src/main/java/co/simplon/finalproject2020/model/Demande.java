package co.simplon.finalproject2020.model;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import co.simplon.finalproject2020.model.AttachedDocument;

@Entity
public class Demande /* implements Serializable */ {
	
	// static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "ID_DEMANDE")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	// généré par le service lors de l'instanciation. Les deux premiers chiffres dépendent de la nature (01 à 05), les deux suivants représentent l'année en cours et les 4 suivants s'incrémentent.
	@Column(name = "NNUMERO", nullable = false)
	private String numero;
	
	@Column(name = "OBJET")
	private String objet;
	
	@Column(name = "LCOMMENT", nullable = true/*, columnDefinition = "CLOB"*/)
	private String commentaire;
	
	// dates :
	@Column(name = "DCREATION", nullable = false)		/* the DTO should allow null here. If we're instanciating, we initialize DCREATION as current date */
	// @Temporal(TemporalType.DATE) apparently not ok with LocalDate (java.time) and rather used with Date (java.util)
	private LocalDate dateCreation;
	
//	@Column(name = "DATTRIBUTION", nullable = false)
	private LocalDate dateAttribution;
	
//	@Column(name = "DCLOTURE", nullable = false)
	private LocalDate dateCloture;

	/* Will be calculated at creation, its value will depend of the demand's nature */
//	@Column(name = "DECHEANCE", nullable = true)
	private LocalDate dateEcheance;
	
	
	// relationships :
	@OneToMany
	@JoinColumn(name = "ID_DEMANDE")
	private List<AttachedDocument> listeDocuments;
	
	@ManyToOne
	@JoinColumn(name = "ID_ORIGINE")
	private Origine origine;
	
	@ManyToOne
	@JoinColumn(name = "ID_TYPE", referencedColumnName = "ID_TYPE_DEMANDE")
	private TypeDemande type;
	
	@ManyToOne
	@JoinColumn(name = "ID_NATURE", referencedColumnName = "ID_NATURE")
	private Nature nature;
	
	@ManyToOne
	@JoinColumn(name = "ID_STATUT", referencedColumnName = "ID_STATUT")
	private Statut statut;
	
	@ManyToOne
	@JoinColumn(name = "ID_AGENT", referencedColumnName = "ID_AGENT")
	private Agent agent;
	
	@ManyToOne
	@JoinColumn(name = "IDRH_GESTIONNAIRE", referencedColumnName = "CMATRICULE")
	private Utilisateur responsable;
	
	
	// CONSTRUCTORS
	public Demande() {
		// System.out.println("valeur numero au moment de l'instanciation " + numero);
	}
																					// contructeur utilisé par le service lors de la conversion du DTO
	public Demande(String numero, Nature nature, String objet,
			LocalDate dateCreation, LocalDate dateEcheance, List<AttachedDocument> listeDocuments, TypeDemande type,
			Origine origine, Statut statut, Agent agent) {
		System.out.println("valeur numero au moment de l'instanciation " + numero);
		this.numero = numero;
		this.nature = nature;
		this.objet = objet;
		this.dateCreation = dateCreation;
		this.dateEcheance = dateEcheance;
		this.listeDocuments = listeDocuments;
		this.type = type;
		this.origine = origine;
		this.statut = statut;
		this.agent = agent;
	}
																					// contructeur utilisé par le service lors de la conversion du DTO
	public Demande(String numero, Nature nature, String objet,
			LocalDate dateCreation, LocalDate dateEcheance, List<AttachedDocument> listeDocuments, TypeDemande type,
			Origine origine, Statut statut, Agent agent, Utilisateur responsable) {
		this.numero = numero;
		this.nature = nature;
		this.objet = objet;
		this.dateCreation = dateCreation;
		this.dateEcheance = dateEcheance;
		this.listeDocuments = listeDocuments;
		this.type = type;
		this.origine = origine;
		this.statut = statut;
		this.agent = agent;
		this.responsable = responsable;
	}

	public Demande(String numero, Nature nature, String objet,
			String commentaire, LocalDate dateCreation, LocalDate dateAttribution, LocalDate dateCloture,
			LocalDate dateEcheance, List<AttachedDocument> listeDocuments, TypeDemande type, Origine origine, Agent agent,
			Utilisateur responsable) {
		this.numero = numero;
		this.nature = nature;
		this.objet = objet;
		this.commentaire = commentaire;
		this.dateCreation = dateCreation;
		this.dateAttribution = dateAttribution;
		this.dateCloture = dateCloture;
		this.dateEcheance = dateEcheance;
		this.listeDocuments = listeDocuments;
		this.type = type;
		this.origine = origine;
		this.agent = agent;
		this.responsable = responsable;
	}
	
	// IMPORTANT OVERRRIDES
	@Override
	public String toString() {
		return "Demande [id=" + id + ", numero=" + numero + ", objet=" + objet + ", commentaire=" + commentaire
				+ ", dateCreation=" + dateCreation + ", dateAttribution=" + dateAttribution + ", dateCloture="
				+ dateCloture + ", dateEcheance=" + dateEcheance + ", listeDocuments=" + listeDocuments + ", origine="
				+ origine + ", type=" + type + ", nature=" + nature + ", statut=" + statut + ", agent=" + agent
				+ ", responsable=" + responsable + "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((agent == null) ? 0 : agent.hashCode());
		result = prime * result + ((commentaire == null) ? 0 : commentaire.hashCode());
		result = prime * result + ((dateAttribution == null) ? 0 : dateAttribution.hashCode());
		result = prime * result + ((dateCloture == null) ? 0 : dateCloture.hashCode());
		result = prime * result + ((dateCreation == null) ? 0 : dateCreation.hashCode());
		result = prime * result + ((dateEcheance == null) ? 0 : dateEcheance.hashCode());
		result = prime * result + id;
		result = prime * result + ((listeDocuments == null) ? 0 : listeDocuments.hashCode());
		result = prime * result + ((nature == null) ? 0 : nature.hashCode());
		result = prime * result + ((numero == null) ? 0 : numero.hashCode());
		result = prime * result + ((objet == null) ? 0 : objet.hashCode());
		result = prime * result + ((origine == null) ? 0 : origine.hashCode());
		result = prime * result + ((responsable == null) ? 0 : responsable.hashCode());
		result = prime * result + ((statut == null) ? 0 : statut.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		Demande other = (Demande) obj;
		if (agent == null) {
			if (other.agent != null)
				return false;
		} else if (!agent.equals(other.agent))
			return false;
		if (commentaire == null) {
			if (other.commentaire != null)
				return false;
		} else if (!commentaire.equals(other.commentaire))
			return false;
		if (dateAttribution == null) {
			if (other.dateAttribution != null)
				return false;
		} else if (!dateAttribution.equals(other.dateAttribution))
			return false;
		if (dateCloture == null) {
			if (other.dateCloture != null)
				return false;
		} else if (!dateCloture.equals(other.dateCloture))
			return false;
		if (dateCreation == null) {
			if (other.dateCreation != null)
				return false;
		} else if (!dateCreation.equals(other.dateCreation))
			return false;
		if (dateEcheance == null) {
			if (other.dateEcheance != null)
				return false;
		} else if (!dateEcheance.equals(other.dateEcheance))
			return false;
		if (id != other.id)
			return false;
		if (listeDocuments == null) {
			if (other.listeDocuments != null)
				return false;
		} else if (!listeDocuments.equals(other.listeDocuments))
			return false;
		if (nature == null) {
			if (other.nature != null)
				return false;
		} else if (!nature.equals(other.nature))
			return false;
		if (numero == null) {
			if (other.numero != null)
				return false;
		} else if (!numero.equals(other.numero))
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
		if (responsable == null) {
			if (other.responsable != null)
				return false;
		} else if (!responsable.equals(other.responsable))
			return false;
		if (statut == null) {
			if (other.statut != null)
				return false;
		} else if (!statut.equals(other.statut))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}
	
	// GETTERS / SETTERS
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Nature getNature() {
		return nature;
	}
	public void setNature(Nature nature) {
		this.nature = nature;
	}

	public Origine getOrigine() {
		return origine;
	}
	public void setOrigineDemande(Origine origine) {
		this.origine = origine;
	}
	
	public String getObjet() {
		return objet;
	}
	public void setObjet(String objet) {
		this.objet = objet;
	}

	public String getCommentaire() {
		return commentaire;
	}
	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

	public LocalDate getDateCreation() {
		return dateCreation;
	}
	public void setDateCreation(LocalDate dateCreation) {
		this.dateCreation = dateCreation;
	}

	public LocalDate getDateAttribution() {
		return dateAttribution;
	}
	public void setDateAttribution(LocalDate dateAttribution) {
		this.dateAttribution = dateAttribution;
	}

	public LocalDate getDateCloture() {
		return dateCloture;
	}
	public void setDateCloture(LocalDate dateCloture) {
		this.dateCloture = dateCloture;
	}

	public LocalDate getDateEcheance() {
		return dateEcheance;
	}
	public void setDateEcheance(LocalDate dateEcheance) {
		this.dateEcheance = dateEcheance;
	}

	public List<AttachedDocument> getListeDocuments() {
		return listeDocuments;
	}
	public void setListeDocuments(List<AttachedDocument> listeDocuments) {
		this.listeDocuments = listeDocuments;
	}

	public TypeDemande getType() {
		return type;
	}
	public void setType(TypeDemande type) {
		this.type = type;
	}

	public Statut getStatut() {
		return statut;
	}
	public void setStatut(Statut statut) {
		this.statut = statut;
	}
	
	public void setOrigine(Origine origine) {
		this.origine = origine;
	}
	public Agent getAgent() {
		return agent;
	}
	public void setAgent(Agent agent) {
		this.agent = agent;
	}

	public Utilisateur getResponsable() {
		return responsable;
	}
	public void setResponsable(Utilisateur responsable) {
		this.responsable = responsable;
	}
	
	
	
	

}
