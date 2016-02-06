package minyanon.prayer.mincha;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;

import minyanon.prayer.Prayer;
import minyanon.synagogue.Synagogue;

@Entity
public class Mincha extends Prayer implements Serializable{
	
	private static final long serialVersionUID = 1L;

	public Mincha() {
	}
	
	public Mincha(Synagogue synagogue, Date date) {
		super(synagogue, date);
	}
	
	@Override
	public PrayerType getPrayerType() {
		return PrayerType.MINCHA;
	}
	
}
