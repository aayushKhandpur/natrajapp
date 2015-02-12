package com.natraj.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.natraj.dao.CustomerDao;
import com.natraj.entity.Customer;
import com.natraj.entity.Customer.Gender;
import com.natraj.manager.CustomerManager;
import com.natraj.vo.CustomerVO;

@RestController
public class NatrajController {
	
	@Autowired
	private CustomerDao customerDao;
	
	@Autowired
	private CustomerManager customerManager;
	
	
	
	@RequestMapping("/hello1")
    public String helloWorld(Model model) {
 
        String message = "Hello World, Spring 3.0!";
        model.addAttribute( "message", message);
        Customer c= new Customer();
        c.setName("aayush");
        c.setEmail("aayush.khandpur@gmail.com");
        c.setPhoneNumber("98299");
        customerDao.create(c);
        return "app/home.html";
    }
	
	@RequestMapping(value="/customer/login", method = RequestMethod.POST)
	@ResponseBody
    public Map<String, Object> loginUser(@RequestBody CustomerVO customerVO, HttpServletResponse response) throws IOException {
		Map<String,Object> resultMap = new HashMap<String, Object>();
        boolean isAuthenticUser = false;
        if(StringUtils.isNotBlank(customerVO.getEmail()) && StringUtils.isNotBlank(customerVO.getPassword())){
        	isAuthenticUser = customerManager.isAuthenticUser(customerVO);
        	resultMap.put("name", customerVO.getName());
        }
        resultMap.put("isAuthenticUser", isAuthenticUser);
        if(!isAuthenticUser){
        	response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
        return resultMap;
    }
	
	@RequestMapping(value="/activateAccount", method=RequestMethod.GET)
	public String activateAccount(HttpServletRequest request){
		String email = request.getParameter("email");
		String token = request.getParameter("token");
		if(StringUtils.isNotBlank(email) && StringUtils.isNotBlank(token)){
			Customer customer = customerDao.getCustomerByEmail(email);
			if(customer != null && customer.getEmailValidationCode().equals(token)) {
				customer.setIsEmailValidated(true);
				customer  = customerDao.update(customer);
				return customer.getName();
			}
		}
		return "error";
	}
	
	@RequestMapping(value="/customer/createCustomer", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> customerSignUp(@RequestBody CustomerVO customerVO){
		Map<String,Object> resultMap = new HashMap<String, Object>();
		boolean isCustomerExists = customerManager.isCustomerExists(customerVO);
		if(!isCustomerExists){
			Customer customer= new Customer();
			customer.setName(customerVO.getName());
			customer.setNameUpperCase(customerVO.getName());
			customer.setAddressLine1(customerVO.getAddressLine1());
			customer.setAddressLine2(customerVO.getAddressLine2());
			customer.setEmail(customerVO.getEmail());
			customer.setPhoneNumber(customerVO.getPhone());
			customer.setGender(Gender.valueOf(customerVO.getGender()));
			customer.setPassword(customerVO.getPassword());
			String token = RandomStringUtils.randomAlphanumeric(40);
			customer.setEmailValidationCode(token);
			customerManager.createCustomer(customer);
			//MailUtil.sendMailForRegistration(customer);	
		}
		resultMap.put("isRegistered", !isCustomerExists);
		return resultMap;
	}
	
	@RequestMapping(value="/customer/SearchCustomer", method=RequestMethod.GET)
	public List<Customer> searchCustomer(HttpServletRequest request){
		
		String searchType= request.getParameter("searchType");
		String searchStr=request.getParameter("searchStr");
		List<Customer> customerList= new ArrayList<Customer>();
		if(searchType !=null && !searchType.isEmpty() && searchStr!=null && !searchStr.isEmpty()){
			customerList=customerManager.searchCustomer(searchType, searchStr);
		}
		
		return customerList;
				
	}
	
	
	
}
