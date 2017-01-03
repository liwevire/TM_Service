package model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@NamedQueries({
	@NamedQuery(
			name="listAllCustomer",
			query="from Customer"
			),
	@NamedQuery(
			name="findCustomerByName",
			query="from Customer c where lower(c.name) like ':name%'"
			)
})
@Entity
@Table(name="customer")
public class Customer implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	long customerId;
	String name;
	String secondaryName;
	Date date;
	String address;
	String post;
	String pin;
	String phone;
	Set<Loan> loans = new HashSet<Loan>();
	public Customer() {		super();	}
	public Customer(String name, String secondaryName, Date date, String address, String post, String pin, String phone) {
		super();
		this.name = name;
		this.secondaryName = secondaryName;
		this.date = date;
		this.address = address;
		this.post = post;
		this.pin = pin;
		this.phone = phone;
	}
	public Customer(String name, String secondaryName, Date date, String address, String post, String pin, String phone,
			Set<Loan> loans) {
		super();
		this.name = name;
		this.secondaryName = secondaryName;
		this.date = date;
		this.address = address;
		this.post = post;
		this.pin = pin;
		this.phone = phone;
		this.loans = loans;
	}
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="customer_id", unique=true, nullable =false)
	public long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}
	@Column(name="name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name="secondary_name")
	public String getSecondaryName() {
		return secondaryName;
	}
	public void setSecondaryName(String secondaryName) {
		this.secondaryName = secondaryName;
	}
	@Column(name="date")
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	@Column(name="address")
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Column(name="post")
	public String getPost() {
		return post;
	}
	public void setPost(String post) {
		this.post = post;
	}
	@Column(name="pin")
	public String getPin() {
		return pin;
	}
	public void setPin(String pin) {
		this.pin = pin;
	}
	@Column(name="phone")
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@OneToMany(fetch=FetchType.LAZY, mappedBy="customer")
	@JsonManagedReference
	public Set<Loan> getLoans() {
		return loans;
	}
	public void setLoans(Set<Loan> loans) {
		this.loans = loans;
	}
}