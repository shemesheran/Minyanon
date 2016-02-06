package minyanon.prayer.mincha;

import org.hibernate.SessionFactory;

import minyanon.prayer.PrayerDAO;

class MinchaDAO extends PrayerDAO<Mincha> {

	public MinchaDAO(SessionFactory sessionFactory) {
		super(Mincha.class, sessionFactory);
	}

}