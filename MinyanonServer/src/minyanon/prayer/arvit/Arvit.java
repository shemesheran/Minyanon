package minyanon.prayer.arvit;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;

import minyanon.address.Address;
import minyanon.prayer.Prayer;
import minyanon.synagogue.Synagogue;

@Entity
public class Arvit extends Prayer implements Serializable{
	
	private static final long serialVersionUID = 1L;

	public Arvit() {
	}
	
	public Arvit(Synagogue synagogue, Date date) {
		super(synagogue, date);
	}

	@Override
	public PrayerType getPrayerType() {
		return PrayerType.ARVIT;
	}
}
