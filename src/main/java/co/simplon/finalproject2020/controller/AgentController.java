package co.simplon.finalproject2020.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.finalproject2020.model.Agent;
import co.simplon.finalproject2020.service.AgentServiceImpl;

@RestController
@RequestMapping("/agent")
@CrossOrigin(origins = "http://localhost:4200")
public class AgentController {
	
	@Autowired 
	AgentServiceImpl agentService;
	
	@GetMapping("/{idrh}")
	public ResponseEntity<Agent> getDemandes(@PathVariable String idrh) {
		try {
			return new ResponseEntity<Agent>(agentService.findByIDRH(idrh), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Agent>(HttpStatus.NOT_FOUND);
		}
			// eventually replace that endpoint with last 2 months demands																						
	}
}
