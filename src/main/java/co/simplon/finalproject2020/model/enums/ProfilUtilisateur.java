package co.simplon.finalproject2020.model.enums;

import org.springframework.security.core.GrantedAuthority;

public enum ProfilUtilisateur implements GrantedAuthority {
	SAISIE, GESTIONNAIRE, ADMIN;

	public String getAuthority() {
		// TODO Auto-generated method stub
		return this.name();
	}
}
