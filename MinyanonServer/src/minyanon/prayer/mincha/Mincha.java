package minyanon.prayer.mincha;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;

import minyanon.city.City;
import minyanon.prayer.Prayer;
import minyanon.synagogue.Synagogue;

@Entity
public class Mincha extends Prayer implements Serializable{
	
	private static final long serialVersionUID = 1L;

	public Mincha() {
	}
	
	public Mincha(City city, Synagogue synagogue, Date date) {
		super(city, synagogue, date);
	}
	
	@Override
	public PrayerType getPrayerType() {
		return PrayerType.MINCHA;
	}
	
}
