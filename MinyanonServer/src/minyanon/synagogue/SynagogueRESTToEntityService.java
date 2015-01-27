package minyanon.synagogue;

import java.util.LinkedList;
import java.util.List;

import javax.transaction.Transactional;

import minyanon.RESTToEntityService;
import minyanon.city.City;

public class SynagogueRESTToEntityService extends RESTToEntityService<SynagogueREST, Synagogue>{

	private final SynagogueDAO synagogueDao;
	
	public SynagogueRESTToEntityService(SynagogueDAO synagogueDao) {
		this.synagogueDao = synagogueDao;
	}

	@Transactional
	public List<SynagogueREST> getAllSynagogues(List<String> fromCitiesNames) {
		List<SynagogueREST> synagogueRESTList = new LinkedList<SynagogueREST>();
		for (Synagogue synagogue : synagogueDao.getAllSynagogues(fromCitiesNames)){
			synagogueRESTList.add(new SynagogueREST(synagogue.getName(), synagogue.getCity().getName()));
		}
		return synagogueRESTList;
	}
	
	@Transactional
	public void addNewSynagogue(String synagogueName, String cityName){
		synagogueDao.registerNew(new Synagogue(synagogueName, new City(cityName)));
	}
	
	@Transactional
	public void deleteSynagogue(String synagogueName, String cityName){
		synagogueDao.delete(new Synagogue(synagogueName, new City(cityName)));
	}

}
