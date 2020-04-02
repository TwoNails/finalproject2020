package co.simplon.finalproject2020.repository;

import java.util.Optional;

import co.simplon.finalproject2020.model.Agent;

public interface AgentDAO extends DAO<Agent> {
	
	Optional<Agent> findByIdentifiantRH(String IDRH);
}
