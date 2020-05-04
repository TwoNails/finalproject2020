package co.simplon.finalproject2020.service;

import java.util.List;

import co.simplon.finalproject2020.model.Agent;
import co.simplon.finalproject2020.model.criteria.AgentCriteria;

public interface AgentService {
	
	public List<Agent> findAll();
	
	public List<Agent> findByCriteria(AgentCriteria criteres);
	
	public Agent findById(int id) throws Exception;
	
	public Agent findByIDRH(String IDRH) throws Exception;
	
	// a priori ne devrait pas être utilisé - les agents devraient être présent en base.
	public Agent saveAgent(Agent agent) throws Exception;
	
	public Agent updateAgent(Agent agent) throws Exception;
	
	// public Set<Agent>

}
