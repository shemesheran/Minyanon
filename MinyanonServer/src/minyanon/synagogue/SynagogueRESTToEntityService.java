package minyanon.synagogue;

import java.util.LinkedList;
import java.util.List;

import javax.transaction.Transactional;

import minyanon.RESTToEntityService;
import minyanon.address.Address;
import minyanon.address.AddressUtils;

public class SynagogueRESTToEntityService extends RESTToEntityService<SynagogueREST, Synagogue>{

	private final SynagogueDAO synagogueDao;
	
	public SynagogueRESTToEntityService(SynagogueDAO synagogueDao) {
		this.synagogueDao = synagogueDao;
	}

	@Transactional
	public List<SynagogueREST> getAllSynagogues(List<String> fromCitiesNames) {
		List<SynagogueREST> synagogueRESTList = new LinkedList<SynagogueREST>();
		for (Synagogue synagogue : synagogueDao.getAllSynagogues(fromCitiesNames)){
			synagogueRESTList.add(new SynagogueREST(synagogue));
		}
		return synagogueRESTList;
	}
	
	@Transactional
	public void addNewSynagogue(String synagogueName, String addressStr){
		Address address = AddressUtils.getAddressFromGoogle(addressStr);
		synagogueDao.registerNew(new Synagogue(synagogueName, address));
	}
	
	@Transactional
	public void deleteSynagogue(String synagogueName, String cityName){
		synagogueDao.delete(new Synagogue(synagogueName, new Address(cityName)));
	}

}
