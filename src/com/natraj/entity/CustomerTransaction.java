/**
 * 
 */
package com.natraj.entity;

import java.io.Serializable;
import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

/**
 * @author Aayush
 *
 */

@SuppressWarnings("serial")
@PersistenceCapable(detachable = "true", identityType = IdentityType.APPLICATION)
public class CustomerTransaction implements Serializable{
	
	public enum Vehicle{
		BAJAJ("Bajaj"), HYUNDAI("Hyundai"), MAHINDRA_PERSONAL("Mahindra Personal"), ASHOK_LEYLAND("Ashok Leyland");
		
		private final String description;
		
		private Vehicle(String s){
			description=s;
		}
		
		public String getDescription(){
			return description;
		}
	}
	
	public enum TransactionType{
		ALLOCATE("Allocate"), ENCASH("Encash");
		
		private final String description;
		
		private TransactionType(String s){
			description=s;
		}
		
		public String getDescription(){
			return description;
		}
		
	}
	
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key key;
	
	@Persistent
	private Key customerKey;
	
	@NotPersistent
	private Customer customer;
	
	@Persistent
	private Vehicle vehicle;
	
	@Persistent
	private Integer points;
	
	@Persistent
	private TransactionType transactionType;
	
	@Persistent
	private Date transactionDate;
	
	@Persistent
	private String uniqueId;
	
	@Persistent
	private Date createdDate;
	
	@Persistent
	private Date lastUpdatedDate;
	
	

	/**
	 * @return the createdDate
	 */
	public Date getCreatedDate() {
		return createdDate;
	}

	/**
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * @return the lastUpdatedDate
	 */
	public Date getLastUpdatedDate() {
		return lastUpdatedDate;
	}

	/**
	 * @param lastUpdatedDate the lastUpdatedDate to set
	 */
	public void setLastUpdatedDate(Date lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}

	/**
	 * @return the key
	 */
	public Key getKey() {
		return key;
	}

	/**
	 * @param key the key to set
	 */
	public void setKey(Key key) {
		this.key = key;
	}

	/**
	 * @return the customerKey
	 */
	public Key getCustomerKey() {
		return customerKey;
	}

	/**
	 * @param customerKey the customerKey to set
	 */
	public void setCustomerKey(Key customerKey) {
		this.customerKey = customerKey;
	}

	/**
	 * @return the customer
	 */
	public Customer getCustomer() {
		return customer;
	}

	/**
	 * @param customer the customer to set
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	/**
	 * @return the vehicle
	 */
	public Vehicle getVehicle() {
		return vehicle;
	}

	/**
	 * @param vehicle the vehicle to set
	 */
	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	/**
	 * @return the points
	 */
	public Integer getPoints() {
		return points;
	}

	/**
	 * @param points the points to set
	 */
	public void setPoints(Integer points) {
		this.points = points;
	}

	/**
	 * @return the transactionType
	 */
	public TransactionType getTransactionType() {
		return transactionType;
	}

	/**
	 * @param transactionType the transactionType to set
	 */
	public void setTransactionType(TransactionType transactionType) {
		this.transactionType = transactionType;
	}

	/**
	 * @return the transactionDate
	 */
	public Date getTransactionDate() {
		return transactionDate;
	}

	/**
	 * @param transactionDate the transactionDate to set
	 */
	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	/**
	 * @return the uniqueId
	 */
	public String getUniqueId() {
		return uniqueId;
	}

	/**
	 * @param uniqueId the uniqueId to set
	 */
	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}
	
	
	
	
	
	
}
