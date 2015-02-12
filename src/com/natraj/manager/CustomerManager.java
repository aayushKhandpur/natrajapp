/**
 * 
 */
package com.natraj.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.natraj.entity.Customer;
import com.natraj.service.CustomerService;
import com.natraj.vo.CustomerVO;

/**
 * @author Aayush
 *
 */
@Component
public class CustomerManager {
	
	@Autowired
	private CustomerService service;
	
	public Customer createCustomer(Customer customer){
		
		return service.createCustomer(customer);
	}
	
	public List<Customer> searchCustomer(String searchType, String searchStr){
		return service.searchCustomer(searchType, searchStr);
	}
	
	public boolean isAuthenticUser(CustomerVO customerVO) {
		return service.isAuthenticUser(customerVO);
	}
	
	public boolean isCustomerExists(CustomerVO customerVO){
		boolean isCustomerExists = false;
		Customer customer = service.getCustomerByEmail(customerVO.getEmail());
		if(customer != null){
			isCustomerExists = true;
		}
		return isCustomerExists;
	}

}
