package co.simplon.finalproject2020.model;

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

@Entity
public class Demande {
	
	@Id
	@Column(name = "ID_DEMANDE")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	// généré par le service lors de l'instanciation. Les deux premiers chiffres dépendent de la nature (01 à 05), les deux seconds représentent l'année en cours et les 4 suivants s'incrémentent.
	@Column(name = "NNUMERO", nullable = false, length = 10)
	private String numero;
	
	
	// enums :
	@Column(name = "CNATURE_DEMANDE")										
	private NatureTypeDemande natureDemande;
	
	@Column(name = "LORIGINE")
	private OrigineDemande origineDemande;
	
	@Column(name = "LCOMMENT", nullable = true, columnDefinition = "CLOB")
	private String commentaire;
	
	// dates :
	@Column(name = "DCREATION", nullable = false)		/* the DTO should allow null here. If we're instanciating, we initialize DCREATION as current date */
	@Temporal(TemporalType.DATE)
	private Date dateCreation;
	
	@Column(name = "DATTRIBUTION", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dateAttribution;
	
	@Column(name = "DCLOTURE", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dateCloture;

	/* Will be calculated at creation, its value will depend of the demand's nature */
	@Column(name = "DECHEANCE", nullable = true)
	@Temporal(TemporalType.DATE)
	private Date dateEcheance;
	
	
	// relationships :
	@OneToMany(mappedBy = "fileReference")
	protected List<AttachedDocument> listeDocuments;
	
	@ManyToOne
	@JoinColumn(name = "ID_TYPE", referencedColumnName = "ID")
	private TypeDemande type;
	
	@ManyToOne
	@JoinColumn(name = "ID_AGENT", referencedColumnName = "ID")
	private Agent agent;
	
	@ManyToOne
	@JoinColumn(name = "ID_UTILISATEUR", referencedColumnName = "ID")
	private Utilisateur correspondant;
	

}
