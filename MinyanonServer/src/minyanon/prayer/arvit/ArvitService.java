package minyanon.prayer.arvit;

import java.util.Date;

import javax.transaction.Transactional;

import minyanon.address.Address;
import minyanon.prayer.AbstractPrayerService;
import minyanon.synagogue.Synagogue;

public class ArvitService extends AbstractPrayerService<Arvit>{

	ArvitService(ArvitDAO prayerDAO) {
		super(prayerDAO);
	}

	@Override
	@Transactional
	public void newPrayer(String addressesName, String synagogueName, String dateStr)
			throws Exception {

		Address address = new Address(addressesName);
		Synagogue synagogue = new Synagogue(synagogueName,address);
		Date date = dateFormatter.parse(dateStr);
		
		prayerDao.registerNew(new Arvit(address, synagogue, date));
	}

}
