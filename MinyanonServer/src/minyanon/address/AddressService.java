package minyanon.address;

import javax.transaction.Transactional;

public class AddressService {

//	private final AddressDAO addressDao;
//
//	public AddressService(AddressDAO addressDao) {
//		this.addressDao = addressDao;
//	}
//	
//	public Address getAddressFromUserString(String requestCityName, String requestStreetName,
//			String requestStreetNumber){
//		Address address = addressDao.getAdderss(requestCityName, requestStreetName, requestStreetNumber);
//		if(address != null){
//			return address;
//		}
//		else{
//			address = AddressUtils.getAddressFromGoogle(requestCityName, requestStreetName, requestStreetNumber);
//		}
//		return address;
//	}
//	
//	@Transactional
//	private Address getAddressFromDB(String requestCityName, String requestStreetName,
//			String requestStreetNumber){
//		return addressDao.getAdderss(requestCityName, requestStreetName, requestStreetNumber);
//	}
}
