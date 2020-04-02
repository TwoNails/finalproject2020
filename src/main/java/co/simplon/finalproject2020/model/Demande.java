package co.simplon.finalproject2020.model;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

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

import co.simplon.finalproject2020.model.enums.NatureTypeDemande;
import co.simplon.finalproject2020.model.enums.OrigineDemande;
import co.simplon.finalproject2020.model.AttachedDocument;

@Entity
public class Demande {
	
	@Id
	@Column(name = "ID_DEMANDE")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	// généré par le service lors de l'instanciation. Les deux premiers chiffres dépendent de la nature (01 à 05), les deux seconds représentent l'année en cours et les 4 suivants s'incrémentent.
	@Column(name = "NNUMERO", nullable = false, length = 8)
	private String numero;
	
	
	// enums :
	@Column(name = "CNATURE_DEMANDE")										
	private NatureTypeDemande natureDemande;
	
	@Column(name = "LORIGINE")
	private OrigineDemande origineDemande;
	
	@Column(name = "LCOMMENT", nullable = true/*, columnDefinition = "CLOB"*/)
	private String commentaire;
	
	// dates :
	@Column(name = "DCREATION", nullable = false)		/* the DTO should allow null here. If we're instanciating, we initialize DCREATION as current date */
	// @Temporal(TemporalType.DATE) apparently not ok with LocalDate (java.time) and rather used with Date (java.util)
	private LocalDate dateCreation;
	
//	@Column(name = "DATTRIBUTION", nullable = false)
//	@Temporal(TemporalType.DATE)
	private LocalDate dateAttribution;
	
//	@Column(name = "DCLOTURE", nullable = false)
//	@Temporal(TemporalType.DATE)
	private LocalDate dateCloture;

	/* Will be calculated at creation, its value will depend of the demand's nature */
//	@Column(name = "DECHEANCE", nullable = true)
//	@Temporal(TemporalType.DATE)
	private LocalDate dateEcheance;
	
	
	// relationships :
	@OneToMany
	@JoinColumn(name = "ID_DEMANDE")
	private List<AttachedDocument> listeDocuments;
	
	@ManyToOne
	@JoinColumn(name = "ID_TYPE", referencedColumnName = "ID_TYPE_DEMANDE")
	private TypeDemande type;
	
	@ManyToOne
	@JoinColumn(name = "ID_AGENT", referencedColumnName = "ID_AGENT")
	private Agent agent;
	
	@ManyToOne
	@JoinColumn(name = "ID_UTILISATEUR", referencedColumnName = "ID")
	private Utilisateur responsable;
	
	
	// CONSTRUCTORS
	public Demande() {
	}
	public Demande(String numero, NatureTypeDemande natureDemande, OrigineDemande origineDemande,
			String commentaire, LocalDate dateCreation, LocalDate dateAttribution, LocalDate dateCloture, LocalDate dateEcheance,
			List<AttachedDocument> listeDocuments, TypeDemande type, Agent agent, Utilisateur responsable) {
		this.numero = numero;
		this.natureDemande = natureDemande;
		this.origineDemande = origineDemande;
		this.commentaire = commentaire;
		this.dateCreation = dateCreation;
		this.dateAttribution = dateAttribution;
		this.dateCloture = dateCloture;
		this.dateEcheance = dateEcheance;
		this.listeDocuments = listeDocuments;
		this.type = type;
		this.agent = agent;
		this.responsable = responsable;
	}
	
	// IMPORTANT OVERRRIDES
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
		result = prime * result + ((natureDemande == null) ? 0 : natureDemande.hashCode());
		result = prime * result + ((numero == null) ? 0 : numero.hashCode());
		result = prime * result + ((origineDemande == null) ? 0 : origineDemande.hashCode());
		result = prime * result + ((responsable == null) ? 0 : responsable.hashCode());
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
		if (natureDemande != other.natureDemande)
			return false;
		if (numero == null) {
			if (other.numero != null)
				return false;
		} else if (!numero.equals(other.numero))
			return false;
		if (origineDemande != other.origineDemande)
			return false;
		if (responsable == null) {
			if (other.responsable != null)
				return false;
		} else if (!responsable.equals(other.responsable))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Demande [id=" + id + ", numero=" + numero + ", natureDemande=" + natureDemande + ", origineDemande="
				+ origineDemande + ", commentaire=" + commentaire + ", dateCreation=" + dateCreation
				+ ", dateAttribution=" + dateAttribution + ", dateCloture=" + dateCloture + ", dateEcheance="
				+ dateEcheance + ", listeDocuments=" + listeDocuments + ", type=" + type + ", agent=" + agent
				+ ", responsable=" + responsable + "]";
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

	public NatureTypeDemande getNatureDemande() {
		return natureDemande;
	}
	public void setNatureDemande(NatureTypeDemande natureDemande) {
		this.natureDemande = natureDemande;
	}

	public OrigineDemande getOrigineDemande() {
		return origineDemande;
	}
	public void setOrigineDemande(OrigineDemande origineDemande) {
		this.origineDemande = origineDemande;
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
