package com.natraj.dao;

import java.util.Collection;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import org.springframework.stereotype.Repository;


import com.natraj.util.PMF;

@Repository
public class AbstractDao<E> {

	public E save(E object){
		PersistenceManager pm = PMF.get().getPersistenceManager();
        try {
        	object = pm.makePersistent(object);
        } finally {
            pm.close();
        }
        return object;
	}
	
	public E getById(Class<E> type, Object id) {
		PersistenceManager pm = null;
		try {
			pm = PMF.get().getPersistenceManager();
			Object oid = pm.newObjectIdInstance(type, id);
			Object entity = pm.getObjectById(oid);
			return pm.detachCopy((E) entity);
		} finally {
			pm.close();
		}
	}
	
	public Collection<E> getAll(Class<E> type) {
		PersistenceManager pm = null;
		try {
			pm = PMF.get().getPersistenceManager();
			Query query = pm.newQuery(type);
			return pm.detachCopyAll((Collection<E>) query.execute());
		} finally {
			pm.close();
		}
	}
	
	
	
}
