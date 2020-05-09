package co.simplon.finalproject2020.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonProperty;

import co.simplon.finalproject2020.model.ProfilUtilisateur;

@Entity
@Table(name = "UTILISATEUR")
public class Utilisateur implements UserDetails {
	
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "CMATRICULE", nullable = false, unique = true, length = 7)
	private String identifiantRH;
	
	@Column(name = "LNOM", nullable = false, length = 40)
	private String nom;
	
	@Column(name = "LPRENOM", nullable = false, length = 30)
	private String prenom;
	
	@Column(name = "BACTIF")
	private Boolean estActif;
	
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // permet de ne pas afficher le pw dans le json
	private String password;
	
	/*
	@ElementCollection
	@CollectionTable(
			name = "tableProfilsUtilisateur",
			joinColumns = @JoinColumn(name = "id_utilisateur")
	)
	@Column(name = "ProfilId") */
	
	@ManyToMany
	@JoinTable(name = "UTILISATEUR_ROLE",
			joinColumns = @JoinColumn(name = "ID"),
	        inverseJoinColumns = @JoinColumn(name = "ID_ROLE"))
	private List<ProfilUtilisateur> roles = new ArrayList<ProfilUtilisateur>();

	// @ManyToMany(cascade = { CascadeType.ALL })
	// @JoinTable(
	//name = "
	//)
	// private List<ProfilUtilisateur> 
	
	@ManyToOne
	@JoinColumn(name = "ID_EQUIPE", referencedColumnName = "ID_EQUIPE")
	private Equipe equipe;
	
	
	
	// CONSTRUCTORS
	
	public Utilisateur() {
	}
	
	public Utilisateur(String identifiantRH, String nom, String prenom, Boolean estActif, String password,
			List<ProfilUtilisateur> roles, Equipe equipe) {
		this.identifiantRH = identifiantRH;
		this.nom = nom;
		this.prenom = prenom;
		this.estActif = estActif;
		this.password = password;
		this.roles = roles;
		this.equipe = equipe;
	}
	
	
	// GETTERS / SETTERS
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getIdentifiantRH() {
		return identifiantRH;
	}
	public void setIdentifiantRH(String identifiantRH) {
		this.identifiantRH = identifiantRH;
	}

	public String getMatricule() {
		return identifiantRH;
	}
	public void setMatricule(String matricule) {
		this.identifiantRH = matricule;
	}

	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
	public Boolean getEstActif() {
		return estActif;
	}
	public void setEstActif(Boolean estActif) {
		this.estActif = estActif;
	}
	
	public List<ProfilUtilisateur> getRoles() {
		return roles;
	}
	public void setRoles(List<ProfilUtilisateur> roles) {
		this.roles = roles;
	}
	
	public Equipe getEquipe() {
		return equipe;
	}
	public void setEquipe(Equipe equipe) {
		this.equipe = equipe;
	}
	
	// public String getPassword() 
	public void setPassword(String password) {
		this.password = password;
	}
	
	/* IMPORTANT OVERRIDES */
	
	
	
	@Override
	public String toString() {
		return "Utilisateur [id=" + id + ", identifiantRH=" + identifiantRH + ", nom=" + nom + ", prenom=" + prenom
				+ ", estActif=" + estActif + ", password=" + password + ", roles=" + roles + ", equipe=" + equipe + "]";
	}
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((equipe == null) ? 0 : equipe.hashCode());
		result = prime * result + ((estActif == null) ? 0 : estActif.hashCode());
		result = prime * result + id;
		result = prime * result + ((identifiantRH == null) ? 0 : identifiantRH.hashCode());
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((prenom == null) ? 0 : prenom.hashCode());
		result = prime * result + ((roles == null) ? 0 : roles.hashCode());
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
		Utilisateur other = (Utilisateur) obj;
		if (equipe == null) {
			if (other.equipe != null)
				return false;
		} else if (!equipe.equals(other.equipe))
			return false;
		if (estActif == null) {
			if (other.estActif != null)
				return false;
		} else if (!estActif.equals(other.estActif))
			return false;
		if (id != other.id)
			return false;
		if (identifiantRH == null) {
			if (other.identifiantRH != null)
				return false;
		} else if (!identifiantRH.equals(other.identifiantRH))
			return false;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (prenom == null) {
			if (other.prenom != null)
				return false;
		} else if (!prenom.equals(other.prenom))
			return false;
		if (roles == null) {
			if (other.roles != null)
				return false;
		} else if (!roles.equals(other.roles))
			return false;
		return true;
	}

	// SECURITY METHODS
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> rolesAsAuthorities = new ArrayList<>();
		for (ProfilUtilisateur role : roles) {
			rolesAsAuthorities.add(new SimpleGrantedAuthority(role.toString()));
		}
		System.out.println("method GetAuthorities returning : " + rolesAsAuthorities);
		return rolesAsAuthorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.identifiantRH;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return this.estActif;
	}	
}
