package co.simplon.finalproject2020.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DAO<T> extends JpaRepository<T, Integer> {
	
}
