package minyanon.city;

import java.util.LinkedList;
import java.util.List;

import javax.transaction.Transactional;

import minyanon.RESTToEntityService;


class CityRESTToEntityService extends RESTToEntityService<CityREST, City>{
	
	private final CityDAO cityDao;
	
	public CityRESTToEntityService(CityDAO cityDao) {
		this.cityDao = cityDao;
	}

	@Transactional
	public List<CityREST> getAll(CityREST cityREST) {
		List<CityREST> cityRESTList = new LinkedList<CityREST>();
		for (City city : cityDao.getAllCities()){
			cityRESTList.add(new CityREST(city.getName()));
		}
		return cityRESTList;
	}
	
	@Transactional
	public void addNewCity(String cityName){
		cityDao.registerNew(new City(cityName));
	}
	
	@Transactional
	public void deleteCity(String cityName){
		cityDao.delete(new City(cityName));
	}
	
}
