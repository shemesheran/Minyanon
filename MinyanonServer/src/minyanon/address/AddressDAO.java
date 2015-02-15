package minyanon.address;

import java.util.List;

import minyanon.GenericDAO;

import org.hibernate.SessionFactory;

class AddressDAO extends GenericDAO<Address> {

	public AddressDAO(SessionFactory sessionFactory) {
		super(Address.class, sessionFactory);
	}

	List<Address> getAllAddresses() {
		return sessionFactory.getCurrentSession().createCriteria(Address.class).list();
	}

	@Override
	protected Address getEntityWithAttachedDependencies(Address address) {
		return address;
	}

	public Address getAdderss(String requestCityName, String requestStreetName,
			String requestStreetNumber) {
	}

}
