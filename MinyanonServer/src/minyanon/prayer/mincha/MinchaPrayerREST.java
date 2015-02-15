package minyanon.prayer.mincha;

import minyanon.prayer.PrayerREST;


public class MinchaPrayerREST extends PrayerREST{

	public MinchaPrayerREST(String address, String synagogue, String date) {
		super("Mincha", address, synagogue, date);
	}

}
