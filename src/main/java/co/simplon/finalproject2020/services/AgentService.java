package co.simplon.finalproject2020.services;

import co.simplon.finalproject2020.model.Agent;

public interface AgentService {
	
	public Agent findById(int id) throws Exception;
	
	public Agent findByIDRH(String IDRH) throws Exception;
	
	// a priori ne devrait pas être utilisé - les agents devraient être présent en base.
	public Agent saveAgent(Agent agent) throws Exception;
	
	public Agent updateAgent(Agent agent) throws Exception;

}
