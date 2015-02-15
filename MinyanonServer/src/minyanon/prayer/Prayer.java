package minyanon.prayer;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import minyanon.AbstractEntity;
import minyanon.address.Address;
import minyanon.synagogue.Synagogue;

import org.hibernate.annotations.NaturalId;

@SuppressWarnings("serial")
@Entity
public abstract class Prayer extends AbstractEntity implements Serializable{
	
	public enum PrayerType{
		SHAHRIT {
			public String toString(){
				return "Shaharit";
			}
		}, 
		MINCHA {
			public String toString(){
				return "Mincha";
			}
		}, 
		ARVIT {
			public String toString(){
				return "Arvit";
			}
		}
	}
	
	public enum PrayerStyle{
		SFARAD, ASHKENAZ
	}
	
	@ManyToOne
	@JoinColumn(name="city_FK")
	protected Address city;
	
	@NaturalId
	@NotNull
	@ManyToOne
	@JoinColumn(name="synagogue_FK")
	protected Synagogue synagogue;
	
	@NaturalId
	@NotNull
	protected Date date;
		
	public Prayer() {}
	
	public Prayer(Address city, Synagogue synagogue, Date date) {
		this.city = city;
		this.synagogue = synagogue;
		this.date = date;
	}
	
	public void setId(long id) {
		this.id = id;
	}
		
	public long getId() {
		return id;
	}
	
	public Address getCity() {
		return city;
	}
	
	public void setCity(Address city) {
		this.city = city;
	}
	
	public Synagogue getSynagogue() {
		return synagogue;
	}

	public void setSynagogue(Synagogue synagogue) {
		this.synagogue = synagogue;
	}
	
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public abstract PrayerType getPrayerType();

}
