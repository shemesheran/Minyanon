package minyanon.synagogue;

import java.util.List;

import minyanon.GenericDAO;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Restrictions;
import minyanon.city.City;

class SynagogueDAO extends GenericDAO<Synagogue> {

	public SynagogueDAO(SessionFactory sessionFactory) {
		super(Synagogue.class, sessionFactory);
	}

	
	void deleteCity(Synagogue synagogue) {
		sessionFactory.getCurrentSession().delete(getEntityWithAttachedDependencies(synagogue));
	}

	List<Synagogue> getAllSynagogues(List<String> fromCitiesNames) {
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

	@Override
	protected Synagogue getEntityWithAttachedDependencies(Synagogue synagogue) {
		City cityFromDB = (City) sessionFactory.getCurrentSession().byNaturalId(City.class).using("name", synagogue.getCity().getName()).getReference();
		synagogue.setCity(cityFromDB);
		return synagogue;
	}


	@Override
	public void delete(Synagogue entity) {
		// TODO Auto-generated method stub
		
	}

}
