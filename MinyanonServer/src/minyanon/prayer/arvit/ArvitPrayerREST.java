package minyanon.prayer.arvit;

import minyanon.prayer.PrayerREST;


public class ArvitPrayerREST extends PrayerREST{

	public ArvitPrayerREST(String prayerType, String city, String synagogue, String date) {
		super("Arvit", city, synagogue, date);
	}

}
