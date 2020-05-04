package co.simplon.finalproject2020.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.simplon.finalproject2020.model.Adresse;
import co.simplon.finalproject2020.model.Agent;
import co.simplon.finalproject2020.model.Demande;
import co.simplon.finalproject2020.model.criteria.AgentCriteria;
import co.simplon.finalproject2020.model.criteria.DemandeCriteria;
import co.simplon.finalproject2020.repository.AgentDAO;
import co.simplon.finalproject2020.repository.CustomCriteriaAgentRepository;
import co.simplon.finalproject2020.repository.CustomCriteriaDemandeRepository;


@Service
public class AgentServiceImpl implements AgentService {

	@Autowired
	private AgentDAO agentDAO;
	
	@Autowired
	private AdresseDAO adresseDAO;
	
	@Autowired
	private CustomCriteriaAgentRepository ccRepository; 
	
	@Override
	public List<Agent> findAll() {
		return agentDAO.findAll();
	}

	@Override
	public List<Agent> findByCriteria(AgentCriteria criteres) {
		System.out.println("naissance obtenue via json : " + criteres.getAnneeNaissance());
		return ccRepository.findAllWithCriteria(criteres);
	}
	
	@Override
	public Agent findById(int id) throws Exception {
		 Optional<Agent> optAgent = agentDAO.findById(id);
			if(optAgent.isPresent()) {
				return optAgent.get();
			} else {
				throw new Exception("agent(id) can't be found in database");
			}
	 }

	@Override
	public Agent findByIDRH(String IDRH) throws Exception {
		Optional<Agent> optAgent = agentDAO.findByIdentifiantRH(IDRH);
		if(optAgent.isPresent()) {
			return optAgent.get();
		} else {
			throw new Exception("agent(idrh) can't be found in database");
		}
	}

	@Override
	public Agent saveAgent(Agent agent) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Agent updateAgent(Agent agent) throws Exception {
		Optional<Agent> optAgent = agentDAO.findById(agent.getId());
		if(optAgent.isPresent()) {
			adresseDAO.saveAndFlush(agent.getAdresse());
			agentDAO.saveAndFlush(agent);
			return agent;
		} else {
			throw new Exception("agent(id) can't be found and therefore be updated");
		}	
	}



}
