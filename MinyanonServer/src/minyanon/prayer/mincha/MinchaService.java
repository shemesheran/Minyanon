package minyanon.prayer.mincha;

import java.util.Date;

import javax.transaction.Transactional;

import minyanon.address.Address;
import minyanon.prayer.AbstractPrayerService;
import minyanon.prayer.PrayerDAO;
import minyanon.synagogue.Synagogue;

public class MinchaService extends AbstractPrayerService<Mincha>{

	MinchaService(PrayerDAO<Mincha> prayerDAO) {
		super(prayerDAO);
	}

	@Override
	@Transactional
	public void newPrayer(String addressName, String synagogueName, String dateStr)
			throws Exception {
		
		Address address = new Address(addressName);
		Synagogue synagogue = new Synagogue(synagogueName,address);
		Date date = dateFormatter.parse(dateStr);
		
		prayerDao.registerNew(new Mincha(address, synagogue, date));
	}

}
