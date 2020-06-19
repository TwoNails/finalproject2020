package co.simplon.finalproject2020.service.impl;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import co.simplon.finalproject2020.repository.DemandeDAO;
import co.simplon.finalproject2020.repository.criteria.CustomCriteriaDemandeRepository;

class DemandeServiceImplTest {

	@MockBean
	private DemandeDAO demandeDAO;
	
	@MockBean
	private CustomCriteriaDemandeRepository demandeCustomRepo;
	
	
	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testFindAll() {
		fail("Not yet implemented");
	}

	@Test
	void testFindByCriteria() {
		fail("Not yet implemented");
	}

	@Test
	void testFindById() {
		fail("Not yet implemented");
	}

	@Test
	void testFindByNumero() {
		fail("Not yet implemented");
	}

	@Test
	void testSaveDemande() {
		fail("Not yet implemented");
	}

	@Test
	void testCloseDemande() {
		fail("Not yet implemented");
	}

	@Test
	void testDelete() {
		fail("Not yet implemented");
	}

	@Test
	void testDtoToDemande() {
		fail("Not yet implemented");
	}

	@Test
	void testGenerateNum() {
		fail("Not yet implemented");
	}

	@Test
	void testAddDocuments() {
		fail("Not yet implemented");
	}

	@Test
	void testRemoveAttachedDocuments() {
		fail("Not yet implemented");
	}

	@Test
	void testAssign() {
		fail("Not yet implemented");
	}

	@Test
	void testUpdateComment() {
		fail("Not yet implemented");
	}

}
