package minyanon.prayer.shaharit;

import minyanon.prayer.PrayerREST;


public class ShaharitPrayerREST extends PrayerREST{

	public ShaharitPrayerREST(String prayerType, String city, String synagogue, String date) {
		super("Shaharit", city, synagogue, date);
	}

}