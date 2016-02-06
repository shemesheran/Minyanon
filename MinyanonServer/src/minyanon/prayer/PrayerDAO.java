package minyanon.prayer;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Restrictions;

import minyanon.GenericDAO;
import minyanon.synagogue.Synagogue;
import minyanon.synagogue.SynagogueNotFoundException;

public abstract class PrayerDAO<P extends Prayer> extends GenericDAO<P> {
	
	public PrayerDAO(Class<P> clazz, SessionFactory sessionFactory) {
		super(clazz, sessionFactory);
	}
	
	//Notice that an empty list means all of the possibilities from this category.
	@SuppressWarnings("unchecked")
	public List<P> getAllPrayers(List<String> cities,List<Date[]> datesRanges, List<Prayer.PrayerStyle> prayerStyles) {
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
	protected P getEntityWithAttachedDependencies(P prayer) throws SynagogueNotFoundException{
		Synagogue synagogueCityFromDB = (Synagogue) sessionFactory.getCurrentSession().byNaturalId(Synagogue.class)
				.using("name", prayer.getSynagogue().getName());
		prayer.setSynagogue(synagogueCityFromDB);
		return prayer;
	}
	
	@Override
	public P getByNaturalIDs(P prayer) {
		return (P) sessionFactory.getCurrentSession().byNaturalId(Prayer.class)
				.using("synagogue", prayer.getSynagogue())
				.using("date", prayer.getDate()).load();
	}
	
}
