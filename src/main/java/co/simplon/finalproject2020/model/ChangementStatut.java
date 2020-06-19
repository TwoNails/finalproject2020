package co.simplon.finalproject2020.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "CHANGEMENTSTATUT")
public class ChangementStatut implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2608178944804792888L;
	
	@Id
	@Column(name = "ID_CHANGEMENTSTATUT")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
		
	private LocalDate dateChangement;
	
	// relationships :
	
	@ManyToOne
	@JoinColumn(name = "ID_STATUT", referencedColumnName = "ID_STATUT")
	private Statut nouveauStatut;
	
	@ManyToOne
	@JoinColumn(name = "NUM_DEMANDE", referencedColumnName = "NNUMERO")
	private Demande demande;
	
	@ManyToOne
	@JoinColumn(name = "IDRH_GESTIONNAIRE", referencedColumnName = "CMATRICULE")
	private Utilisateur utilisateur;

	
	// CONSTRUCTORS
	
	public ChangementStatut() {
	}

	public ChangementStatut(LocalDate dateChangmentStatut, Statut nouveauStatut, Demande demande,
			Utilisateur utilisateur) {
		this.dateChangement = dateChangmentStatut;
		this.nouveauStatut = nouveauStatut;
		this.demande = demande;
		this.utilisateur = utilisateur;
	}
	
	// IMPORTANT OVERRIDES

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dateChangement == null) ? 0 : dateChangement.hashCode());
		result = prime * result + ((demande == null) ? 0 : demande.hashCode());
		result = prime * result + id;
		result = prime * result + ((nouveauStatut == null) ? 0 : nouveauStatut.hashCode());
		result = prime * result + ((utilisateur == null) ? 0 : utilisateur.hashCode());
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
		ChangementStatut other = (ChangementStatut) obj;
		if (dateChangement == null) {
			if (other.dateChangement != null)
				return false;
		} else if (!dateChangement.equals(other.dateChangement))
			return false;
		if (demande == null) {
			if (other.demande != null)
				return false;
		} else if (!demande.equals(other.demande))
			return false;
		if (id != other.id)
			return false;
		if (nouveauStatut == null) {
			if (other.nouveauStatut != null)
				return false;
		} else if (!nouveauStatut.equals(other.nouveauStatut))
			return false;
		if (utilisateur == null) {
			if (other.utilisateur != null)
				return false;
		} else if (!utilisateur.equals(other.utilisateur))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ChangementStatut [id=" + id + ", dateChangement=" + dateChangement + ", nouveauStatutLib="
				+ nouveauStatut.getLibelle() + ", demandeNum=" + demande.getNumero() + ", utilisateurIDRH=" + utilisateur.getIdentifiantRH() + "]";
	}

	// GETTERS / SETTERS 
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getDateChangmentStatut() {
		return dateChangement;
	}
	public void setDateChangmentStatut(LocalDate dateChangmentStatut) {
		this.dateChangement = dateChangmentStatut;
	}

	public Statut getNouveauStatut() {
		return nouveauStatut;
	}
	public void setNouveauStatut(Statut nouveauStatut) {
		this.nouveauStatut = nouveauStatut;
	}

	public Demande getDemande() {
		return demande;
	}
	public void setDemande(Demande demande) {
		this.demande = demande;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}
	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}
	
	
	
	


}
