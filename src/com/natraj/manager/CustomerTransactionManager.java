/**
 * 
 */
package com.natraj.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.natraj.entity.CustomerTransaction;
import com.natraj.service.CustomerTransactionService;

/**
 * @author Aayush
 *
 */

@Component
public class CustomerTransactionManager {
	
	@Autowired
	private CustomerTransactionService service;
	
	public  CustomerTransaction createTransaction(CustomerTransaction transaction){
		return service.createTransaction(transaction);
	}
	
}
