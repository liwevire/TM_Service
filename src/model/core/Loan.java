package model.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
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
			),
	@NamedQuery(
			name="deleteLoanbyLoanId",
			query="delete from Loan l where l.loanId = :loanId"
			)
})
@NamedNativeQueries({
	@NamedNativeQuery(
			name = "getLoansByDate",
			query = "select * from tm_core.loan where tm_core.loan.loan_id in "
						+ "(select tm_core.loan.loan_id from tm_core.loan inner join tm_core.transaction " + 
							"on tm_core.loan.loan_id = tm_core.transaction.loan_id " + 
							"where " + 
								"loan_status = 'open' and " + 
								"category = 'principal' and " + 
								"date = :date " + 
						"order by date asc)"
	),
	@NamedNativeQuery(
			name = "getLoansBetweenDates",
			query = "select * from tm_core.loan where tm_core.loan.loan_id in "
						+ "(select tm_core.loan.loan_id from tm_core.loan inner join tm_core.transaction " + 
							"on tm_core.loan.loan_id = tm_core.transaction.loan_id " + 
							"where " + 
								"loan_status = 'open' and " + 
								"category = 'principal' and " + 
								"date >= :fromDate and " +
								"date <= :toDate " +
						"order by date asc)"
	)
})
@Entity
@Table(name="loan")
public class Loan implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
//	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="loan_id", nullable=false, unique=true)
	String loanId;
	@ManyToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="customer_id", nullable = false)
	@JsonBackReference
	Customer customer;
	@Column(name="loan_status")
	String loanStatus;
	@Column(name="weight")
	double weight;
	@Column(name="comments")
	String comments;
	@OneToMany(fetch=FetchType.LAZY, mappedBy="loan", cascade=CascadeType.ALL)
	@JsonManagedReference
	List<Item> items = new ArrayList<Item>();
	@OneToMany(fetch=FetchType.LAZY, mappedBy="loan", cascade=CascadeType.ALL)
	@JsonManagedReference
	List<Transaction> transactions= new ArrayList<Transaction>();
	public Loan() {		super();	}
	public String getLoanId() {
		return loanId;
	}
	public void setLoanId(String loanId) {
		this.loanId = loanId;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public String getLoanStatus() {
		return loanStatus;
	}
	public void setLoanStatus(String loanStatus) {
		this.loanStatus = loanStatus;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public List<Item> getItems() {
		return items;
	}
	public void setItems(List<Item> items) {
		this.items = items;
	}
	public List<Transaction> getTransactions() {
		return transactions;
	}
	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}
}