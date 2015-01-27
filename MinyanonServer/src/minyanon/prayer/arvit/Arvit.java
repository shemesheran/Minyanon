package minyanon.prayer.arvit;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;

import minyanon.city.City;
import minyanon.prayer.Prayer;
import minyanon.synagogue.Synagogue;

@Entity
public class Arvit extends Prayer implements Serializable{
	
	private static final long serialVersionUID = 1L;

	public Arvit() {
	}
	
	public Arvit(City city, Synagogue synagogue, Date date) {
		super(city, synagogue, date);
	}

	@Override
	public PrayerType getPrayerType() {
		return PrayerType.ARVIT;
	}
}
