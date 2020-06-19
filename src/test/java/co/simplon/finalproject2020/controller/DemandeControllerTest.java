package co.simplon.finalproject2020.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;

import co.simplon.finalproject2020.model.Demande;
import co.simplon.finalproject2020.model.Nature;
import co.simplon.finalproject2020.model.dto.DemandeDTO;
import co.simplon.finalproject2020.service.DemandeService;


@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class DemandeControllerTest {
	
	// MOCK							// @WithMockUser(authorities = "Admin")
	
	@MockBean
	private DemandeService demandeService;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	ObjectMapper mapper;
	
	Demande testDemande;
	
	// TESTS

	@BeforeEach
	void setUp() throws Exception {
		testDemande = new Demande("00000000", new Nature("testNature", "00"), "testObjet", null, null, null, null, null, null, null);
		
	}

	@AfterEach
	void tearDown() throws Exception {
		
	}

	@Test
	void testAddDemande() throws Exception {
		DemandeDTO testDTO = new DemandeDTO("PSYX555", "AGELEGAL", "", "MAIL", "N/A");
		
		when(demandeService.dtoToDemande(testDTO)).thenReturn(testDemande);
		when(demandeService.saveDemande(testDemande)).thenReturn(testDemande);
		
		ResultActions reponse = this.mockMvc.perform(post("/demande/save")
									.contentType(MediaType.APPLICATION_JSON)
									.content(mapper.writeValueAsString(testDTO)));
		reponse.andExpect(status().isCreated());
	}

	@Test
	void testGetDemandes() throws Exception {
		List<Demande> testList = new ArrayList<Demande>();
		testList.add(testDemande);
		testList.add(testDemande);
		
		when(demandeService.findAll()).thenReturn(testList);
		
		ResultActions reponse = this.mockMvc.perform(get("/demande/all"));
		
		reponse.andExpect(status().isOk());
		reponse.andExpect(jsonPath("$", hasSize(2)));
	}

	@Test
	void testGetDemandesWithCrits() throws Exception {
		fail("Not yet implemented");
	}

	@Test
	void testGetDemande() throws Exception {
		when(demandeService.findByNumero("00000000")).thenReturn(testDemande);
		
		ResultActions reponse = this.mockMvc.perform(get("/demande/00000000"));
		reponse.andExpect(status().isOk());
		reponse.andExpect(jsonPath("$.numero", containsString("00000000")));
	}

	@Test
	void testUpdateResponsable() {
		fail("Not yet implemented");
	}

	@Test
	void testUpdateObjet() {
		fail("Not yet implemented");
	}

	@Test
	void testDelete() {
		fail("Not yet implemented");
	}

	@Test
	void testGetDocument() {
		fail("Not yet implemented");
	}

	@Test
	void testAddDocuments() {
		fail("Not yet implemented");
	}

	@Test
	void testGetExcelFromSearchResults() {
		fail("Not yet implemented");
	}

	@Test
	void testGetManualOrigines() {
		fail("Not yet implemented");
	}

	@Test
	void testGetTypesAndNatures() {
		fail("Not yet implemented");
	}

	@Test
	void testGetStatuts() {
		fail("Not yet implemented");
	}

	@Test
	void testGetBranches() {
		fail("Not yet implemented");
	}

	@Test
	void testGetDocuments() {
		fail("Not yet implemented");
	}

}
