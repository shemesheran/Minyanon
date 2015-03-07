package minyanon.synagogue;

import java.util.LinkedList;
import java.util.List;

import javax.transaction.Transactional;

import minyanon.RESTToEntityService;
import minyanon.address.Address;
import minyanon.address.AddressUtils;

public class SynagogueRESTToEntityService extends RESTToEntityService<SynagogueREST, Synagogue>{

	private final SynagogueDAO synagogueDao;
	private Synagogue synagogueEntity;
		
	public SynagogueRESTToEntityService(SynagogueDAO synagogueDao) {
		this.synagogueDao = synagogueDao;
	}

	@Transactional
	public List<SynagogueREST> getAllSynagogues(String fromCity) {
		List<Synagogue> allSynagogues = synagogueDao.getAllSynagogues(fromCity);
		List<SynagogueREST> allSynagoguesRest = new LinkedList<SynagogueREST>();
		for (Synagogue synagogue : allSynagogues) {
			allSynagoguesRest.add(new SynagogueREST(synagogue));
		}
		return allSynagoguesRest;
	}
	
	@Transactional
	public void addNewSynagogue(String synagogueName, String addressStr){
		
		//If no address was found in google, throw an exception
		Address address = AddressUtils.getAddressFromGoogle(addressStr);
	
		synagogueDao.getCityDao().registerNew(address.getCity());
		synagogueDao.registerNew(new Synagogue(synagogueName, address));
	}
	
	@Transactional
	public void deleteSynagogue(String synagogueName, String cityName){
		synagogueDao.delete(new Synagogue(synagogueName, new Address(cityName)));
	}

	public SynagogueREST getSynagogue(String synagogueName, String cityName) {
		Synagogue synagogueFromDB = synagogueDao.getOne(new Synagogue(synagogueName, new Address(cityName)));
		return new SynagogueREST(synagogueFromDB);
	}

	public List<SynagogueREST> getSynagoguesInArea(int location, int radius) {
		List<SynagogueREST> synagogueRestList = new LinkedList<SynagogueREST>();
		for(Synagogue synagogue : synagogueDao.getSynagoguesInArea(location, radius)){
			synagogueRestList.add(new SynagogueREST(synagogue));
		}
		return synagogueRestList;
	}
	
	

	
}
