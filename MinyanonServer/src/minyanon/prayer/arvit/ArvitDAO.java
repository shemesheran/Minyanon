package minyanon.prayer.arvit;

import org.hibernate.SessionFactory;

import minyanon.prayer.PrayerDAO;

class ArvitDAO extends PrayerDAO<Arvit> {

	public ArvitDAO(SessionFactory sessionFactory) {
		super(Arvit.class, sessionFactory);
	}
	
	

}