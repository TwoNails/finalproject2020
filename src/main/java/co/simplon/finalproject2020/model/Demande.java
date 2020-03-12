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

@Entity
public class Demande {
	
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "NNUMERO", nullable = false, length = 10)
	private String numero;
											/* the DTO should allow null here. If the Demande isn't found in base, we initialize DCREATION as current date */
	@Column(name = "DCREATION", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dateCreation;
								/* is an object in SHARP, stored as an ID. Values are Allocation, Communication, Estimation, Retraite, Requete */
	@Column(name = "CNATURE_DEMANDE")										
	private String natureDemande;
	
	@Column(name = "LCOMMENT", nullable = true, columnDefinition = "CLOB")
	private String commentaires;
	
	/*
	 * A 
	 * 
	 * 
	 * 
	 */
											/* Will be calculated at creation, its value will depend of the demand's nature */
	@Column(name = "DECHEANCE", nullable = true)
	@Temporal(TemporalType.DATE)
	private Date dateEcheance;
	
	@OneToMany(mappedBy = "fileReference")
	protected List<AttachedDocument> listeDocuments;
	
	@ManyToOne
	@JoinColumn(name = "ID_AGENT", referencedColumnName = "ID")
	private Agent agent;
	
	@ManyToOne
	@JoinColumn(name = "ID_UTILISATEUR", referencedColumnName = "ID")
	private Utilisateur correspondant;
}
