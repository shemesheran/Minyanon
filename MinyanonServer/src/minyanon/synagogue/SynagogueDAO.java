package minyanon.synagogue;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

import javax.persistence.EntityExistsException;

import minyanon.GenericDAO;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

import minyanon.address.Address;
import minyanon.address.AddressUtils;
import minyanon.address.BadAddressException;
import minyanon.address.city.City;

class SynagogueDAO extends GenericDAO<Synagogue>{

	private CityDAO cityDao;
	
	public SynagogueDAO(SessionFactory sessionFactory) {
		super(Synagogue.class, sessionFactory);
		cityDao = new CityDAO(City.class, sessionFactory);
	}
	
	void deleteCity(Synagogue synagogue) {
		sessionFactory.getCurrentSession().delete(getEntityWithAttachedDependencies(synagogue));
	}

	@SuppressWarnings("unchecked")
	List<Synagogue> getSynagoguesInArea(double latitude, double longtitude, int radius) {
		List<Entry<Integer, Synagogue>> distanceToSynagogue = new LinkedList<Entry<Integer, Synagogue>>(); 
		List list = Arrays.copyOfRange(sessionFactory.getCurrentSession()
				.createSQLQuery("call geodist(:latitude ,:longtitude, :dist)")
				.setParameter("latitude", latitude)
				.setParameter("longtitude", longtitude)
				.setParameter("dist", radius)
				.list().toArray(), 0,4);
		return null;
	}
	
	@SuppressWarnings("unchecked")
	List<Synagogue> getAllSynagogues(String fromCity) {
		City city = cityDao.getCityByName(fromCity);
		return (List<Synagogue>)sessionFactory.getCurrentSession().createCriteria(Synagogue.class, "synagogue")
				.add(Restrictions.eq("synagogue.address.city.id", city.getId())).list();
	}


	@Override
	protected Synagogue getEntityWithAttachedDependencies(Synagogue synagogue) {
		City cityFromDB = cityDao.getCityByName(synagogue.getAddress().getCity().getName());
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
		
		private City getCityByName(String givenCityName){
			//Trying to fetch from the DB by given city name
			City city = (City) sessionFactory.getCurrentSession().bySimpleNaturalId(City.class).getReference(givenCityName);
			if(city != null){
				return city;
			}
			//Checking at the DB if given city name is an alias, and if so, get the city from the DB
			city = getCityByAlias(givenCityName);
			if(city != null) {
				return city;
			}
			
			//Checking with google if this city name is an alias. if so, save it as alias
			Address addressFromGoogle;
			addressFromGoogle = AddressUtils.getAddressFromGoogle(givenCityName);
			if(addressFromGoogle != null && addressFromGoogle.getCity() != null){
				addCityAlias(addressFromGoogle.getCity(), givenCityName);
				return addressFromGoogle.getCity();
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
			city.addCityAlias(cityAlias);
			sessionFactory.getCurrentSession().update(city);
		}
	}
	
	public CityDAO getCityDao() {
		return cityDao;
	}

		
}
