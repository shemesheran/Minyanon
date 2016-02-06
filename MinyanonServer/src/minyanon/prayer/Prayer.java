package minyanon.prayer;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.NaturalId;

import minyanon.AbstractEntity;
import minyanon.synagogue.Synagogue;

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
	
	@NaturalId
	@NotNull
	@ManyToOne
	@JoinColumn(name="synagogue_FK")
	protected Synagogue synagogue;
	
	@NaturalId
	@NotNull
	protected Date date;
		
	public Prayer() {}
	
	public Prayer(Synagogue synagogue, Date date) {
		this.synagogue = synagogue;
		this.date = date;
	}
	
	public void setId(long id) {
		this.id = id;
	}
		
	public long getId() {
		return id;
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
