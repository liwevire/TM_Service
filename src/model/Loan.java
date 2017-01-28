package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@NamedQueries({
	@NamedQuery(
			name="getLoanById",
			query="from Loan l where l.loanId = :loanId"
			)
})
@Entity
@Table(name="loan")
public class Loan implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="loan_id", nullable=false, unique=true)
	long loanId;
	@ManyToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="customer_id", nullable = false)
	@JsonBackReference
	Customer customer;
	@Column(name="date", nullable=false)
	Date date;
	@Column(name="principal", nullable=false)
	double principal;
	@Column(name="return_amount")
	double returnAmount;
	@Column(name="loan_status")
	String loanStatus;
	@OneToMany(fetch=FetchType.LAZY, mappedBy="loan", cascade=CascadeType.ALL)
	@JsonManagedReference
	List<Item> items = new ArrayList<Item>();
	public Loan() {		super();	}
	public Loan(Customer customer, Date date, double principal, double returnAmount, String loanStatus) {
		super();
		this.customer = customer;
		this.date = date;
		this.principal = principal;
		this.returnAmount = returnAmount;
		this.loanStatus = loanStatus;
	}
	public Loan(Customer customer, Date date, double principal, double returnAmount, String loanStatus,	List<Item> items) {
		super();
		this.customer = customer;
		this.date = date;
		this.principal = principal;
		this.returnAmount = returnAmount;
		this.loanStatus = loanStatus;
		this.items = items;
	}
	public long getLoanId() {
		return loanId;
	}
	public void setLoanId(long loanId) {
		this.loanId= loanId;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public double getPrincipal() {
		return principal;
	}
	public void setPrincipal(double principal) {
		this.principal = principal;
	}
	public double getReturnAmount() {
		return returnAmount;
	}
	public void setReturnAmount(double returnAmount) {
		this.returnAmount = returnAmount;
	}
	public String getLoanStatus() {
		return loanStatus;
	}
	public void setLoanStatus(String loanStatus) {
		this.loanStatus = loanStatus;
	}
	public List<Item> getItems() {
		return items;
	}
	public void setItems(List<Item> items) {
		this.items = items;
	}
}