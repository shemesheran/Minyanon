package minyanon.address;

import java.util.LinkedList;
import java.util.List;

import javax.transaction.Transactional;

import minyanon.RESTToEntityService;


class AddressRESTToEntityService extends RESTToEntityService<AddressREST, Address>{
	
	private final AddressDAO addressDao;
	
	public AddressRESTToEntityService(AddressDAO addressDao) {
		this.addressDao = addressDao;
	}

	@Transactional
	public List<AddressREST> getAll(AddressREST addressREST) {
		List<AddressREST> addressRESTList = new LinkedList<AddressREST>();
		for (Address address : addressDao.getAllAddresses()){
			addressRESTList.add(new AddressREST(address.getName()));
		}
		return addressRESTList;
	}
	
	@Transactional
	public void addNewAddress(String addressName){
		addressDao.registerNew(new Address(addressName));
	}
	
	@Transactional
	public void deleteAddress(String addressName){
		addressDao.delete(new Address(addressName));
	}
	
}
