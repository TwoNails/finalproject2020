package co.simplon.finalproject2020.controller;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.finalproject2020.model.Agent;
import co.simplon.finalproject2020.model.Grade;
import co.simplon.finalproject2020.model.criteria.AgentCriteria;
import co.simplon.finalproject2020.service.AgentService;
import co.simplon.finalproject2020.service.GradeService;
import co.simplon.finalproject2020.service.impl.AgentServiceImpl;

@RestController
@RequestMapping("/agent")
@CrossOrigin(origins = "http://localhost:4200")
public class AgentController {
	
	@Autowired 
	AgentService agentService;
	
	@Autowired 
	GradeService gradeService;
	
	@GetMapping("/{idrh}")
	public ResponseEntity<Agent> getAgent(@PathVariable String idrh) {
		try {
			return new ResponseEntity<Agent>(agentService.findByIDRH(idrh), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Agent>(HttpStatus.NOT_FOUND);
		}
			// eventually replace that endpoint with last 2 months demands																						
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<Agent>> getAgents() {
		return new ResponseEntity<List<Agent>>(agentService.findAll(), HttpStatus.OK);
	}
	
	@PostMapping("/all")
	public ResponseEntity<List<Agent>> getAgentsWithCrits(@RequestBody AgentCriteria criteres){
		System.out.println(criteres);
		return new ResponseEntity<List<Agent>>(agentService.findByCriteria(criteres), HttpStatus.OK);
	}
	
	@PostMapping("/update")
	public ResponseEntity<Agent> updateAgent(@RequestBody Agent agent) {
		try {
			return new ResponseEntity<Agent>(agentService.updateAgent(agent), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Agent>(HttpStatus.NOT_FOUND);
		}
	}
	
	// Listes d'options à proposer en menu déroulant
	
	@GetMapping("/grades")
	public ResponseEntity<List<String>> getGrades() {
		return new ResponseEntity<List<String>>(gradeService.findAllCodes(), HttpStatus.OK);
	}
	
	@GetMapping("/grade/{code}")
	public ResponseEntity<Grade> getGrade(@PathVariable String code) {
		return new ResponseEntity<Grade>(gradeService.findByCode(code), HttpStatus.OK);
	}
	
	@GetMapping(path = "/gradelibelle/{code}", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> getGradeLibelle(@PathVariable String code) {
		return new ResponseEntity<String>(gradeService.findCodeLibelle(code), HttpStatus.OK);
	}
}
