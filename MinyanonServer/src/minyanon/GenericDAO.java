package minyanon;

import java.io.Serializable;

import minyanon.synagogue.Synagogue;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.InitializingBean;


public abstract class GenericDAO<E extends Serializable> implements InitializingBean{
	
	protected SessionFactory sessionFactory;
	
	protected final Class<E> type;
	
	public GenericDAO(Class<E> type, SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
		this.type = type;
	}	
	
	public void registerNew(E entity) {
		sessionFactory.getCurrentSession().save(getEntityWithAttachedDependencies(entity));
	}

	public abstract void delete(E entity);

	
	/**
	 * Getting the prayer entity and making its dependencies persistent
	 * @param the Transient entity
	 * @return the prayer with a persistent dependencies
	 */
	protected abstract E getEntityWithAttachedDependencies(E entity);
	
	@Override
	public void afterPropertiesSet() throws Exception {
		
	}
	
}
