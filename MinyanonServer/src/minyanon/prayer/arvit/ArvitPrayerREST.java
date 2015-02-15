package minyanon.prayer.arvit;

import minyanon.prayer.PrayerREST;


public class ArvitPrayerREST extends PrayerREST{

	public ArvitPrayerREST(String prayerType, String address, String synagogue, String date) {
		super("Arvit", address, synagogue, date);
	}

}
