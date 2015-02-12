/**
 * 
 */
package com.natraj.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.natraj.dao.CustomerDao;
import com.natraj.entity.Customer;
import com.natraj.util.CommonUtil;
import com.natraj.vo.CustomerVO;

/**
 * @author Aayush
 *
 */

@Service
public class CustomerService {
	
	@Autowired
	CustomerDao customerDao;
	
	public Customer createCustomer(Customer customer){
		
		return customerDao.create(customer);
	}
	
	public String generateCustomerUID(String name){
		String uniqueId="";
		Calendar calendar= Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("DDHHmmss");
    	String name1=name.replaceAll("\\s+","");
    	name1= name1.replaceAll(".", "");
		uniqueId= name1.substring(0, 3)+"_"+sdf.format(calendar.getTime()) ;
		return uniqueId;
				
	}
	
	public boolean isAuthenticUser(CustomerVO customerVO) {
		boolean isAuthenticUser = false;
		Customer customer = customerDao.getCustomerByEmail(customerVO.getEmail());
		if(customer !=null ){
			isAuthenticUser = customer.getPassword().equals(customerVO.getPassword());
		}
		if(isAuthenticUser){
			HttpSession session = CommonUtil.getCurrentSession();
			session.setAttribute("email", customerVO.getEmail());
		}
		return isAuthenticUser;
	}
	
	public Customer getCustomerByEmail(String email){
		return customerDao.getCustomerByEmail(email);
	}
	public List<Customer> searchCustomer(String searchType, String searchStr){
		List<Customer> resultList= new ArrayList<Customer>();
		List<Customer> returnList=null;
		switch (searchType) {
		case "NAME":
			 returnList=customerDao.getByName(searchStr);
			 break;
		
		case "EMAIL":
			returnList=customerDao.getByEmail(searchStr);
			break;
		
		case "PHONE":
			returnList=customerDao.getByPhoneNumber(searchStr);
			break;
		
		case "UID":
			returnList=customerDao.getByUniqueId(searchStr);
			break;

		default:
			break;
		}
		
		if(returnList!=null){
			resultList.addAll(returnList);
		}
		
		return resultList;
	}
	
}
