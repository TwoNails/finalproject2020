package co.simplon.finalproject2020.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import co.simplon.finalproject2020.model.__test__Student;

public interface StudentRepository extends JpaRepository<__test__Student, Integer> {
	
	List<__test__Student> findAllByOverallaverageGreaterThanEqual(float f); 

}
