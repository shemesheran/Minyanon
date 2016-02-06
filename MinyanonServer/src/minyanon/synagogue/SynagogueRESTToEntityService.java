package minyanon.synagogue;

import java.util.LinkedList;
import java.util.List;

import javax.transaction.Transactional;

import minyanon.EntityNotFoundException;
import minyanon.RESTToEntityService;
import minyanon.address.Address;
import minyanon.address.AddressUtils;
import minyanon.address.BadAddressException;

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
	public void addNewSynagogue(String synagogueName, String addressStr) throws BadAddressException, EntityNotFoundException{		
		Address addressFromGoogle = AddressUtils.getAddressFromGoogle(addressStr);
		if(addressFromGoogle.getCity() == null){
			throw new BadAddressException("Missing city");
		}
		else if(addressFromGoogle.getStreetName() == null){
			throw new BadAddressException("Missing street name");
		}
		
		if(synagogueDao.getCityDao().getByNaturalIDs(addressFromGoogle.getCity())!=null){
			synagogueDao.getCityDao().registerNew(addressFromGoogle.getCity());
		}
		synagogueDao.registerNew(new Synagogue(synagogueName, addressFromGoogle));
	}
	
	@Transactional
	public void deleteSynagogue(String synagogueName, String cityName) throws EntityNotFoundException{
		synagogueDao.delete(new Synagogue(synagogueName, new Address(cityName)));
	}

	@Transactional
	public SynagogueREST getSynagogue(String synagogueName, String cityName) {
		Synagogue synagogueFromDB = synagogueDao.getByNaturalIDs(new Synagogue(synagogueName, new Address(cityName)));
		if(synagogueFromDB != null){
			return new SynagogueREST(synagogueFromDB);
		}
		else{
			return null;
		}
	}

	@Transactional
	public List<SynagogueInAreaREST> getSynagoguesInArea(double latitude, double longtitude, int radius) {
		List<SynagogueInAreaREST> synagogueRestList = new LinkedList<SynagogueInAreaREST>();
		for(Synagogue synagogue : synagogueDao.getSynagoguesInArea(latitude, longtitude, radius)){
			synagogueRestList.add(new SynagogueInAreaREST(synagogue, 1));
		}
		return synagogueRestList;
	}
	
	

	
}
