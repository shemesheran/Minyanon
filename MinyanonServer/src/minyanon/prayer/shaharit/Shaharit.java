package minyanon.prayer.shaharit;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;

import minyanon.city.City;
import minyanon.prayer.Prayer;
import minyanon.synagogue.Synagogue;

@Entity
public class Shaharit extends Prayer implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	public Shaharit() {
	}
	
	public Shaharit(City city, Synagogue synagogue, Date date) {
		super(city, synagogue, date);
	}

	@Override
	public PrayerType getPrayerType() {
		return PrayerType.SHAHRIT;
	}

	
	
}
