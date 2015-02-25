package minyanon.address.city;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import minyanon.GenericDAO;

public class CityDAO extends GenericDAO<City>{

	public CityDAO(SessionFactory sessionFactory) {
		super(City.class, sessionFactory);
	}

	@Override
	protected City getEntityWithAttachedDependencies(City city) {
		return city;
	}
	
	public City getCityByName(String name){
		City city = (City) sessionFactory.getCurrentSession().bySimpleNaturalId(type).getReference(name);
		if(city != null){
			return city;
		}
		else return getCityByAlias(name);
	}

	private City getCityByAlias(String name) {
		return (City) sessionFactory.getCurrentSession().createCriteria(City.class).createAlias("cityAliases", "cityAliases")
		.add(Restrictions.eq("cityAliases.cityAliases", name)).uniqueResult();
	}

}
