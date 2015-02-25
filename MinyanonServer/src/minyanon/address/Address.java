package minyanon.address;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

import minyanon.address.city.City;

@Embeddable
public class Address implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	private City city;
	
	private String streetName;
	
	private String streetNumber;
	
	private double latitude;
	
	private double longtitude;
	
	public Address() {}
	
	public Address(String cityName){
		this.city = new City(cityName);
	}
	
	public Address(String cityName, String streetName, String streetNumber, double latitude, double longtitude) {
		city = new City(cityName);
		this.streetName = streetName;
		this.streetNumber = streetNumber;
		this.latitude = latitude;
		this.longtitude = longtitude;
	}
	
	public City getCity() {
		return city;
	}
	
	public void setCity(City city) {
		this.city = city;
	}
	
	public String getStreetName() {
		return streetName;
	}
	
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}
	
	public String getStreetNumber() {
		return streetNumber;
	}
	
	public void setStreetNumber(String streetNumber) {
		this.streetNumber = streetNumber;
	}
	
	public double getLatitude() {
		return latitude;
	}
	
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	
	public double getLongtitude() {
		return longtitude;
	}
	
	public void setLongtitude(double longtitude) {
		this.longtitude = longtitude;
	}
	
}
