/**
 * 
 */
package com.natraj.dao;

import java.util.Calendar;
import java.util.Collection;
import java.util.TimeZone;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.natraj.entity.CustomerTransaction;

/**
 * @author Aayush
 *
 */

@Repository
@Transactional(propagation=Propagation.REQUIRED)
public class CustomerTransactionDao extends AbstractDao<CustomerTransaction> {
	
	public CustomerTransaction create(CustomerTransaction object){
		setDateFieldsValues(object);
		return super.save(object);
	}
	
	private void setDateFieldsValues(CustomerTransaction object){
		TimeZone tz1 = TimeZone.getTimeZone("IST");
		Calendar newDate= Calendar.getInstance(tz1);
		object.setCreatedDate(newDate.getTime());
		object.setLastUpdatedDate(newDate.getTime());
	}
	
	public Collection<CustomerTransaction> getAllRecords(){
		return super.getAll(CustomerTransaction.class);
	}

}
