package model.core;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@NamedQueries({
	@NamedQuery(
			name="getMiscTransactionByDate",
			query="from MiscTransaction m where m.date = date(:date)"
			)
//	@NamedQuery(
//			name="getLoanById",
//			query="from Loan l where l.loanId = :loanId"
//			)
	})	
@Entity
@Table(name="misctransaction")
public class MiscTransaction implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="transaction_id", nullable=false, unique=true)
	long transactionId;
	@Column(name="date", nullable=false)
	Date date;
	@Column(name="amount", nullable=false)
	double amount;
	@Column(name="description", nullable=false)
	String description;
	@Column(name="transactionType", nullable=false)
	boolean transactionType;
	public MiscTransaction() {		super();	}
	public long getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(long transactionId) {
		this.transactionId = transactionId;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public boolean isTransactionType() {
		return transactionType;
	}
	public void setTransactionType(boolean transactionType) {
		this.transactionType = transactionType;
	}
}