package minyanon.address;

public class AddressREST {

	public String cityName;
	
	public String streetName;
	
	public String streetNumber;

	public AddressREST(Address address){
		this.cityName = address.getCity().getName();
		this.streetName = address.getStreetName();
		this.streetNumber = address.getStreetNumber();
	}
}
