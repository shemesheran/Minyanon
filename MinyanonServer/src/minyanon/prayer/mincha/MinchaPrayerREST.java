package minyanon.prayer.mincha;

import minyanon.prayer.PrayerREST;


public class MinchaPrayerREST extends PrayerREST{

	public MinchaPrayerREST(String city, String synagogue, String date) {
		super("Mincha", city, synagogue, date);
	}

}