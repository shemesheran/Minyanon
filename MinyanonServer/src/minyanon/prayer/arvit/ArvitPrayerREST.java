package minyanon.prayer.arvit;

import minyanon.prayer.PrayerREST;
import minyanon.synagogue.SynagogueREST;


public class ArvitPrayerREST extends PrayerREST{

	public ArvitPrayerREST(String prayerType, SynagogueREST synagogue) {
		super("Arvit", synagogue);
	}

}
