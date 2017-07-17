package model.reports;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@NamedNativeQueries({
	@NamedNativeQuery(
		name = "calculateDailyReport",
		query = "CALL calculateDailyReport(:date,:recursionIndex)"
	),
	@NamedNativeQuery(
		name = "recalculateDailyReport",
		query = "CALL recalculateDailyReport(:date)"
	)
})
@NamedQueries({
	@NamedQuery(
		name="getReportByDate",
		query="from Daily d where d.date = :reportDate"
	)
})
@Entity
@Table(name="Daily")
public class Daily implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="date", unique=true, nullable =false)
	private Date date;
	@Column(name="principal")
	private double principal;
	@Column(name="roi")
	private double roi;
	@Column(name="rop")
	private double rop;
	@Column(name="firstMonthInterest")
	private double firstMonthInterest;
	@Column(name="appraisalCharges")
	private double appraisalCharges;
	@Column(name="closingBalance")
	private double closingBalance;
	
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
	public double getRoi() {
		return roi;
	}
	public void setRoi(double roi) {
		this.roi = roi;
	}
	public double getRop() {
		return rop;
	}
	public void setRop(double rop) {
		this.rop = rop;
	}
	public double getFirstMonthInterest() {
		return firstMonthInterest;
	}
	public double getClosingBalance() {
		return closingBalance;
	}
	public void setClosingBalance(double closingBalance) {
		this.closingBalance = closingBalance;
	}
	public void setFirstMonthInterest(double firstMonthInterest) {
		this.firstMonthInterest = firstMonthInterest;
	}
	public double getAppraisalCharges() {
		return appraisalCharges;
	}
	public void setAppraisalCharges(double appraisalCharges) {
		this.appraisalCharges = appraisalCharges;
	}
	public Daily() {
		super();
	}
	public Daily(Date date, double principal, double roi, double rop, double firstMonthInterest,
			double appraisalCharges, double closingBalance) {
		super();
		this.date = date;
		this.principal = principal;
		this.roi = roi;
		this.rop = rop;
		this.firstMonthInterest = firstMonthInterest;
		this.appraisalCharges = appraisalCharges;
		this.closingBalance = closingBalance;
	}
}