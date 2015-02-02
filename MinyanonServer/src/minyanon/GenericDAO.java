package minyanon;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import minyanon.city.City;

import org.hibernate.SessionFactory;

public abstract class GenericDAO<E extends Serializable> {
	
	protected SessionFactory sessionFactory;

	protected final Class<E> type;

	
	public GenericDAO(Class<E> type, SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
		this.type = type;
	}

	public void registerNew(E entity) {
		sessionFactory.getCurrentSession().save(
				getEntityWithAttachedDependencies(entity));
	}

	public void delete(E entity){
		String deleteHQL = "Delete From :entityType where";
		StringBuilder whereClause = new StringBuilder();
		Set<Entry<String, Object>> entrySet = getNaturalIDFieldValuePairs(entity).entrySet();
		int size = entrySet.size();
		for(Map.Entry<String, Object> fieldValuePair : entrySet){
			whereClause.append(fieldValuePair.getKey());
			whereClause.append("=");
			whereClause.append(fieldValuePair.getValue());
			if(--size != 0){
				whereClause.append(" and ");
			}
		}
		sessionFactory.getCurrentSession().createQuery(deleteHQL).executeUpdate();
		
//		Criteria deleteCriteria = sessionFactory.getCurrentSession().createCriteria(type);
//		NaturalIdentifier naturalIdRestriction = Restrictions.naturalId();
//		for(Map.Entry<String, Object> fieldValuePair : getNaturalIDFieldValuePair(entity).entrySet()){
//			
//		}
//		deleteCriteria.add(naturalIdRestriction);
	}

	protected Map<String, Object> getNaturalIDFieldValuePairs(E entity) {

		int[] naturalID_Indexes = sessionFactory.getClassMetadata(type)
				.getNaturalIdentifierProperties();
		String[] propsNames = sessionFactory.getClassMetadata(City.class)
				.getPropertyNames();

		Map<String, Object> naturalIDs = new HashMap<String, Object>();
		for (int i = 0; i < naturalID_Indexes.length; i++) {
			try {
				String fieldName = propsNames[naturalID_Indexes[i]];
				Object fieldValue = entity.getClass().getField(fieldName)
						.get(entity);
				naturalIDs.put(fieldName, fieldValue);
			} catch (Exception e) {
			}
		}
		return naturalIDs;
	}

	/**
	 * Getting the prayer entity and making its dependencies persistent
	 * 
	 * @param the
	 *            Transient entity
	 * @return the prayer with a persistent dependencies
	 */
	protected abstract E getEntityWithAttachedDependencies(E entity);

}
