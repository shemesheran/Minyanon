package minyanon.prayer;

import java.util.Date;
import java.util.List;

import minyanon.GenericDAO;
import minyanon.address.Address;
import minyanon.synagogue.Synagogue;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Restrictions;

public abstract class PrayerDAO<E extends Prayer> extends GenericDAO<E> {
	
	public PrayerDAO(Class<E> clazz, SessionFactory sessionFactory) {
		super(clazz, sessionFactory);
	}
		
	public void newPrayer(E prayer){
		sessionFactory.getCurrentSession().save(getEntityWithAttachedDependencies(prayer));
	}
	
	//Notice that an empty list means all of the possibilities from this category.
	@SuppressWarnings("unchecked")
	public List<E> getAllPrayers(List<String> cities,List<Date[]> datesRanges, List<Prayer.PrayerStyle> prayerStyles) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(type,"prayer");				
		criteria.createAlias("prayer.city", "city");
		
		//Choosing from the requested cities
		Disjunction  cityDisjunction = Restrictions.disjunction();
		for(String city : cities){
			cityDisjunction.add(Restrictions.eq("city.name", city));
		}
		criteria.add(cityDisjunction);

		//Choosing from the requested dates ranges
		Disjunction  datesRangesDisjunction = Restrictions.disjunction();
		for(Date[] dateRangeTuple : datesRanges){
			datesRangesDisjunction.add(
					Restrictions.and(
							Restrictions.ge("date", dateRangeTuple[0]),
							Restrictions.le("date", dateRangeTuple[1])));
		}
		criteria.add(datesRangesDisjunction);
		
		//Choosing from the requested prayer styles
		Disjunction  prayerStyleDisjunction = Restrictions.disjunction();
		for(Prayer.PrayerStyle prayerStyle : prayerStyles){
			prayerStyleDisjunction.add(Restrictions.eq("PrayerStyle", prayerStyle));
		}
		criteria.add(prayerStyleDisjunction);
		
		return criteria.list();
	}
		
	@Override
	protected E getEntityWithAttachedDependencies(E prayer){
		Address cityFromDB = (Address) sessionFactory.getCurrentSession().byNaturalId(Address.class)
				.using("name", prayer.getCity().getName()).load();
		Synagogue synagogueCityFromDB = (Synagogue) sessionFactory.getCurrentSession().byNaturalId(Synagogue.class)
				.using("name", prayer.getSynagogue().getName())
				.using("city", cityFromDB).load();
		prayer.setCity(cityFromDB);
		prayer.setSynagogue(synagogueCityFromDB);
		return prayer;
	}
	
	@Override
	public void delete(E prayer) {
		prayer = getEntityWithAttachedDependencies(prayer);
//		sessionFactory.getCurrentSession().bycreateQuery("Delete From :prayerType where name = :name")
//		.setParameter("name", prayer.getName()).executeUpdate();	
	}
	
}
