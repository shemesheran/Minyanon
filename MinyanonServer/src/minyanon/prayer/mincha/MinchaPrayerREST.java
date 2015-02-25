package minyanon.prayer.mincha;

import minyanon.prayer.PrayerREST;
import minyanon.synagogue.SynagogueREST;


public class MinchaPrayerREST extends PrayerREST{

	public MinchaPrayerREST(String prayerType, SynagogueREST synagogue) {
		super("Mincha", synagogue);
	}

}
