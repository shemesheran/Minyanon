package minyanon.synagogue;

import java.util.List;

import javax.persistence.EntityExistsException;

import minyanon.GenericDAO;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Restrictions;

import minyanon.address.Address;
import minyanon.address.AddressUtils;
import minyanon.address.city.City;

class SynagogueDAO extends GenericDAO<Synagogue> {

	public SynagogueDAO(SessionFactory sessionFactory) {
		super(Synagogue.class, sessionFactory);
	}
	
	void deleteCity(Synagogue synagogue) {
		sessionFactory.getCurrentSession().delete(getEntityWithAttachedDependencies(synagogue));
	}

	List<Synagogue> getAllSynagogues(int radiusInMeters) {
		Criteria synagoguesCriteria = sessionFactory.getCurrentSession().createCriteria(Synagogue.class, "synagogue");
		synagoguesCriteria.createAlias("synagogue.city", "city");

		//Choosing from the requested cities
		Disjunction  cityDisjunction = Restrictions.disjunction();
		for(String city : fromCitiesNames){
			cityDisjunction.add(Restrictions.eq("city.name", city));
		}
		synagoguesCriteria.add(cityDisjunction);
		
		return synagoguesCriteria.list();
	}
	
	@SuppressWarnings("unchecked")
	List<Synagogue> getAllSynagogues(String fromCity) {
		City city = getCityByName(fromCity);
		return (List<Synagogue>)sessionFactory.getCurrentSession().createCriteria(Synagogue.class, "synagogue")
				.add(Restrictions.eq("synagogue.address.city.id", city.getId())).list();
	}


	@Override
	protected Synagogue getEntityWithAttachedDependencies(Synagogue synagogue) {
		City cityFromDB = getCityByName(synagogue.getAddress().getCity().getName());
		synagogue.getAddress().setCity(cityFromDB);
		return synagogue;
	}


	@Override
	public void delete(Synagogue entity) {
		// TODO Auto-generated method stub
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
		Address addressFromGoogle = AddressUtils.getAddressFromGoogle(givenCityName); 
		if(addressFromGoogle != null){
			//TODO What if I was given a city that I still dont have in the DB?
			//Maybe throw exception if real city from google is not known yet, or null.
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
