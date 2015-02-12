/**
 * 
 */
package com.natraj.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.TimeZone;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.natraj.entity.Customer;
import com.natraj.util.PMF;


/**
 * @author Aayush
 *
 */
@Repository
@Transactional(propagation=Propagation.REQUIRED)
public class CustomerDao extends AbstractDao<Customer> {
	
	public Customer create(Customer object){
		setDateFieldsValues(object);
		return super.save(object);
	}
	
	public Customer update(Customer object){
		updateDateFieldsValues(object);
		return super.save(object);
	}
	
	private void setDateFieldsValues(Customer object){
		TimeZone tz1 = TimeZone.getTimeZone("IST");
		Calendar newDate= Calendar.getInstance(tz1);
		object.setCreatedDate(newDate.getTime());
		object.setLastUpdatedDate(newDate.getTime());
	}
	
	private void updateDateFieldsValues(Customer object){
		TimeZone tz1 = TimeZone.getTimeZone("IST");
		Calendar newDate= Calendar.getInstance(tz1);
		object.setLastUpdatedDate(newDate.getTime());
	}
	
	public Customer getCustomerByEmail(String email) {
		Customer customer = null;
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query newQuery = pm.newQuery(Customer.class);
		newQuery.setFilter("email == emailParam");
		newQuery.declareParameters("String emailParam");
		try {
			List<Customer> results = (List<Customer>) newQuery.execute(email);
			if(!results.isEmpty()){
				customer = results.get(0);
			}
		} finally {
			newQuery.closeAll();
			pm.close();
		}
		return customer;
	}
	
	public List<Customer> getByUniqueId(String uid){
		PersistenceManager pm=null;
		List<Customer> customers= new ArrayList<Customer>();
		
		try {
			pm= PMF.get().getPersistenceManager();
			
			Query query= pm.newQuery(Customer.class,"uniqueId==uniqueIdStr");
			query.declareParameters("String uniqueIdStr");
			Collection<Customer> customerCollection=(Collection<Customer>) query.execute(uid);
			if(customerCollection!=null && customerCollection.size()>0){
				
				customers.addAll(pm.detachCopyAll(customerCollection));
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			pm.close();
		}
		
		
		return customers;
		
	}
	
	public List<Customer>getByName(String name){
		PersistenceManager pm=null;
		List<Customer> customers= new ArrayList<Customer>();
		
		try {
			pm= PMF.get().getPersistenceManager();
			
			Query query= pm.newQuery(Customer.class,"nameUpperCase==nameUpperCaseStr");
			query.declareParameters("String nameUpperCaseStr");
			Collection<Customer> customerCollection=(Collection<Customer>) query.execute(name);
			if(customerCollection!=null && customerCollection.size()>0){
				
				customers.addAll(pm.detachCopyAll(customerCollection));
				
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			pm.close();
		}
		
		
		return customers;
		
	}
	
	public List<Customer> getByEmail(String email){
		PersistenceManager pm=null;
		List<Customer> customers= new ArrayList<Customer>();
		
		try {
			pm= PMF.get().getPersistenceManager();
			
			Query query= pm.newQuery(Customer.class,"email==emailStr");
			query.declareParameters("String emailStr");
			Collection<Customer> customerCollection=(Collection<Customer>) query.execute(email);
			if(customerCollection!=null && customerCollection.size()>0){
				
				customers.addAll(pm.detachCopyAll(customerCollection));
				
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			pm.close();
		}
		
		
		return customers;
		
	}
	
	public List<Customer> getByPhoneNumber(String phone){
		PersistenceManager pm=null;
		List<Customer> customers= new ArrayList<Customer>();
		
		try {
			pm= PMF.get().getPersistenceManager();
			
			Query query= pm.newQuery(Customer.class,"phoneNumber==phoneNumberStr");
			query.declareParameters("String phoneNumberStr");
			Collection<Customer> customerCollection=(Collection<Customer>) query.execute(phone);
			if(customerCollection!=null && customerCollection.size()>0){
				
				customers.addAll(pm.detachCopyAll(customerCollection));
				
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			pm.close();
		}
		
		
		return customers;
	}
	
	
	
	

}
