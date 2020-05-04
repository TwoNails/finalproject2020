package co.simplon.finalproject2020.repository;

import java.util.List;

import co.simplon.finalproject2020.model.Agent;
import co.simplon.finalproject2020.model.criteria.AgentCriteria;

public interface CustomCriteriaAgentRepository extends CustomCriteriaRepository<Agent> {

	public List<Agent> findAllWithCriteria(AgentCriteria criteres);
}
