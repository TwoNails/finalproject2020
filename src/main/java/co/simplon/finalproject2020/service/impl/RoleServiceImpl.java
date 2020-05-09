package co.simplon.finalproject2020.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.simplon.finalproject2020.model.ProfilUtilisateur;
import co.simplon.finalproject2020.repository.RoleDAO;
import co.simplon.finalproject2020.service.RoleService;


@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleDAO roleDAO;
	
	@Override
	public List<String> findAll() {
		List<ProfilUtilisateur> listRoles = roleDAO.findAll();
		List<String> listRolesAsString = new ArrayList<String>();
		for (ProfilUtilisateur profil : listRoles) {
			listRolesAsString.add(profil.getLibelle());
		}
		return listRolesAsString;
	}

}
