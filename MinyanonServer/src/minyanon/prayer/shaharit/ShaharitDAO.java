package minyanon.prayer.shaharit;

import org.hibernate.SessionFactory;

import minyanon.prayer.PrayerDAO;

class ShaharitDAO extends PrayerDAO<Shaharit>{

		public ShaharitDAO(SessionFactory sessionFactory) {
			super(Shaharit.class, sessionFactory);
		}
		
	}