package co.simplon.finalproject2020.service;

import co.simplon.finalproject2020.model.Agent;

public interface AgentService {
	
	public Agent findById(int id) throws Exception;
	
	public Agent findByIDRH(String IDRH) throws Exception;
	
	// a priori ne devrait pas �tre utilis� - les agents devraient �tre pr�sent en base.
	public Agent saveAgent(Agent agent) throws Exception;
	
	public Agent updateAgent(Agent agent) throws Exception;
	
	// public Set<Agent>

}
