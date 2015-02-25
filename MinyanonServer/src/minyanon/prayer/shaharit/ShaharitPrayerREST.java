package minyanon.prayer.shaharit;

import minyanon.prayer.PrayerREST;
import minyanon.synagogue.SynagogueREST;


public class ShaharitPrayerREST extends PrayerREST{

	public ShaharitPrayerREST(String prayerType, SynagogueREST synagogue) {
		super("Shaharit", synagogue);
	}

}
