package minyanon.prayer;

import minyanon.AbstractREST;

public class PrayerREST extends AbstractREST{
	
	public String prayerType;
	
	public String cityName;
	
	public String synagogueName;
	
	public String datesStr;
		
	public PrayerREST(String prayerType, String city, String synagogue, String date){
		this.prayerType = prayerType;
		this.cityName = city;
		this.synagogueName = synagogue;
		this.datesStr = date;
	}
	
}
