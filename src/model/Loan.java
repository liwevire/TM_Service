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
	@Column(name="return_date")
	Date returnDate;
	@Column(name="return_amount")
	double returnAmount;
	@Column(name="return_status")
	String loanStatus;
	@Column(name="comments")
	String comments;
	@OneToMany(fetch=FetchType.LAZY, mappedBy="loan", cascade=CascadeType.ALL)
	@JsonManagedReference
	List<Item> items = new ArrayList<Item>();
	public Loan() {		super();	}
	public Loan(Customer customer, Date date, double principal, Date returnDate, double returnAmount, String loanStatus,
			String comments) {
		super();
		this.customer = customer;
		this.date = date;
		this.principal = principal;
		this.returnDate = returnDate;
		this.returnAmount = returnAmount;
		this.loanStatus = loanStatus;
		this.comments = comments;
	}

	public Loan(Customer customer, Date date, double principal, Date returnDate, double returnAmount, String loanStatus,
			String comments, List<Item> items) {
		super();
		this.customer = customer;
		this.date = date;
		this.principal = principal;
		this.returnDate = returnDate;
		this.returnAmount = returnAmount;
		this.loanStatus = loanStatus;
		this.comments = comments;
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
	public Date getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
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
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
}