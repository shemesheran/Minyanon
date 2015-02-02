package minyanon.city;

import java.util.List;

import minyanon.GenericDAO;

import org.hibernate.SessionFactory;

class CityDAO extends GenericDAO<City> {

	public CityDAO(SessionFactory sessionFactory) {
		super(City.class, sessionFactory);
	}

	List<City> getAllCities() {
		return sessionFactory.getCurrentSession().createCriteria(City.class).list();
	}

	@Override
	protected City getEntityWithAttachedDependencies(City city) {
		return city;
	}
	
	@Override
	public void delete(City entity) {
	}



}
