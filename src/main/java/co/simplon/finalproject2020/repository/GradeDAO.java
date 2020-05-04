package co.simplon.finalproject2020.repository;

import co.simplon.finalproject2020.model.Grade;

public interface GradeDAO extends DAO<Grade> {

	Grade findByCode(String code);

}
