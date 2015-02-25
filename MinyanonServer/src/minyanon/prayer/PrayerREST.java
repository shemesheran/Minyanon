package minyanon.prayer;

import minyanon.AbstractREST;
import minyanon.synagogue.SynagogueREST;

public class PrayerREST extends AbstractREST{
	
	public String prayerType;
		
	public SynagogueREST synagogue;
			
	public PrayerREST(String prayerType, SynagogueREST synagogue){
		this.prayerType = prayerType;
		this.synagogue = synagogue;
	}
	
	public PrayerREST(Prayer prayer){
		this.prayerType = prayer.getPrayerType().toString();
		this.synagogue = new SynagogueREST(prayer.getSynagogue());
	}
	
}
