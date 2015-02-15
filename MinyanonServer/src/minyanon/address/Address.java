package minyanon.address;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import minyanon.AbstractEntity;
import minyanon.prayer.Prayer;
import minyanon.synagogue.Synagogue;

import org.hibernate.annotations.NaturalId;
import org.hibernate.validator.constraints.NotBlank;

@Entity
public class Address extends AbstractEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@NotBlank
	@NaturalId
	private String cityName;
	
	@NaturalId
	private String streetName;
	
	@NaturalId
	private String streetNumber;
	
	private double latitude;
	
	private double longtitude;
	
	public Address(){}
	
	public Address(String cityName, String streetName, String streetNumber) {
		this.cityName = cityName;
		this.streetName = streetName;
		this.streetNumber = streetNumber;
	}
	
	public String getCityName() {
		return cityName;
	}
	
	public void setCityName(String cityName) {
		this.cityName = cityName;
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
