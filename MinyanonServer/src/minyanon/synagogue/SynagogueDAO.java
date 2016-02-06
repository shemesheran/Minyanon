package minyanon.synagogue;

import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import minyanon.GenericDAO;
import minyanon.address.Address;
import minyanon.address.AddressUtils;
import minyanon.address.city.City;

class SynagogueDAO extends GenericDAO<Synagogue>{

	private CityDAO cityDao;
	@SuppressWarnings("unchecked")
	List<Synagogue> getAllSynagogues(String fromCity) {
		City city = cityDao.getCityByName(fromCity);
		return (List<Synagogue>)sessionFactory.getCurrentSession().createCriteria(Synagogue.class, "synagogue")
				.add(Restrictions.eq("synagogue.address.city.id", city.getId())).list();
	}
	
	public SynagogueDAO(SessionFactory sessionFactory) {
		super(Synagogue.class, sessionFactory);
		cityDao = new CityDAO(City.class, sessionFactory);
	}
	
	@Override
	public Synagogue getByNaturalIDs(Synagogue synagogue) {
		return (Synagogue) sessionFactory.getCurrentSession().byNaturalId(Synagogue.class).using("name", synagogue.getName())
			.using("city", synagogue.getAddress().getCity()).getReference();
	}

	@SuppressWarnings("unchecked")
	List<Synagogue> getSynagoguesInArea(double latitude, double longtitude, int radius) {
		List<Entry<Integer, Synagogue>> distanceToSynagogue = new LinkedList<Entry<Integer, Synagogue>>(); 
		List synagogueAndDistanceList = sessionFactory.getCurrentSession()
			.createSQLQuery("call geodist(:latitude ,:longtitude, :dist)")
			.setParameter("latitude", latitude)
			.setParameter("longtitude", longtitude)
			.setParameter("dist", radius)
			.list();
		for(Object obj : synagogueAndDistanceList){
			distanceToSynagogue.add(distToSynagogueEntryFromObject(obj));
		}
		return null;
	}
	

	
	private Entry<Integer, Synagogue> distToSynagogueEntryFromObject(Object obj) {
		City city = new City();
		return null;
	}

	@Override
	protected Synagogue getEntityWithAttachedDependencies(Synagogue synagogue) throws CityNotFoundException {
		City cityFromDB = cityDao.getCityByName(synagogue.getAddress().getCity().getName());
		if(cityFromDB == null)
			throw new CityNotFoundException();
		else
			synagogue.getAddress().setCity(cityFromDB);
			return synagogue;
	}

	public class CityDAO extends GenericDAO<City>{

		public CityDAO(Class<City> type, SessionFactory sessionFactory) {
			super(type, sessionFactory);
		}

		@Override
		protected City getEntityWithAttachedDependencies(City entity) {
			return entity;
		}
		
		@Override
		public City getByNaturalIDs(City city) {
			return getCityFromDB(city.getName());
		}
		
		private City getCityFromDB(String givenCityName){
			City city;
			//Trying to fetch from the DB by given city name
			if((city = (City) sessionFactory.getCurrentSession().bySimpleNaturalId(City.class).getReference(givenCityName)) != null){
				return city;
			}
			
			//Checking at the DB if given city name is an alias, and if so, get the city from the DB
			else if ((city = getCityByAlias(givenCityName)) != null){
				return city;
			}	
			return null;
		}
		
		private City getCityByName(String givenCityName){
			City city;
			Address addressFromGoogle;
			
			if ((city = getCityFromDB(givenCityName)) != null){
				return city;
			}
			
			//Checking with google if this city name is an alias. if so, save it as alias
			else if((addressFromGoogle = AddressUtils.getAddressFromGoogle(givenCityName)) != null 
					&& addressFromGoogle.getCity() != null){
				city = (City) sessionFactory.getCurrentSession().bySimpleNaturalId(City.class).getReference(addressFromGoogle.getCity().getName());
				if(city != null){
					addCityAlias(city, givenCityName);
				}
				return city;
			}
			else{
				return null;
			}
		}

		private City getCityByAlias(String name) {
			return (City) sessionFactory.getCurrentSession().createCriteria(City.class).createAlias("cityAliases", "cityAliases")
			.add(Restrictions.eq("cityAliases.elements", name)).uniqueResult();
		}
		
		private void addCityAlias(City city, String cityAlias){
			city = (City) sessionFactory.getCurrentSession().bySimpleNaturalId(City.class).getReference(city.getName());
			if(city != null){
				city.addCityAlias(cityAlias);
				sessionFactory.getCurrentSession().update(city);
			}
		}
	}
	
	public CityDAO getCityDao() {
		return cityDao;
	}

		
}
