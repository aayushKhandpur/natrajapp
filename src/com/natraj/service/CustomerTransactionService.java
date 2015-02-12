/**
 * 
 */
package com.natraj.service;

import java.util.Calendar;
import java.util.Collection;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.appengine.api.datastore.Key;
import com.natraj.dao.CustomerTransactionDao;
import com.natraj.entity.CustomerTransaction;
import com.natraj.entity.CustomerTransaction.Vehicle;

/**
 * @author Aayush
 *
 */

@Service
public class CustomerTransactionService {
	
	@Autowired
	private CustomerTransactionDao customerTransactionDao;
	
	
	public  CustomerTransaction createTransaction(CustomerTransaction transaction){
		if(transaction.getTransactionType().toString().equals("ALLOCATE")){
			int points=getPointsToAssign(transaction.getCustomerKey(), transaction.getVehicle());
			transaction.setPoints(points);
		}
		TimeZone tz1 = TimeZone.getTimeZone("IST");
		Calendar newDate= Calendar.getInstance(tz1);
		transaction.setTransactionDate(newDate.getTime());
		CustomerTransaction customerTransaction=customerTransactionDao.create(transaction);
		
		return customerTransaction;
	}
	
	
	public int getPointsToAssign(Key customerKey,Vehicle vehicle){
		
		//TODO get recordes based on customer KEy and type as allocated and vehile type
		Collection<CustomerTransaction> transactionCollection= customerTransactionDao.getAllRecords();
		int totalObj=0;
		int points=0;
		if(transactionCollection!=null){
			totalObj=transactionCollection.size();
		}
		
		String vehicleType=vehicle.toString();
		
		switch (vehicle) {
		
		case BAJAJ:
			points=getBajajPoints(totalObj);
			break;
			
		case HYUNDAI:
			points=getHyundaiPoints(totalObj);
			break;
			
		case MAHINDRA_PERSONAL:
			points=getMahindraPoints(totalObj);
			break;
		
		case ASHOK_LEYLAND:
			points=getAshokPoints(totalObj);
			break;

		default:
			break;
		}
		
		
		return points;
		
	}
	
	private int getBajajPoints(int totalVehicle){
		int points=0;
		if(totalVehicle<3){
			points=50;
		}else if(totalVehicle>2 && totalVehicle<6){
			points=100;
		}else if(totalVehicle>5){
			points=150;
		}
		
		return points;
		
		
	}
	
	private int getHyundaiPoints(int totalVehicle){
		int points=0;
		if(totalVehicle<3){
			points=500;
		}else if(totalVehicle>2 && totalVehicle<6){
			points=1000;
		}else if(totalVehicle>5){
			points=1500;
		}
		
		return points;
		
		
	}
	
	private int getMahindraPoints(int totalVehicle){
		int points=0;
		if(totalVehicle<3){
			points=700;
		}else if(totalVehicle>2 && totalVehicle<6){
			points=1200;
		}else if(totalVehicle>5){
			points=1500;
		}
		
		return points;
		
		
	}
	
	private int getAshokPoints(int totalVehicle){
		int points=0;
		if(totalVehicle<3){
			points=2000;
		}else if(totalVehicle>2 && totalVehicle<6){
			points=2600;
		}else if(totalVehicle>5){
			points=3000;
		}
		
		return points;
		
		
	}
}
