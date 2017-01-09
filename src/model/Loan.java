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
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	long loanId;
	Customer customer;
	Date date;
	double amount;
	List<Item> items = new ArrayList<Item>();
	public Loan() {		super();	}
	public Loan(Customer customer, Date date, double amount) {
		super();
		this.customer = customer;
		this.date = date;
		this.amount = amount;
	}
	public Loan(Customer customer, Date date, double amount, List<Item> items) {
		super();
		this.customer = customer;
		this.date = date;
		this.amount = amount;
		this.items = items;
	}
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="loan_id", nullable=false, unique=true)
	public long getLoanId() {
		return loanId;
	}
	public void setLoanId(long loanId) {
		this.loanId= loanId;
	}
	@ManyToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="customer_id", nullable = false)
	@JsonBackReference
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	@Column(name="date", nullable=false)
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	@Column(name="amount", nullable=false)
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	@OneToMany(fetch=FetchType.LAZY, mappedBy="loan", cascade=CascadeType.ALL)
	@JsonManagedReference
	public List<Item> getItems() {
		return items;
	}
	public void setItems(List<Item> items) {
		this.items = items;
	}
}