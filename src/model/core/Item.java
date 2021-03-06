package model.core;

import java.io.Serializable;

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
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@NamedQueries({
	@NamedQuery(
			name="getItemById",
			query="from Item i where i.itemId = :itemId"
			),
	@NamedQuery(
			name="deleteItemsbyLoanId",
			query="delete from Item i where i.loan.loanId = :loanId"
			)
	})	
@Entity
@Table(name="item")
public class Item implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="item_id", nullable=false, unique=true)
	long itemId;
	@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="loan_id", nullable = false)
	@JsonBackReference
	Loan loan;
	@Column(name="name", nullable=false)
	String name;
	@Column(name="quantity", nullable=false)
	float quantity;
	public Item() {		super();	}
	public long getItemId() {
		return itemId;
	}
	public void setItemId(long itemId) {
		this.itemId = itemId;
	}
	public Loan getLoan() {
		return loan;
	}
	public void setLoan(Loan loan) {
		this.loan = loan;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getQuantity() {
		return quantity;
	}
	public void setQuantity(float quantity) {
		this.quantity = quantity;
	}
}